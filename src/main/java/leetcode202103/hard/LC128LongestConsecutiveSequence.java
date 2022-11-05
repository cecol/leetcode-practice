package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class LC128LongestConsecutiveSequence extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC128LongestConsecutiveSequence();
    }

    /**
     * https://leetcode.com/problems/longest-consecutive-sequence/discuss/41055/My-really-simple-Java-O(n)-solution-Accepted
     * 我是無法想出 O(N)的解法, 雖然有想到應該要用 HashMap, 但該怎麼好好使用沒想到
     * 正確答案是還不錯, 用HashMap 記載看到的 number, k = num, v = 該num目前已知的有延伸多長
     * 所以每一個新進num 都去檢查有無 num-1, num+1 => 有的話 就是拿他們來延長
     * 當然 num+1, num-1 也都要跟著更新
     * <p>
     * 但回應的答案更簡單 直接先把int[] nums 轉成 set, 先取出一個, 然後直接拿 該 num -- or ++ 邊拿邊計數直到拿完
     * 我覺得如果卡在得 從 0 -> n-1 access nums這樣固定的想法, 是不會想到set的解法
     * <p>
     * 2022/11/3
     * https://leetcode.com/problems/longest-consecutive-sequence/discuss/41055/My-really-simple-Java-O(n)-solution-Accepted
     * Set 解法會變成TLE, 得回到用 HashMap 解法才會過
     * <p>
     * https://leetcode.com/problems/longest-consecutive-sequence/discuss/41055/My-really-simple-Java-O(n)-solution-Accepted
     * 裡面有一個解釋是說, 把它當作一個graph 來看
     * consecutive 的數字間有edge, 就會成一個圖, 所以視為有多個 multiple disconnected subgraphs,
     * 每個都是consecutive 數字集成,
     * 1. 所以要做的就是先走遍 nums, 把所有點的 edge建立起來
     * 2. 然後 bfs 走過所有點, 就會知道每個圖的 max path, 找出所有圖中 max path
     * 我猜這題主要就是考圖論這個概念(其實還有 union find 解法)
     */
    public int longestConsecutiveGraph(int[] nums) {
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int n : nums) {
            if (!m.containsKey(n)) m.put(n, new ArrayList<>());
            if (m.containsKey(n - 1)) {
                m.get(n).add(n - 1);
                m.get(n - 1).add(n);
            }
            if (m.containsKey(n + 1)) {
                m.get(n).add(n + 1);
                m.get(n + 1).add(n);
            }
        }
        Set<Integer> v = new HashSet<>();
        Queue<Integer> bfs = new LinkedList<>();
        int res = 0;
        for (Integer p : m.keySet()) {
            if (v.contains(p)) continue;
            v.add(p);
            bfs.add(p);
            int c = 1;
            while (bfs.size() > 0) {
                Integer n = bfs.poll();
                for (Integer nn : m.get(n)) {
                    if (v.contains(nn)) continue;
                    bfs.add(nn);
                    v.add(nn);
                    c++;
                }
            }
            res = Math.max(res, c);
        }
        return res;
    }

    public int longestConsecutiveMap(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        int res = 0;
        for (int n : nums) {
            if (!m.containsKey(n)) {
                int left = m.containsKey(n - 1) ? m.get(n - 1) : 0;
                int right = m.containsKey(n + 1) ? m.get(n + 1) : 0;
                int sum = left + right + 1;
                m.put(n, sum);
                res = Math.max(res, sum);
                // 這邊再延伸邊界, left 是指, n 左邊(n-1) 有延伸多長, 現在加上n 了, 該邊界也要更新
                // EX, n == 3, n-1 = 2, m[2->2](代表有 1,2), m[3-2]=sum => m[1]=sum(代表延伸到3)
                m.put(n - left, sum);
                m.put(n + right, sum);
            }
        }
        return res;
    }

    public int longestConsecutiveOld(int[] nums) {
        Set<Integer> s = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int res = 0;
        while (s.size() > 0) {
            Integer n = s.iterator().next();
            int c = 0, l = n - 1, r = n;
            while (s.remove(l--)) c++;
            while (s.remove(r++)) c++;
            res = Math.max(res, c);
        }
        return res;
    }
}
