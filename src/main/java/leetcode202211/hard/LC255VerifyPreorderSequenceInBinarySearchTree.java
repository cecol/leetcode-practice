package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC255VerifyPreorderSequenceInBinarySearchTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC255VerifyPreorderSequenceInBinarySearchTree();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Tree/255.Verify-Preorder-Sequence-in-Binary-Search-Tree
     * 完全忘記可以用 DFS 配上左右邊界來下去找
     * 直接先看 preorder[0] 當 rt, 然後找 left/right 樹邊界, 下去遞迴 left/right 邊界 DFS
     * 這是 O(N log N 解)
     *
     * https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/discuss/68142/Java-O(n)-and-O(1)-extra-space
     * O(N) 解法
     * 關鍵: 右樹一定比前面的任何數字還要大！！
     * 所以這個 stack 記載 monotonic decrease
     * pop when we see right sub tree
     *
     * preorder 從左到右走過去, 每個 preorder[i] 都是一棵樹的 root
     * 把 preorder[i] 當 root 放入到 stack 中
     * 用一個 global variable 記載當前看到的 low_bound, 因為是 左往右 一定都要大於 low_bound
     * 如果 preorder[i] < low_bound 一定不是 BST
     *
     * 但 preorder[i] 可能是在
     * 1. left subtree 過程, 所以抬升 low_bound 得看
     * - while (path.size() > 0 && path.peek() < preorder[i]),
     * - stack top 是目前看到最小的 root, -> monotonic decrease
     * - 只有當 path.peek() < preorder[i] 代表進入 right -> 要開始抬升 low_bound
     * - 不然就只是一直 path.push(preorder[i]) -> left tree stack.top 過程越來越小
     * 2. right subtree 過程
     * - 當 path.peek() < preorder[i] 代表進入 right -> 要開始抬升 low_bound
     * - 下一個 preorder[i+1] 必然大於這個 抬升過後的 low_bound
     */
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> path = new Stack<>();
        for (int p : preorder) {
            if (p < low) return false;
            while (path.size() > 0 && path.peek() < p) low = path.pop();
            path.push(p);
        }
        return true;
    }

    public boolean verifyPreorderDFS(int[] preorder) {
        return dfs(preorder, 0, preorder.length - 1);
    }

    boolean dfs(int[] p, int s, int e) {
        if (e - s <= 1) return true;
        int rt = p[s];
        int i = s + 1;
        while (i <= e && p[i] < rt) i++;
        for (int j = i; j <= e; j++) {
            if (p[j] <= rt) return false;
        }
        return dfs(p, s + 1, i - 1) && dfs(p, i, e);
    }
}
