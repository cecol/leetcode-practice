package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC547FriendCircles extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC547FriendCircles();
    var s = LC.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
  }


  /**
   *  https://leetcode.com/problems/friend-circles/discuss/101336/Java-solution-Union-Find
   */
  public int findCircleNum(int[][] M) {
    int n = M.length;
    if (n == 1) return 1;
    int[] p = new int[n];
    int[] s = new int[n];
    Arrays.fill(s, 1);
    for (int i = 0; i < n; i++) p[i] = i;
    int c = n;
    for (int i = 0; i < n; i++)
      for (int j = i + 1; j < n; j++) {
        if (M[i][j] == 1) c = union(p, s, i, j, c);
      }
    return c;
  }

  // path compression
  public int find(int[] parent, int i) {
    while (i != parent[i]) {
      parent[i] = parent[parent[i]];
      i = parent[i];
    }
    return i;
  }

  public int union(int[] p, int[] s, int i, int j, int count) {
    int ir = find(p, i);
    int jr = find(p, j);
    if (ir == jr) return count;
    // union by size
    if (s[ir] > s[jr]) {
      p[jr] = ir;
      s[ir] += s[jr];
    } else {
      p[ir] = jr;
      s[jr] += s[ir];
    }
    return count - 1;
  }
}
