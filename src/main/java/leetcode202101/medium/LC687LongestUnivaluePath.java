package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC687LongestUnivaluePath extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC687LongestUnivaluePath();
    LC.longestUnivaluePath(null);
  }

  /**
   * https://www.youtube.com/watch?v=yX1hVhcHcH8&ab_channel=HuaHua
   * 這題是看花花醬才理解的
   * 最長同值的路徑同應該是包含某一點的root, 但不見得會是包含當前該點
   * 所以多做一個遞迴 -> univaluePath(TreeNode root, int[] max)
   * 裡面的 int[] max 是記載目前看到的 longestUnivaluePath
   * 而 univaluePath是回傳包含TreeNode root所能得到的最長路徑(該最長路徑是左兒子or右兒子擇一條)
   * 所以如果TreeNode root跟左右子都不一樣 -> 回傳一定是0
   * <p>
   * 所謂的longestUnivaluePath 就是要馬
   * 1. 包含root
   * -> 單純只看左子樹or右子樹最長(左or右子樹各自子樹去走出來)
   * or
   * -> 看左子樹or右子樹配上各自其一最長子樹 -> 不可能取子樹的兩邊子樹路徑 -> 再配上root就會產生分岔path
   * 2. 不包含root
   * -> 單純只看左子樹or右子樹最長
   */
  public int longestUnivaluePath(TreeNode root) {
    if (root == null) return 0;
    int[] res = new int[1];
    univaluePath(root, res);
    return res[0];
  }

  private int univaluePath(TreeNode root, int[] max) {
    if (root == null) return 0;
    int l = univaluePath(root.left, max);
    int r = univaluePath(root.right, max);
    int pl = 0;
    int pr = 0;
    if (root.left != null && root.left.val == root.val) pl = l + 1;
    if (root.right != null && root.right.val == root.val) pr = r + 1;
    max[0] = Math.max(max[0], pl + pr);
    return Math.max(pl, pr);
  }

  /**
   * 關於上面的標準解答, 我理解完之後大概可以知道其實遞迴要回的應該是要兩個值
   * 1. 子樹且其任一子子樹最長(含子樹自己root)
   * 2. 最佳解(可能是包含自己或者不包含自己)
   * ->  如果root跟子樹值不一樣 -> 一定不包含自己
   * ->  如果root跟子樹值一樣
   * ->     不包含自己代表子子樹左右都考慮path會比包含自己的path還要長
   * ->     包含自己代表包含自己的配上某一子子樹的path會大於任兩條子子樹path
   * 但最終一定是某一子樹的(or子孫樹)root會被包含在最長path
   * 上面的解法是把當前最佳解放在 int[] max or global value
   * 然後univaluePath回傳是包含TreeNode root的最長path(且也只包含某一邊子樹)
   * 真正解答是最佳解-> int[] max or global value
   * univaluePath回傳只是過程中幫助運算
   *
   * 這是因為找出longestUnivaluePath有可能是包含當前root or 不包含卻是左右子樹其一最長
   * 所以只要遞迴回傳這兩者資訊就可以知道最佳解
   *
   * 所以在算1.的時候 -> 要先檢查自己跟子樹是否值依樣 -> 如果一樣  才有機會再延長
   * 如果不一樣 就算子樹很長 -> 也都是0
   * 所以就是root.val跟left.val一樣 -> left single path + 1 or -> 0
   * 所以就是root.val跟right.val一樣 -> right single path + 1 or -> 0
   * max = Math.max(pl + pr, Math.max(l[1], r[1]));
   * pl + pr => 包含 root
   * Math.max(l[1], r[1]) -> 任一子樹各自max
   *
   * 所以univaluePath2回傳的int[]中
   * int[0] -> 子樹配上其一子子樹最長path
   * int[1] -> 子樹max path(可能是 子樹配上其1子子樹最長path or 子樹配上其2子子樹最長path or 子子樹自己最長(當子樹根子子樹值不一樣))
   * */
  public int longestUnivaluePath2(TreeNode root) {
    if (root == null) return 0;
    int[] res = univaluePath2(root);
    return res[1];
  }

  private int[] univaluePath2(TreeNode root) {
    if (root == null) return new int[]{0, 0};
    int[] l = univaluePath2(root.left);
    int[] r = univaluePath2(root.right);
    int pl = 0;
    int pr = 0;
    if (root.left != null && root.left.val == root.val) pl = l[0] + 1;
    if (root.right != null && root.right.val == root.val) pr = r[0] + 1;
    int max = Math.max(pl + pr, Math.max(l[1], r[1]));
    return new int[]{Math.max(pl, pr), max};
  }
}
