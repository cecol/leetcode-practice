package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LC179LargestNumber extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC179LargestNumber();
        LC.largestNumber(new int[]{3, 30, 34, 5, 9});
    }

    /**
     * 有很短的解法
     * https://leetcode.com/problems/largest-number/discuss/53162/My-3-lines-code-in-Java-and-Python
     * 我看到就想到先 radix sort 配上 Max queue
     * PriorityQueue<Integer>[] bucket = new PriorityQueue[10];
     * 但花很多時間去作出合理的 同一個基數但組合最大化的可能
     * 結果看到人家的比對方式才驚覺我想太多, 直接字串組合比對
     * bucket[i] = new PriorityQueue<>((i1, i2) -> {
     * String s1 = i1.toString() + i2.toString();
     * String s2 = i2.toString() + i1.toString();
     * return s2.compareTo(s1);
     * });
     * 很短的解法是很精簡, 但速度蠻慢的
     * radix sort 比較快 -> 5 ms, faster than 62.76% of Java, less than 92.89% of Java
     * 很短的解法要 9 ms 差快一倍, 我猜大概是有很多test case 導致 O(nlogn) 超慢
     */
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "0";
        if (nums.length == 1) return String.valueOf(nums[0]);
        PriorityQueue<String>[] bucket = new PriorityQueue[10];
        for (int i = 0; i < 10; i++)
            bucket[i] = new PriorityQueue<String>((i1, i2) -> {
                String s1 = i1 + i2;
                String s2 = i2 + i1;
                return s1.compareTo(s2);
            });
        for (Integer n : nums) {
            bucket[n.toString().charAt(0) - '0'].add(n.toString());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            PriorityQueue<String> mq = bucket[i];
            while (mq.size() > 0) sb.append(mq.poll());
        }
        while (sb.length() > 1 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.toString();
    }
}
