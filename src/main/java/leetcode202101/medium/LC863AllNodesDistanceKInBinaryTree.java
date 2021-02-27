package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class LC863AllNodesDistanceKInBinaryTree extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC863AllNodesDistanceKInBinaryTree();
    LC.distanceK(null, null, 0);
  }

  /**
   * 只用來記錄 root -> target 中間路徑的所有是 target 的父輩or祖父輩到target的路徑
   * 所以其他不是位在root -> target 的node都不會在裡面
   * */
  Map<TreeNode, Integer> toTarget = new HashMap<>();

  /**
   * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143798/1ms-beat-100-simple-Java-dfs-with(without)-hashmap-including-explanation
   * 先建立個Map -> 紀錄其他點到 target的距離
   * find() 算出 root -> target的path 其中所有點到target的距離
   * dfs -> 從root開始遞迴下去, 也帶入剛剛算好的 root->target的距離
   * -> 就是說如果dfs中帶下來的 int length == K 就代表該點確實是其中一個答案
   * 然後dfs左右子樹下去
   *
   * 這題有個關鍵是, 要先釐清只有兩種情境
   * 1. target 的子樹,子子樹的節點到target的距離
   * ->   這個用dfs解, 不用特別存路徑長度, 只要過程中把距離待下去, 然後看看是否是要求的K就好
   * 2. root -> target 途中的target 父輩, 祖父輩到target的情境
   * ->   這個得先算過把它存成map, 當dfs走到時候可以拿來出用
   * ->   會有情境2是因為 target 是在樹中的任意一個點, 而我們也只能從root走下去, 所以如果dfs是(node.left + 1)
   * ->   從root開始中間走到的 parent再到target 就不能用 root.left + 1
   * ->   而如果是中間岔開的node(不是target的祖父輩), 自然一定得經過root, 就得dfs(node.left + 1)
   * 解答中有個論述是
   * node -> target 如果距離剛好是K -> 那node的 child -> target應該是k+1
   * 除非node的 child 離target更近 -> 這樣既代表 target 是node的兒子
   * 這時候node其實是 target的父輩or祖父(總之是上層)
   *
   * 所以才要先把 root -> target之間的path透過 先算出來 -> 因為這樣就先算出所有 target的祖父輩到target的距離, 這樣dfs下去時候
   * 就可以單純的
   * dfs(root.left, K, length + 1, res);
   * dfs(root.right, K, length + 1, res);
   * 因為遞迴帶下去的 length + 1, 如果遇到是root-> parent -> target中間過程的parent
   * 因為parent已算算過了 -> 所以不會用root遞迴下來的length + 1來看
   * -> 而是拿先算過的 if (pathToTarget.containsKey(cur)) length = pathToTarget.get(cur);
   * if (pathToTarget.containsKey(cur)) length = pathToTarget.get(cur); -> 這邊就是在解釋如果當前node是target的parent or 祖父輩
   * 那麼就要用當時算的俱來在遞迴下去找其他child到target的距離, 而child就會是,當前的length + 1
   *
   * 總結來說
   * 直觀上 當前node的child到target的距離應該是 當前node到target距離+1
   * -> 可是如果target是node的child -> 當前node的child到target可能不見得就是：當前node到target距離+1
   * -> 所以得先建立好root -> target 的中間每個點到target的距離, 中間都是target的父輩或者祖父輩
   * 然後dfs就用child+1來找, 但會配上述資訊來特別處理target是node的child的case
   *
   * 我這題困擾就是不會好好處理 任何是target的parent到target的case
   * 重點就是先把他們找出來, 剩下都是child+1的情境了
   */
  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
    List<Integer> res = new ArrayList<>();
    find(root, target);
    dfs(root, K, toTarget.get(root), res);
    return res;
  }

  private void find(TreeNode root, TreeNode target) {
    if (root == null) return;
    if (root == target) {
      toTarget.put(root, 0);
      return;
    }
    find(root.left, target);
    if (toTarget.containsKey(root.left)) {
      toTarget.put(root, toTarget.get(root.left) + 1);
      return;
    }
    find(root.right, target);
    if (toTarget.containsKey(root.right)) {
      toTarget.put(root, toTarget.get(root.right) + 1);
      return;
    }
  }

  private void dfs(TreeNode cur, int K, int length, List<Integer> res) {
    if (cur == null) return;
    if (toTarget.containsKey(cur)) length = toTarget.get(cur);
    if (length == K) res.add(cur.val);
    dfs(cur.left, K, length + 1, res);
    dfs(cur.right, K, length + 1, res);
  }
}
