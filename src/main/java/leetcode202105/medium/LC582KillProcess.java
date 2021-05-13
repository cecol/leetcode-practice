package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class LC582KillProcess extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC582KillProcess();
    }

    /**
     * 蠻直觀解法, 就是Tree, 但不是給TreeNode, 所以自己算出 outEdges
     * 然後用 queue bfs走完, 就是把要kill的 child都清光
     * */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> res = new ArrayList<>();
        int n = pid.size();
        Map<Integer, List<Integer>> outEdges = new HashMap<>();
        for(int i=0;i<n;i++) {
            outEdges.computeIfAbsent(ppid.get(i), x -> new ArrayList<>());
            outEdges.get(ppid.get(i)).add(pid.get(i));
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(kill);
        while(q.size() > 0) {
            Integer killed = q.poll();
            res.add(killed);
            for(Integer child:outEdges.getOrDefault(killed, new ArrayList<>())) {
                q.offer(child);
            }
        }
        return res;
    }
}
