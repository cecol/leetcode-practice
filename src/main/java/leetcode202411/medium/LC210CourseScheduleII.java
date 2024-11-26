package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;
import scala.Int;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC210CourseScheduleII extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 直觀解 前面解過 CourseScheduleI 就足夠
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> res = new ArrayList<>();
        int[] inC = new int[numCourses];
        List<Integer>[] outE = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) outE[i] = new ArrayList<>();
        for (int[] p : prerequisites) {
            inC[p[0]]++;
            outE[p[1]].add(p[0]);
        }
        Queue<Integer> bfs = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) if (inC[i] == 0) bfs.add(i);
        while (!bfs.isEmpty()) {
            Integer v = bfs.poll();
            res.add(v);
            for (int oe : outE[v]) {
                inC[oe]--;
                if (inC[oe] == 0) bfs.add(oe);
            }
        }
        if(res.size() != numCourses) return new int[0];
        int[] res2 = new int[res.size()];
        for (int i = 0; i < res.size(); i++) res2[i] = res.get(i);
        return res2;
    }
}
