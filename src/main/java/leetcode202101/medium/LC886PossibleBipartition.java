package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class LC886PossibleBipartition extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC886PossibleBipartition();
    var s = LC.possibleBipartition(4, new int[][]{{1, 2}, {1, 3}, {2, 4}});
    var s1 = LC.possibleBipartition(3, new int[][]{{1, 2}, {1, 3}, {2, 3}});
    var s2 = LC.possibleBipartition(5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}});
  }

  /**
   * https://leetcode.com/problems/possible-bipartition/discuss/158957/Java-DFS-solution
   * 分邊 & 填色彩問題
   * 先建立一個 boolean[][] g 表達這兩者有無互相dislike關係, true -> dislike(edge), false -> 沒有dislike關聯(no edge)
   * g[d[0] - 1][d[1] - 1] = true;
   * g[d[1] - 1][d[0] - 1] = true;
   * dislike是互相的, 所以兩邊都要設定有關聯 -> 無向圖
   * 建立一個 int[] colors = new int[N]; 表示每個點顏色 -> 裡面只會有
   * 1. 0 -> 還沒被分邊(還沒有顏色), 要DFS下去分邊
   * 2. -1 -> 被分到左邊(or填上藍色)
   * 3. 1 -> 被分到右邊(or填上紅色)
   * 下去DFS, DFS是根據boolean[][] g的關聯下去遞迴
   *
   */
  public boolean possibleBipartition(int N, int[][] dislikes) {
    boolean[][] dislikeGraph = new boolean[N][N];
    for (int[] d : dislikes) {
      dislikeGraph[d[0] - 1][d[1] - 1] = true;
      dislikeGraph[d[1] - 1][d[0] - 1] = true;
    }
    int[] colors = new int[N];
    for (int i = 0; i < N; i++)
      if (colors[i] == 0 && !paint(i, 1, colors, dislikeGraph)) return false;
    return true;
  }

  private boolean paint(int i, int paintColor, int[] colors, boolean[][] dislikeGraph) {
    if (colors[i] != 0) return colors[i] == paintColor;
    colors[i] = paintColor;
    for (int v = 0; v < colors.length; v++) {
      if (dislikeGraph[i][v] && !paint(v, -paintColor, colors, dislikeGraph)) return false;
    }
    return true;
  }
}
