package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC53MaximumSubarray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC53MaximumSubarray();
        LC.maxSubArray(new int[]{-2, 1});
    }

    /**
     * O(N^2)是可以解, 但O(N) 想不到怎解
     * O(N) 是個演算法
     * https://leetcode.com/problems/maximum-subarray/discuss/20211/Accepted-O(n)-solution-in-java
     * Jon Bentley (Sep. 1984 Vol. 27 No. 9 Communications of the ACM P885)
     * 基本概念就是要處理'負數', 基本概念就是記住到目前 i 為止的加總 max
     * 因為如果是正數就一直加下去
     * 如果遇到負數, 但加nums[i]下去
     * 1. 雖然變小, 但可能接下來還可以用 -> 所以繼續保留
     * 2. 如果nums[i]下去變更小, 那就reset curEndMax = nums[i], 從新計算
     * 反正每次算完當前都會記下 res = Math.max(res, curEndMax);
     * 就算後面的沒比較大, 前面大的也都遇過了
     * 所以關鍵在於, 如果累加nums[i] 變更小, 不如就由 nums[i]從新開始計算, 反正也不會更大了
     */
    public int maxSubArray(int[] nums) {
        int res = nums[0], curEndMax = nums[0];
        for (int i =1;i<nums.length;i++) {
            int n = nums[i];
            curEndMax = Math.max(n, curEndMax + n);
            res = Math.max(res, curEndMax);
        }
        return res;
    }
}
