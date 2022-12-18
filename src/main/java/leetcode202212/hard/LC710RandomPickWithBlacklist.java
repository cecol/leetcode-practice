package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC710RandomPickWithBlacklist extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC710RandomPickWithBlacklist();
    }

    /**
     * https://leetcode.com/problems/random-pick-with-blacklist/solutions/188735/java-map-solution/
     * reject sampling 還是會 TLE
     * 這題比較特別的操作是
     * 把 blacklist 的人 mapping 到合法的人
     * 先用 Set<Integer> blackSet = new HashSet<>(); 記載有哪些 blacklist
     * 所以剩下來可以 random 的區間變成 n - blackSet.size();
     * <p>
     * 等於說 n 的前半段 是合法的 , 後半段給 blacklist
     * 可是 前半段還是有 id 會在 blacklist 內
     * 後半段也會有 id 是合法的
     * <p>
     * 所以建立 Map<Integer, Integer> blackMirror
     * 把每個 blacklist id 都看看
     * 1. if (i >= m) continue; 如果在後半段就不用 mapping
     * 2. int mirror = n - 1; 可以 mirror 的合法 id 是從後半段開始找
     * - while (blackSet.contains(mirror)) mirror--; 找到後半段 id 是 合法的
     * blackMirror.put(i, mirror--); 建立 mirror
     * <p>
     * 所以之後在
     * int v = rand.nextInt(m);
     * 如果是抽到前半段但還是在黑名單的
     * 去 mirror map 找吧
     * if (blackMirror.containsKey(v)) return blackMirror.get(v);
     *
     * 還是要配上 while(true) 某種程度也還是 reject sampling
     * 只是比較快
     */
    class Solution {

        Map<Integer, Integer> blackMirror = new HashMap<>();
        int m = 0;
        Random rand = new Random();

        public Solution(int n, int[] blacklist) {
            Set<Integer> blackSet = new HashSet<>();
            for (int i : blacklist) blackSet.add(i);
            this.m = n - blackSet.size();

            int mirror = n - 1;
            for (int i : blacklist) {
                if (i >= m) continue;
                while (blackSet.contains(mirror)) mirror--;
                blackMirror.put(i, mirror--);
            }
        }

        public int pick() {
            while (true) {
                int v = rand.nextInt(m);
                if (blackMirror.containsKey(v)) return blackMirror.get(v);
                return v;
            }
        }
    }
}
