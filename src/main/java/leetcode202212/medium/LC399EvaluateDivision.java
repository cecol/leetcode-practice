package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC399EvaluateDivision extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC399EvaluateDivision();
    }

    /**
     * https://leetcode.com/problems/evaluate-division/discuss/1992891/java-oror-DFS-Solution-with-comments-oror-Evaluate-Division-%3A)
     * 第一眼看到題目有點眼花撩亂, 但大概知道要推導的 queries[i] 結果 一定是從 equations 這邊帶來的
     * 1. 所以先透過 List<List<String>> equations, double[] values 建立
     * Map<String, Map<String, Double>> graph = new HashMap<>();
     * 正向反向都要
     * 2. 接著就是根據 queries[i].src 到 queries[i].dest 下去 dfs
     * in dfs, 如果帶進來的 src 根本不在 Map<String, Map<String, Double>> graph, 一定 -1
     * in dfs, 如果帶進來的 src 已經有了 dest, 直接回傳
     * in dfs, 根據 src Map<String, Double> 裏面的所有元素 變成下一層 src 下去 dfs
     * in dfs, 如果有回傳 非 -1, 就是拿來跟目前的 結果相乘回傳, Ex: a/c == a/b * b/c
     * */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        String u, v;
        for (int i = 0; i < equations.size(); i++) {
            u = equations.get(i).get(0);
            v = equations.get(i).get(1);

            graph.putIfAbsent(u, new HashMap<>());
            graph.get(u).put(v, values[i]);

            graph.putIfAbsent(v, new HashMap<>());
            graph.get(v).put(u, 1 / values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            res[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), graph, new HashSet<>());
        }
        return res;
    }

    double dfs(String src, String dest, Map<String, Map<String, Double>> graph, Set<String> visited) {
        if (!graph.containsKey(src)) return -1.0;
        if (graph.get(src).containsKey(dest)) return graph.get(src).get(dest);
        visited.add(src);
        for (Map.Entry<String, Double> nbr : graph.get(src).entrySet()) {
            if (!visited.contains(nbr.getKey())) {
                double weight = dfs(nbr.getKey(), dest, graph, visited);
                if(weight != -1) return nbr.getValue() * weight;
            }
        }
        return -1.0;
    }
}
