package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC1319NumberOfOperationsToMakeNetworkConnected extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC1319NumberOfOperationsToMakeNetworkConnected();
//    var s = LC.makeConnected(4, new int[][]{{0, 1}, {0, 2}, {1, 2}});
    var s1 = LC.makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {4, 5}});
//    var s2 = LC.makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}});
//    var s3 = LC.makeConnected(5, new int[][]{{0, 1}, {0, 2}, {3, 4}, {2, 3}});
  }

  public int makeConnected(int n, int[][] connections) {
    if (connections.length < n - 1) return -1;
    int[] p = new int[n];
    for (int i = 0; i < n; i++) p[i] = i;
    int s = connections.length;
    for (int[] conn : connections) union(p, conn[0], conn[1]);

    int c = -1;
    for (int i = 0; i < n; i++) if (p[i] == i) c++;
    log.debug("n:{}, conn:{}, c:{}, p:{}", n, connections, c, p);
    return c;
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
