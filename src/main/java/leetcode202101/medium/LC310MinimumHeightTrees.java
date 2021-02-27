package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC310MinimumHeightTrees extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC310MinimumHeightTrees();
    var s = LC.findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}});
    var s2 = LC.findMinHeightTrees(6, new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}});
  }

  /**
   * https://leetcode.com/problems/minimum-height-trees/discuss/76055/Share-some-thoughts
   * Tree: 沒有cycle的 graph, 所以可以形成tree的graph一定是no cycle
   * graph中可以有多個最長路徑, 但他們之間一定共享同組中間點, ((如果不共享, Height & 最長路徑會再延長(不然就不聯通)
   * -   簡單來說
   * -   max length = 奇數 -> 只有1個中間點, 形成 Min Height
   * -   max length = 偶數 -> 只有2個中間點, 形成 Min Height
   * 基本概念就是從leave(degree == 1)出發,過程中就找到parent
   * (不從leave開始找, 就是把leave當作的tree root,一定不會是Min Height, 因為leave當root會造成longest path -> Max height)
   * 每拿到leave就把node size -= 1
   * 把 leave 到 parent 中間的edge拆掉, 這時候parent可能就成為 new leave,
   * 然後再把new leave放到leaves繼續下去找,
   * 直到node size < 2 因為只有中間點的1 or 2 node可以當作root來達成Min Height
   *
   * 所以就是透過 BFS 從leave出發, 過程中邊砍leave 如果剩下的節點少於2, 必然就是root
   * 所以最後成為leaves的就是root(因為一開始一直從leave往上開始減,parent變成leave, 最後root也變成leave)
   * */
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    if (n == 1) return Collections.singletonList(0);
    Set<Integer>[] nodeEdges = new HashSet[n];
    for (int i = 0; i < n; i++) nodeEdges[i] = new HashSet<>();
    for (int[] edge : edges) {
      nodeEdges[edge[0]].add(edge[1]);
      nodeEdges[edge[1]].add(edge[0]);
    }
    List<Integer> leaves = new ArrayList<>();
    for (int i = 0; i < n; i++) if (nodeEdges[i].size() == 1) leaves.add(i);
    while (n > 2) {
      n -= leaves.size();
      List<Integer> newLeaves = new ArrayList<>();
      for (int l : leaves) {
        int parent = nodeEdges[l].iterator().next();
        nodeEdges[parent].remove(l);
        if (nodeEdges[parent].size() == 1) newLeaves.add(parent);
      }
      leaves = newLeaves;
    }
    return leaves;
  }
}
