package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC1569NumberOfWaysToReorderArrayToGetSameBST extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1569NumberOfWaysToReorderArrayToGetSameBST();
    }

    /**
     * https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/discuss/819725/Java-Clean-code-uses-Yang-Hui'sPascal's-Triangle-With-Explanation
     * 這題真的有程度的難
     * 其實有個 pattern 可以觀察出來
     * 只要 BST: [3,6,4,1] , left: [1], right: [6,4]
     * 只要 right 6 在 ４ 之前就好, 怎麼樣排列組合都會是一樣的 BST
     * [6,4,1], [6,1,4], [1,6,4] 都是合法的
     * 這個排列組合是 3C1 which is 3 (C 3 取 1)
     *
     * 更複雜一點 [3,6,4,1,7], right: [6,4,7]
     * right 中 6 一定要在 4, 7 前面, 但 [4,7] 可以對調, 所以 right 又有自己的 BST 組合 (暗示再下去 dfs)
     *
     * 所以除了當前 BST 的 (C 幾 取 幾)  還有再下去 left/right 樹排列組合下去 再一次 (C 幾 取 幾)
     *
     * 講到 C 幾取幾, 是有個公式: Pascle triangle or Yang Hui 楊輝三角形
     * https://zh.wikipedia.org/wiki/%E6%9D%A8%E8%BE%89%E4%B8%89%E8%A7%92%E5%BD%A2
     * Pascle triangle or Yang Hui 楊輝三角形 第 n 行的第 k 個數組合數為 (n-1)C(k-1) -> C n-1 取 k-1
     *
     * 1. 所以先把整個 nums 所有可能的 最大 Yang Hui triangle 算出來, 後續算 BST 子樹可能性需要
     * 2. dfs 下去所有子樹可能
     * - dfs 取出第一個 root, 找出剩下 left, right
     * - return combination(len(left+right), len(left)) * dfs(left) * dfs(right)
     * - combination(len(left+right), len(left)) = 當前 BST 的 (C 幾 取 幾)
     * - * dfs(left) * dfs(right) -> left/right 樹排列組合下去 再一次 (C 幾 取 幾)
     * 3. https://zh.wikipedia.org/wiki/%E6%9D%A8%E8%BE%89%E4%B8%89%E8%A7%92%E5%BD%A2#/media/File:Fibonacci_in_Pascal_triangle.png
     * Yang Hui triangle 算法
     * */
    long mod = (long) 1e9 + 7;

    public int numOfWays(int[] nums) {
        int len = nums.length;
        List<Integer> arr = new ArrayList<>();
        for (int n : nums) arr.add(n);
        return (int) getCombs(arr, getTriangle(len + 1)) - 1;
    }

    long getCombs(List<Integer> nums, long[][] combs) {
        if (nums.size() <= 2) return 1;
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int n : nums) {
            if (n < root) left.add(n);
            else if (n > root) right.add(n);
        }

        return (combs[left.size() + right.size()][left.size()] * (getCombs(left, combs) % mod)) % mod *
                getCombs(right, combs) % mod;
    }

    long[][] getTriangle(int n) {
        long[][] triangle = new long[n][n];
        for (int i = 0; i < n; i++) {
            triangle[i][0] = triangle[i][i] = 1;
        }
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                triangle[i][j] = (triangle[i - 1][j] + triangle[i - 1][j - 1]) % mod;
            }
        }
        return triangle;
    }
}
