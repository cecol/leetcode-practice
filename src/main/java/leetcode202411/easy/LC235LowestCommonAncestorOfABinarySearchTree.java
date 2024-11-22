package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;


public class LC235LowestCommonAncestorOfABinarySearchTree extends BasicTemplate {
    public static void main(String[] args) {
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 花了點時間回想
    // 1. Tree 問題很多可能都是遞迴解決 -> 遞迴下去找 有沒有找到 p, q
    // 2. rt 中獎 p or q, 就可以回傳
    // 2. 左右兒子下去遞迴找, 左兒子沒中, 代表在右兒子, 右兒子沒中, 代表在左兒子, 左右都有找到 -> 代表當前 Rt 才是共同爸爸
    public TreeNode lowestCommonAncestor(TreeNode rt, TreeNode p, TreeNode q) {
        if(rt == null) return null;
        if(rt == p || rt == q) return rt;
        TreeNode lf = lowestCommonAncestor(rt.left, p, q);
        TreeNode rf = lowestCommonAncestor(rt.right, p, q);
        return lf == null ? rf : rf == null ? lf : rt;
    }
}
