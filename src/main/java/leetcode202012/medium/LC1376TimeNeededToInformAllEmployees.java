package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1376TimeNeededToInformAllEmployees extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC1376TimeNeededToInformAllEmployees();
    LC.numOfMinutes(1, 0, new int[]{-1}, new int[]{0});
    LC.numOfMinutes(6, 2, new int[]{2, 2, -1, 2, 2, 2}, new int[]{0, 0, 1, 0, 0, 0});
    LC.numOfMinutes(7, 6, new int[]{1, 2, 3, 4, 5, 6, -1}, new int[]{0, 6, 5, 4, 3, 2, 1});

  }

  /**
   * https://leetcode.com/problems/time-needed-to-inform-all-employees/discuss/532560/JavaC%2B%2BPython-DFS
   */
  public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
    // Superordinate -> subordinates
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int subordinates = 0; subordinates < n; subordinates++) {
      int superordinate = manager[subordinates];
      if (!graph.containsKey(superordinate)) graph.put(superordinate, new ArrayList<>());
      graph.get(superordinate).add(subordinates);
    }
    return dfs(graph, informTime, headID);
  }

  public int dfs(Map<Integer, List<Integer>> graph, int[] informTime, int superordinate) {
    int max = 0;
    if (!graph.containsKey(superordinate)) return 0;
    for (int subordinatesIndex = 0; subordinatesIndex < graph.get(superordinate).size(); subordinatesIndex++) {
      // find max time among all subordinates
      max = Math.max(max, dfs(graph, informTime, graph.get(superordinate).get(subordinatesIndex)));
    }
    return max + informTime[superordinate];
  }


}
