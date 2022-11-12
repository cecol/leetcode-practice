package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1483KthAncestorOfATreeNode extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1483KthAncestorOfATreeNode();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Binary_Search/1483.Kth-Ancestor-of-a-Tree-Node
     * 這題在考 binary lifting 概念
     * 找 k 祖先就是去查 int[] parent k 次, 很直觀
     * 可是如何加速呢? if k == 7, node的7代祖先
     * 假设我们额外知道每个node的2代祖先，记做p[node][1]，那么我们对node的7代祖先只要做4次操作：（7=2^0+2^1+2^1+2^1）
     * 前三次一次跳2格(都查2代), 最後一次跳1格(查1代)
     * 假设我们额外知道每个node的4代祖先，记做p[node][2]，那么我们对node的7代祖先只要做3次操作：（7=2^0+2^1+2^2）
     * 四代查一次, 2代查一次, 1代查一次
     * <p>
     * 到底怎模建立多代祖先關聯？
     * 对于node的k代祖先，只需要将k做二进制分解，有多少个为1的bit，就做多少次該代的query。考虑到 k <= max 5*10^4，最多只需要20次query
     * <p>
     * https://leetcode.com/problems/kth-ancestor-of-a-tree-node/discuss/686362/JavaC%2B%2BPython-Binary-Lifting
     * maxPow 看看 n 多少 取 Log2(10) 就知道最多少代, 就可以建立多代關聯圖
     */
    int[][] jump;
    int maxPow;

    public void TreeAncestor(int n, int[] parent) {
        maxPow = (int) (Math.log(n) / Math.log(2)) + 1;
        jump = new int[maxPow][n];
        jump[0] = parent;
        for (int p = 1; p < maxPow; p++) {
            for (int j = 0; j < n; j++) {
                int pre = jump[p - 1][j];
                jump[p][j] = pre == -1 ? -1 : jump[p - 1][pre];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int maxPow = this.maxPow;
        while (k > 0 && node > -1) {
            if (k >= 1 << maxPow) {
                node = jump[maxPow][node];
                k -= 1 << maxPow;
            } else maxPow--;
        }
        return node;
    }
}
