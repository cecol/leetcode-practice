package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;
import java.util.HashMap;

public class
LC2458HeightOfBinaryTreeAfterSubtreeRemovalQueries extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2458HeightOfBinaryTreeAfterSubtreeRemovalQueries();
    }

    HashMap<Integer, Integer> cache;
    int[] res;
    /**
     * https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/discuss/2762494/PythonC%2B%2BJavaRust-code-as-explanation-%2B-BONUS-(with-detailed-comments)
     *
     * 這題有三個數值要記載
     * 1. 該 node sub tree 數高
     * 2. root 到該 node 的 depth
     *
     * 所以如果移除該 node sub tree 接下來樹高只有兩種可能
     * 1. parent depth -> 因為 parent 沒有其他 sub tree
     * 2. 另一個兄弟的樹高 + parent depth
     *
     * 所以直接 dfs 下去帶上
     * 1. 當前該 node 要被移除
     * 2. root to node 的 depth
     * 3. 該 node 要被移除後的樹高 heightAfterRemove
     * - Ex: node 是上一層 parent.left
     * - heightAfterRemove 是來自上一層的 Math.max(heightAfterRemove, d + h(rt.right)));
     * - 1. heightAfterRemove 來是上一層 dfs 的 parent 移除時候樹高
     * - 2. parent.left 子樹被移除 但另一邊子樹樹高還在加上深度 - d + h(rt.right)
     *
     * 多一個 cache 紀錄算樹高過程
     * */
    public int[] treeQueries(TreeNode root, int[] queries) {
        cache = new HashMap<>();
        res = new int[100001];

        dfs(root, 0, 0);
        return Arrays.stream(queries).map(q -> res[q]).toArray();
    }

    void dfs(TreeNode rt, int d, int heightAfterRemove) {
        if (rt == null) return;
        res[rt.val] = heightAfterRemove;
        dfs(rt.left, d + 1, Math.max(heightAfterRemove, d + h(rt.right)));
        dfs(rt.right, d + 1, Math.max(heightAfterRemove, d + h(rt.left)));
    }

    int h(TreeNode rt) {
        if (rt == null) return 0;
        if (!cache.containsKey(rt.val)) cache.put(rt.val, 1 + Math.max(h(rt.left), h(rt.right)));
        return cache.get(rt.val);
    }
}
