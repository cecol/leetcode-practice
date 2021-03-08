package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC238ProductOfArrayExceptSelf extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC238ProductOfArrayExceptSelf();
    }

    /**
     * 不能用除法還要 O(N) 我還真的想不出來, 一直覺得有什麼數學上的特性我沒想到
     * https://leetcode.com/problems/product-of-array-except-self/discuss/65622/Simple-Java-solution-in-O(n)-without-extra-space
     * 直到看到答案才意會到我想太多, 但到題目叫我不要用除法, 結果腦袋都一直在想沒除法怎可能 O(N) (心理暗示太強 馬的)
     * 事實上最無腦就是, 準備兩個 array
     * left array[i] 就是 nums 0 -> i-1的乘積
     * right array[i] 就是 nums i+1 -> n-1的乘積
     * 然後兩個array[i] 相乘就是答案也是 O(N) 只是會多用一個 O(N) space 除了答案之外
     * 想要只用到 答案的 space O(N)
     * 就是先用答案的 int[] res先存  1 -> n-1的 left 結果, 然後在算 (n-1) -> 1 right的情況
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0) left = left * nums[i - 1];
            res[i] = left;
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1) right = right * nums[i + 1];
            res[i] *= right;
        }
        return res;
    }
}
