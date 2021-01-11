package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class LC210CourseScheduleII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC210CourseScheduleII();
        var s = LC.findOrder(2, new int[][]{{1, 0}, {0, 1}});
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        ArrayList<Integer>[] outEdges = new ArrayList[numCourses];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) outEdges[i] = new ArrayList<>();
        for (int[] req : prerequisites) {
            int n = req[1];
            int m = req[0];
            inDegree[m]++;
            outEdges[n].add(m);
        }
        Queue<Integer> bfsNoInEdge = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) if (inDegree[i] == 0) bfsNoInEdge.offer(i);
        while (!bfsNoInEdge.isEmpty()) {
            Integer n = bfsNoInEdge.poll();
            res.add(n);
            for (int m : outEdges[n]) {
                inDegree[m]--;
                if (inDegree[m] == 0) bfsNoInEdge.offer(m);
            }
        }
        if (IntStream.of(inDegree).anyMatch(i -> i != 0)) return new int[]{};
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
