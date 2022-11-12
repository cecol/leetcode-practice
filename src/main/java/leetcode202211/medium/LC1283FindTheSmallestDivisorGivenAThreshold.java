package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC1283FindTheSmallestDivisorGivenAThreshold extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }
    /**
     * 基礎概念有想出來
     * https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
     * 下界 = 1
     * 上界 = max in nums
     *
     * 然後 binary search 去找 1 to max 哪一個符合
     * 每一個 元素/mid 的加總 <= threshold
     *
     * 但是因為他的商數要進位, 這邊我一直沒找到正確算法直到我看到
     * (d + n) / d -> 把被除數先加上除數再來除
     * Ex: d = 3, n = 2, 2/3=0 但他要的是 1
     * 所以 2+3/3 = 1, 都自動幫你進位
     * */
    public int smallestDivisor(int[] nums, int threshold) {
        int max = nums[0];
        for (int n : nums) max = Math.max(max, n);
        int l = 1, r = max;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (canD(nums, threshold, m)) r = m;
            else l = m + 1;
        }
        return l;
    }

    boolean canD(int[] nums, int threshold, int d) {
        int s = 0;
        for (int n : nums) {
            s += (d + n) / d;
            if (s > threshold) return false;
        }
        return true;
    }
}
