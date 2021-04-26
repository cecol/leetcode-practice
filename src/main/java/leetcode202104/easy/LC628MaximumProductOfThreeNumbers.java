package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC628MaximumProductOfThreeNumbers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC628MaximumProductOfThreeNumbers();
    }

    /**
     * 我有解出來, 只是透過sort, 再來找出最大乘法的3個, 效果走沒有很差, faster than 63.87% of Java
     * 但我其實知道是要找
     * 1. 最大的前三個互乘 or
     * 2. 最小的兩個配一個最大的互乘
     * 只是我沒有辦法好好找到， 應該就是找 m1,m2,m3,m4,m5 的邏輯有比較tricky
     * 找
     * 1. 最大的前三個互乘 -> initial with Integer.MIN_VALUE
     * 2. 最小的兩個配一個最大的互乘 -> initial with Integer.MAX_VALUE
     * 我就是 initial case 沒辦法搞清楚
     */
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE,
                max2 = Integer.MIN_VALUE,
                max3 = Integer.MIN_VALUE,
                min1 = Integer.MAX_VALUE,
                min2 = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }

            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

    public int maximumProductMine(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[n - 1] * nums[n - 2] * nums[n - 3], nums[n - 1] * nums[0] * nums[1]);
    }
}
