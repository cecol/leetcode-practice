package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.Employee;

import java.util.*;

public class LC997FindTheTownJudge extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC997FindTheTownJudge();
    var s = LC.findJudge(4, new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}});
  }

  /**
   * 這題是很簡單, 也很多解法, 但正確的思路應該是
   * 如果有judge -> 他的in edge一定等於 N-1
   * 所以去算 in edge就好
   * (去計算每個點的in edge count, 如果該點也有 out edge, --in edge, 因為法官不會有 out edge, 所以最終應該是 N-1)
   * */
  public int findJudge(int N, int[][] trust) {
    int[] count = new int[N+1];
    for (int[] t: trust) {
      count[t[0]]--;
      count[t[1]]++;
    }
    for (int i = 1; i <= N; ++i) {
      if (count[i] == N - 1) return i;
    }
    return -1;
  }

  /**
   * 可以過 但其實想歪了, 很差解法
   * */
  public int findJudgeOld(int N, int[][] trust) {
    Map<Integer, Set<Integer>> t = new HashMap<>();
    for (int i = 1; i <= N; i++) t.put(i, new HashSet<>());
    for (int[] tt : trust) {
      t.get(tt[0]).add(tt[1]);
    }
    int res = -1;
    for (int i = 1; i <= N; i++)
      if (t.get(i).size() == 0) {
        res = i;
        break;
      }
    if (res != -1) {
      for (int i = 1; i <= N; i++)
        if (i != res && !t.get(i).contains(res)) return -1;
    }
    return res;
  }

}
