package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC785IsGraphBipartite extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC785IsGraphBipartite();
    var s = LC.isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}});
    log.debug("{}", s);
  }

  /**
   * 類似 LC886, 就是DFS + color painting, paint 1 or paint -1
   * 如果過程中要paint的對象已被paint過, 且跟要paint的color不同 -> false, 無法分兩邊(無法分兩種顏色)
   * */
  public boolean isBipartite(int[][] graph) {
    int n = graph.length;
    int[] colors = new int[n];
    for (int i = 0; i < n; i++) if (colors[i] == 0 && !paintDfs(i, 1, colors, graph)) return false;
    return true;
  }

  private boolean paintDfs(int v, int paintC, int[] colors, int[][] graph) {
    if (colors[v] != 0) return colors[v] == paintC;
    colors[v] = paintC;
    for (int u : graph[v]) {
      if (!paintDfs(u, -paintC, colors, graph)) return false;
    }
    return true;
  }
}
