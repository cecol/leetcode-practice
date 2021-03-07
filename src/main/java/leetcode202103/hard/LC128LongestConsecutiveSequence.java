package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Set;
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
     *
     * 但回應的答案更簡單 直接先把int[] nums 轉成 set, 先取出一個, 然後直接拿 該 num -- or ++ 邊拿邊計數直到拿完
     * 我覺得如果卡在得 從 0 -> n-1 access nums這樣固定的想法, 是不會想到set的解法
     * */
    public int longestConsecutive(int[] nums) {
        Set<Integer> s = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int res =0;
        while (s.size()>0) {
            Integer n = s.iterator().next();
            int c=0, l = n-1,r=n;
            while (s.remove(l--)) c++;
            while (s.remove(r++)) c++;
            res = Math.max(res,c);
        }
        return res;
    }
}
