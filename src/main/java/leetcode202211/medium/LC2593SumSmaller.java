package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC2593SumSmaller extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2593SumSmaller();
        LC.threeSumSmaller(new int[]{-1, 1, -1, -1}, -1);
    }

    /**
     * https://leetcode.com/problems/3sum-smaller/discuss/68817/Simple-and-easy-understanding-O(n2)-JAVA-solution/126365
     * O(N^3) 暴力解會過蠻意外的
     * 但理當用 O(N^2) tow pointer 但我有個問題想不通就是
     * 當固定 i, 然後 left = i + 1, right = n - 1;
     * 如果 nums[i] + nums[left] + nums[right] < target
     * 其實 res += right - left; 為什麼?
     * 因為固定 left 之下, right-- 只會更小, 所以 left to right 區間 right-- 到 left+1 都是合法 < target
     * 所以這時候 可以 left++ 看下一個區間
     *
     * 若 nums[i] + nums[left] + nums[right] >= target
     * 就只能 right--, 代表當前 right 太大, 配誰都會超過
     * */
    public int threeSumSmaller(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    res += right - left;
                    left++;
                } else right--;
            }
        }

        return res;
    }
}
