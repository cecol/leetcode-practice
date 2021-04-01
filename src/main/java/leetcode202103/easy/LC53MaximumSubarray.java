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
     * 如果遇到負數: nums[i] < 0 -> 但是 curEndMax + nums[i]
     * 1. curEndMax + nums[i] > nums[i] -> 可能是正值(那後面還有希望繼續變大, 只要下一次是正數), 可能是負值但還是 > nums[i] -> 那就先繼續保留
     * 2. curEndMax + nums[i] < nums[i] -> curEndMax 本身就是負, 越加越負, 留著前面的sub array sum已經沒意義了 -> 直接拿nums[i]當成目前的curEndMax
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
