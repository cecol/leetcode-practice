package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC1339MaximumProductOfSplittedBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/discuss/496549/JavaC%2B%2BPython-Easy-and-Concise
     * 蠻直觀的一題  遞迴走兩便樹
     * 1. 取得 total Sum
     * 2. 算 sub tree sum, 看 (total sum - (sub tree sum)) * (sub tree sum) 是否為最大
     * 只是陷阱在 mod 不能太早做, 答案會錯, 得用 long 紀錄 然後後最後 mod
     */
    int sum = 0;
    long maxSum = 0;
    long mod = (int) 1e9 + 7;

    public int maxProduct(TreeNode rt) {
        sumUp(rt);
        subSum(rt);
        return (int) (maxSum % mod);
    }

    long subSum(TreeNode rt) {
        if (rt == null) return 0;
        long subSum = rt.val + subSum(rt.left) + subSum(rt.right);
        if (subSum * (sum - subSum) > maxSum) maxSum = subSum * (sum - subSum);
        return subSum;
    }

    void sumUp(TreeNode rt) {
        if (rt == null) return;
        sum += rt.val;
        sumUp(rt.left);
        sumUp(rt.right);
    }
}
