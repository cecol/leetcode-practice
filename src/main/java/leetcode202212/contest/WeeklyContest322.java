package leetcode202212.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WeeklyContest322 extends BasicTemplate {
    public static void main(String[] args) throws IOException {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new WeeklyContest322();
//        LC.minScore(4, new int[][]{{1, 2, 9}, {2, 3, 6}, {2, 4, 5}, {1, 4, 7}});
//        LC.minScore(4, new int[][]{{1, 2, 2}, {1, 3, 4}, {3, 4, 7}});
        LC.magnificentSets(6, new int[][]{{1, 2}, {1, 4}, {1, 5}, {2, 6}, {2, 3}, {4, 6}});
    }

    public boolean isCircularSentence(String sentence) {
        String[] ss = sentence.split(" ");
        for (int i = 0; i < ss.length; i++) {
            int idx = (i + 1) % ss.length;
            if (ss[i].charAt(ss[i].length() - 1) != ss[idx].charAt(0)) return false;
        }
        return true;
    }

    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);
        long res = 0;
        int expected = skill[0] + skill[skill.length - 1];
        for (int i = 0; i < skill.length / 2; i++) {
            if (skill[i] + skill[skill.length - i - 1] != expected) return -1;
            else res += (long) skill[i] * skill[skill.length - i - 1];
        }
        return res;
    }

    public int minScore(int n, int[][] roads) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        List<int[]>[] path = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) path[i] = new ArrayList<>();
        for (int[] r : roads) {
            path[r[0]].add(new int[]{r[1], r[2]});
            path[r[1]].add(new int[]{r[0], r[2]});
        }

        dist[1] = 0;
        PriorityQueue<int[]> minCost = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
        minCost.offer(new int[]{1, 0});
        while (!minCost.isEmpty()) {
            int[] minCostNode = minCost.poll();
            int minN = minCostNode[0];
            int minC = minCostNode[1];
            if (dist[minN] < minC) continue;
            for (int[] adjN : path[minN]) {
                Integer adjV = adjN[0];
                Integer adjC = adjN[1];
                if (dist[adjV] > Math.min(adjC, minC) || minC == 0) {
                    dist[adjV] = minC == 0 ? adjN[1] : Math.min(adjC, minC);
                    minCost.offer(new int[]{adjV, dist[adjV]});
                }
            }
        }
        for (int[] p1 : path[1]) {
            dist[n] = Math.min(p1[1], dist[n]);
        }
        for (int[] pn : path[n]) {
            dist[n] = Math.min(pn[1], dist[n]);
        }
        return dist[n];
    }

    public int magnificentSets(int n, int[][] edges) {
        return  - 1;
    }
}
