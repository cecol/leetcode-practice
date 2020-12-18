package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC947MostStonesRemovedWithSameRowOrColumn extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC947MostStonesRemovedWithSameRowOrColumn();
    var s = LC.removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}});
    var s1 = LC.removeStones(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}});
    var s2 = LC.removeStones(new int[][]{{0, 0}});
    var s3 = LC.removeStones(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}});
    var s4 = LC.removeStones(new int[][]{{0, 1}, {1, 0}, {1, 1}});
  }

  /**
   * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/discuss/197693/Java-Union-Find
   */
  public int removeStones(int[][] stones) {
    int n = stones.length;
    int[] p = new int[n];
    for (int i = 0; i < n; i++) {
      p[i] = i;
    }
    int c = 0;
    for (int i = 0; i < n; i++)
      for (int j = i + 1; j < n; j++) {
        if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
          union(p, i, j);
        }
      }
    for (int i = 0; i < n; i++) if (p[i] == i) c++;
    log.debug("{}", n - c);
    return n - c;
  }

  public int find(int[] p, int i) {
    while (i != p[i]) {
      i = p[i];
    }
    return i;
  }

  public void union(int[] p, int i, int j) {
    int ir = find(p, i);
    int jr = find(p, j);
    if (ir == jr) return;
    p[jr] = ir;
  }
}
