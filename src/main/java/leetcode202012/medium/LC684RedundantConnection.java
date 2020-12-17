package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC684RedundantConnection extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC684RedundantConnection();
    var s = LC.findRedundantConnection(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
  }

  /**
   * great explanation
   * https://leetcode.com/problems/redundant-connection/discuss/123819/Union-Find-with-Explanations-(Java-Python)
   * */
  public int[] findRedundantConnection(int[][] edges) {
    int n = edges.length + 1;
    int[] p = new int[n];
    int[] s = new int[n];
    for (int i = 0; i < n; i++) p[i] = i;
    Arrays.fill(s, 1);
    int[] res = new int[2];
    for (int[] e : edges) {
      union(p, e[0], e[1], s, res);
    }
    return res;
  }

  public int find(int[] p, int i) {
    while (p[i] != i) {
      p[i] = p[p[i]];
      i = p[i];
    }
    return i;
  }

  public void union(int[] p, int i, int j, int[] s, int[] res) {
    int ir = find(p, i);
    int jr = find(p, j);
    if (ir == jr) {
      res[0] = i;
      res[1] = j;
      return;
    }
    if (s[ir] > s[jr]) {
      p[jr] = ir;
      s[ir] += s[jr];
    } else {
      p[ir] = jr;
      s[jr] += s[ir];
    }
  }

}
