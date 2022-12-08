package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1136ParallelCourses extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1136ParallelCourses();
    }

    public int minimumSemesters(int n, int[][] relations) {
        List<Integer>[] out = new ArrayList[n + 1];
        int[] in = new int[n + 1];
        for (int i = 1; i <= n; i++) out[i] = new ArrayList<>();
        for (int[] r : relations) {
            out[r[0]].add(r[1]);
            in[r[1]]++;
        }

        Queue<Integer> bfs = new LinkedList<>();
        for (int i = 1; i <= n; i++) if (in[i] == 0) bfs.add(i);
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        while (bfs.size() > 0) {
            int s = bfs.size();
            res++;
            for (int i = 0; i < s; i++) {
                int v = bfs.poll();
                visited.add(v);
                for (int u : out[v]) {
                    in[u]--;
                    if (in[u] == 0) bfs.add(u);
                }
            }
        }
        return visited.size() == n ? res : -1;
    }
}
