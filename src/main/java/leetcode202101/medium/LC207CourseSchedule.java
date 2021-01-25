package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC207CourseSchedule extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC207CourseSchedule();
    var s = LC.canFinish(2, new int[][]{{1, 0}, {0, 1}});
  }

  /**
   * https://leetcode.com/problems/course-schedule/discuss/162743/JavaC%2B%2BPython-BFS-Topological-Sorting-O(N-%2B-E)
   * Topological sort 配BFS解
   * wiki: https://en.wikipedia.org/wiki/Topological_sorting
   * Topological sort 主要就是來找出圖是否是有向圖是否形成cycle, 沒有的話就是DAG, Directed-acyclic-graph
   * 置重點在找出有沒有cycle
   * 經典解法就是 Kahn's algorithm - 也是BFS概念
   * 先找出所有沒有income edge的nodes -> put in list S
   * List L 一開始是空的, 用來搜集走過的node -> sorted elements
   * - while S is not empty
   * -   從S拿出一個點n (一定沒有in edge)
   * -   把n加入 L
   * -   取出所有 n -> m (找出所有 out edge)
   * -     把 out edge: n -> m 從graph中刪除 (對m來說是刪除m的in edge)
   * -     如果m沒有其他in edge -> 把m加入S
   * - 如果graph還有edge -> 一定有cycle
   * - 或者回傳 L 就是 Topological sorted(因為一開始就是no in edge node在裡面, 然後以他們為起點開始找下一個點)
   * <p>
   * 問題實作, BFS概念實作
   * int[] inDegree 就是紀錄每個點 in degree, in edge個數
   * ArrayList<Integer>[] outEdge 就是記載每個點有哪些out edge到哪些點 -> 就是上面演算法中 '取出所有 n -> m (找出所有 out edge)'
   * 所以要先從prerequisites建立 inDegree & outEdge
   * 掃一遍inDegree, 誰是0 誰就是初始點, 加入List<Integer> bfsNoInEdge -> 就是上面演算法的S
   * 但這邊用List 而非queue, 因為這邊不紀錄List L -> topological sorted結果(因為結果只是要有無cycle)
   * 所以 bfsNoInEdge 要一直增加走到的且變成 no in edge的node, 如果最後bfsNoInEdge的size == numCourses, 代表有走完
   * (如果bfsNoInEdge要用queue, 我覺得就要紀錄List L, 來看看最後 List L的size == numCourses, 判定有無有走完)
   * 開始進入bfs裡面往下拓樸
   * <p>
   * <p>
   * 其實簡單想 -> 如果有cycle -> 一定有下面的點是無法刪成 no income edge ->
   * -----------------> 導致走到cycle那一步找不到點繼續走下去, 就出回圈了, 最後走過的點的個數一定不會是整張圖
   * ----------> 如果一定沒有cycle -> 從所有起點開始走邊走邊刪下一個點的 income edge
   * -----------------> 一定會有下面的點變成 no income edge 可以繼續走下去
   * <p>
   * 其實關鍵是要趕快想到要把 int[][] prerequisites
   * 轉換成
   * ArrayList<Integer>[] outEdge = new ArrayList[numCourses];
   * int[] inDegree = new int[numCourses];
   * List<Integer> bfsNoInEdge = new ArrayList<>();
   * 就可以很快解出來
   */
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    ArrayList<Integer>[] outEdge = new ArrayList[numCourses];
    int[] inDegree = new int[numCourses];
    List<Integer> bfsNoInEdge = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) outEdge[i] = new ArrayList<>();
    for (int[] e : prerequisites) {
      outEdge[e[1]].add(e[0]);
      inDegree[e[0]]++;
    }
    for (int i = 0; i < numCourses; i++) if (inDegree[i] == 0) bfsNoInEdge.add(i);
    for (int i = 0; i < bfsNoInEdge.size(); i++) {
      for (int m : outEdge[bfsNoInEdge.get(i)]) {
        if (--inDegree[m] == 0) bfsNoInEdge.add(m);
      }
    }
    return bfsNoInEdge.size() == numCourses;
  }
}
