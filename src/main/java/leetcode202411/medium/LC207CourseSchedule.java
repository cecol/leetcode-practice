package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC207CourseSchedule extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 有想到 in/out 但還是太生疏, 沒選對關鍵 資料結構
    // 1. 用 ArrayList<Integer>[] outEdge 表達 課程完成 可以去檢查的 需求課程
    // 2. 用 int[] inDegree = new int[numCourses]; 只要用總數去檢查需求課程的 counter 即可, 不用用什麼 Map Set,
    // 3. 最後 bfs 有想到
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] outEdge = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) outEdge[i] = new ArrayList<>();
        for (int[] p : prerequisites) {
            inDegree[p[0]]++;
            outEdge[p[1]].add(p[0]);
        }
        LinkedList<Integer> bfs = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) if (inDegree[i] == 0) bfs.add(i);
        while (bfs.size() > 0) {
            int v = bfs.poll();
            for (int out : outEdge[v]) {
                inDegree[out]--;
                if (inDegree[out] == 0) bfs.add(out);
            }
        }
        for (int i : inDegree) if (i > 0) return false;
        return true;

    }
}
