package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1300SumOfMutatedArrayClosestToTarget extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1300SumOfMutatedArrayClosestToTarget();
    }

    /**
     * 我想太複雜了,
     * https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/discuss/463268/JavaC%2B%2B-4ms-binary-search-short-readable-code-%2B-sorting-solution
     * 基本上是 1 to arr max value 之間來挑, 看誰算完接近 target
     * 如果挑到的 mid , 算出來 > target => 還要再往左走, 才可以使 sum 更低 接近 target
     * 如果挑到的 mid , 算出來 < target => 還要再往右走, 才可以使 sum 更高 接近 target
     *
     * corner case: if target > sum of array => 代表不能在減少 sum, 所以直接回傳最大 value
     * 找到 l 後驗證
     * l or l-1 誰才更靠近 target, 如果 sum l - 1 <= sum of l => 回傳 l-1
     */
    public int findBestValue(int[] arr, int target) {
        int l = 0, r = 0, sum = 0;
        for (int n : arr) {
            sum += n;
            r = Math.max(r, n);
        }

        if (sum <= target) return r;

        while (l < r) {
            int mid = l + (r - l) / 2;
            int s = 0;
            for (int a : arr) s += Math.min(a, mid);
            if (s >= target) r = mid;
            else l = mid + 1;
        }

        int s1 = 0, s2 = 0;
        for (int a : arr) {
            s1 += Math.min(a, l);
            s2 += Math.min(a, l - 1);
        }

        return Math.abs(s2 - target) <= Math.abs(s1 - target) ? l - 1 : l;
    }

}
