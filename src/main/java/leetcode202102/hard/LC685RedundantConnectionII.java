package leetcode202102.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC685RedundantConnectionII extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC685RedundantConnectionII();
    var s = LC.findRedundantDirectedConnection(null);
  }


  /**
   * 有解過684無向圖版本  但這題思路比較複雜  想了想還是參考別人的想法
   * 這個中文解釋很清楚 但沒採用這個解法
   * https://leetcode.com/problems/redundant-connection-ii/discuss/278105/topic
   * 這個回應裡面的第一個解法比較理解 所以採用
   * https://leetcode.com/problems/redundant-connection-ii/discuss/108045/C%2B%2BJava-Union-Find-with-explanation-O(n)
   *
   * */
  public int[] findRedundantDirectedConnection(int[][] edges) {

  }
}
