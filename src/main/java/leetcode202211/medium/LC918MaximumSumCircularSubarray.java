package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC918MaximumSumCircularSubarray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/178422/One-Pass
     * 乍看之下沒有想法, 看到解答才完全裡理解
     * 這是兩題的組合
     * 1. 53. Maximum Subarray
     * 2. 1658 Minimum Operations to Reduce X to Zero
     *
     * 在 circular array 中有兩種 maxSum subarray case
     * 1. 正常的 non-circular array 裏面, 就是出現在正中間, 用一般 kandane 算法來找 max subarray
     * 2. 出現在頭尾 所以反過來說 是 non-circular array 中間找出 minimum sum, 然後 total sum - minimum sum
     *
     * Ex: [1,2,5,-2,-3,5]
     * non-circular Maximum subarray -> [1,2,5]
     * non-circular Minimum subarray -> [-2,-3], totalSum = 8, totalSum - Minimum subarray(5) = 13
     * 這邊有個 tricky 就是 把 array sign 值反轉, 然後找 kandaneMaxSum
     * 得到的值 totalSum + kandaneMaxSum => 會變成 + 是因為我們把 sign 值反轉
     *
     * 真正 circular Maximum 是 [1,2,5] + [5] => 13
     * if(circularSum == 0) return nonCircularSum; 代表裏面全部都是 負數, 那只能取裡面最大值
     */
    public int maxSubarraySumCircular(int[] nums) {
        int nonCircularSum = kandaneMaxSum(nums);
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
            nums[i] = -nums[i];
        }

        int circularSum = totalSum + kandaneMaxSum(nums);
        if(circularSum == 0) return nonCircularSum;
        return Math.max(circularSum, nonCircularSum);
    }

    int kandaneMaxSum(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (currentSum < 0) currentSum = 0;
            currentSum += nums[i];
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
