package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class LC662MaximumWidthOfBinaryTree extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC662MaximumWidthOfBinaryTree();
    var s = LC.widthOfBinaryTree(null);
  }

  /**
   * 原本想說用level traversal去走過每一層找出最寬的那一層
   * 但是exceed time limit
   * https://leetcode.com/problems/maximum-width-of-binary-tree/discuss/106654/JavaC%2B%2B-Very-simple-dfs-solution
   * 上面的原作者解法我是沒有看懂, 他的說法我有明白
   * 後來是看回應裡面投票最高的才看懂 -> 太神奇ＸＤＤＤ
   * 基本上就是用一個list去記錄每一層最左的index
   * 然後一直遞迴下去
   * 每一次睇回到當前node都會知道
   * 1. 當前level
   * 2. 當前node的index
   * 3. 到目前為止記錄到的 每一層最左的node index
   *
   * 遞迴中: if (leftestNodeInEveryLevel.size() == level) leftestNodeInEveryLevel.add(index);
   * 就是說 leftestNodeInEveryLevel.size() == level 這邊是判斷 是否當前的level尚未找到最左的node index
   * 如果是 -> 把當前的idnex加入 -> 為什麼可以確保當前走道的index一定是最左那一個？
   * 因為遞迴是左優先 -> 那麼一定是最左的先走到, 如果沒有, 才是後面右兒子才會走到
   * 所以左優先遞迴確保 遇到leftestNodeInEveryLevel.size() == level這個判斷的node一定是該層做左的node先遇到
   *
   * 然後 index + 1 - leftestNodeInEveryLevel.get(level)
   * 就是拿當前node的index去跟他自己當層的做左計算可以拿到的寬度 ->
   * 如果更大 -> 代表當前node可能是該層是最右
   * 如果更小 -> 代表當前node不是最右
   *
   * 之後就中往下遞迴(要確保先走左兒子  再走右兒子)
   */

  /**
   * 完全忘記原本解法, 但這次直接想到說 bfs, 然後直接改 TreeNode.val 成TreeNode的 index
   * 這樣就可以在 bfs 遊歷時候去往後減, 來得知目前的寬度
   * */
  public int widthOfBinaryTree20221103(TreeNode rt) {
    rt.val = 1;
    Queue<TreeNode> bfs = new LinkedList<>();
    int res = 1;
    bfs.offer(rt);
    while(bfs.size()>0) {
      int s = bfs.size();
      int b = bfs.peek().val;
      for(int i=0;i<s;i++) {
        TreeNode n = bfs.poll();
        res = Math.max(res, n.val - b + 1);
        if(n.left != null) {
          n.left.val = 2*n.val;
          bfs.offer(n.left);
        }
        if(n.right != null) {
          n.right.val = 2*n.val + 1;
          bfs.offer(n.right);
        }
      }
    }
    return res;
  }

  int max = 0;

  public int widthOfBinaryTree(TreeNode root) {
    if (root == null) return 0;
    dfs(root, 0, 1, new ArrayList<>());
    return max;
  }

  private void dfs(TreeNode n, int level, int index, List<Integer> leftestNodeInEveryLevel) {
    if (n == null) return;
    if (leftestNodeInEveryLevel.size() == level) leftestNodeInEveryLevel.add(index);
    max = Math.max(max, index + 1 - leftestNodeInEveryLevel.get(level));
    dfs(n.left, level + 1, index * 2, leftestNodeInEveryLevel);
    dfs(n.right, level + 1, index * 2 + 1, leftestNodeInEveryLevel);
  }
}
