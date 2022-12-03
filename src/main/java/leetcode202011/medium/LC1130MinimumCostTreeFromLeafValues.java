package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.Stack;

public class LC1130MinimumCostTreeFromLeafValues extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1130MinimumCostTreeFromLeafValues();
        var s = LC.mctFromLeafValues(new int[]{6, 2, 4});
    }

    /**
     * 這題有 DP 解 但是是暴力解 O(N^3)
     * 這題真正要問的是 Stack 解,
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/1130.Minimum-Cost-Tree-From-Leaf-Values
     * 基本思維是我們要消掉 arr[i], 就要找一個 arr[j] > arr[i]
     * 兩著相乘會產生一個 cost, arr[j] 上升去 parent tree, arr[i] 就消除了
     * 所以要最低 cost, 就是最小化 arr[j] 然後剛好大於 arr[i]
     * <p>
     * 這樣想好了 arr[] 形成一棵樹, 就是裏面每 element 兩兩相配 小的被刪除 大的升上去跟 parent tree 比較
     * 最後剩下一個最大的 max arr[i]
     * <p>
     * 接著就是找 arr[i] 左右誰最小 且 > arr[i] 的案例
     * 這是 O(N^2) 解
     * 要簡化成 O(N^2) 解就要 Stack
     * 所以就是 維持一個 stack monotonic decrease,
     * 當遇到 nums[i] > stack.top -> 這時候 stack top 就是 那一個剛好被夾在中間的最小
     * 得左右選一個較小的算 cost 然後 刪除他
     * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
     * 一些小細節要注意
     * 1. stack 一開始要先放 Integer.MAX_VALUE, 當作 boundary
     * 2. 最後要看 sk.size() > 2 -> 太多遞減還是要兌現回來
     * 為什麼 sk.size() > 2 ? 不是 sk.size() > 1 ?
     * 因為 sk 最終會留下一個 node 是最大 leave, 兩兩低消完 一定有個最後最大的在那
     */
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Stack<Integer> sk = new Stack<>();
        sk.push(Integer.MAX_VALUE);
        for (int a : arr) {
            while (a >= sk.peek()) {
                int mid = sk.pop();
                res += mid * Math.min(a, sk.peek());
            }
            sk.push(a);
        }
        while(sk.size() > 2) res += sk.pop()* sk.peek();
        return res;
    }

    /**
     * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/474188/I-think-I-able-to-explain-it-to-myself-and-to-you...(Java-DP)-.-Complexity-is-in-the-question
     */
    public int mctFromLeafValuesDP(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int[][] dp = new int[arr.length][arr.length];
        return minSumOfNonLEafNodes(arr, 0, arr.length - 1, dp);
    }

    private int minSumOfNonLEafNodes(int[] arr, int startIndex, int endIndex, int[][] memo) {
        if (startIndex >= endIndex) return 0;
        if (memo[startIndex][endIndex] != 0) return memo[startIndex][endIndex];
        int res = Integer.MAX_VALUE;
        for (int i = startIndex; i < endIndex; i++) {
            int left = minSumOfNonLEafNodes(arr, startIndex, i, memo);
            int right = minSumOfNonLEafNodes(arr, i + 1, endIndex, memo);
            int maxLeft = 0;
            int maxRight = 0;

            for (int j = startIndex; j <= i; j++) maxLeft = Math.max(maxLeft, arr[j]);
            for (int j = i + 1; j <= endIndex; j++) maxRight = Math.max(maxRight, arr[j]);
            int valueOfTheNonLeafNode = maxLeft * maxRight;
            res = Math.min(res, valueOfTheNonLeafNode + left + right);
        }
        memo[startIndex][endIndex] = res;

        return res;
    }
}
