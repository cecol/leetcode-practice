package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC410SplitArrayLargestSum extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/split-array-largest-sum/discuss/89817/Clear-Explanation%3A-8ms-Binary-Search-Java
     * 要找出 nums 切成 k 段後, 每一段的 sum 要最小,
     * sum 的區間是 max_value_of_nums to sum_of_nums
     * max_value_of_nums -> 就是 sub array len == 1, 每個元素自己就是 sub array
     * sum_of_nums -> 就是整個 nums 當成一個 sub array
     * 雖然上述不見得符合 k 定義(因為沒切成 k 個 sub array)
     * 但是廣義上所有可能 k 的最大邊際
     *
     * 要在這 max_value_of_nums to sum_of_nums 做 binary search,
     * l = max_value_of_nums
     * r = sum_of_nums,
     * 切出 mid, 然後拿去 valid(nums) 算看看, 是不是真的符合 k 且都小於 mid
     *
     * valid 裏面
     * targetMax 就是 試試看 每個 subarray 頂多加到它
     * count, 就是每 subarray, 就 count++, 代表我們找到一個 subarray sum <= targetMax
     * count > k, 切超過 k 別想了 return false -> 代表這個 mid 太小 mid 往右走
     * count <= k, 這個 mid 有機會, 在小一點 sub array更多, 才會剛好切到接近 k 為極限 -> mid 往左走
     * */
    public int splitArray(int[] nums, int k) {
        int max = 0;
        long sum = 0;
        for (int n : nums) {
            max = Math.max(max, n);
            sum += n;
        }
        if (k == 1) return (int) sum;
        if (k == nums.length) return max;

        long l = max, r = sum;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (valid(mid, nums, k)) {
                r = mid - 1;
            } else l = mid + 1;
        }
        return (int) l;
    }

    boolean valid(long targetMax, int[] nums, int k) {
        int count = 1;
        long total = 0;
        for (int n : nums) {
            total += n;
            if (total > targetMax) {
                total = n;
                count++;
                if (count > k) return false;
            }
        }
        return true;
    }
}
