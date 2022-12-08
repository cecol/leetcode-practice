package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC444SequenceReconstruction extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC444SequenceReconstruction();
    }

    /**
     * https://leetcode.com/problems/sequence-reconstruction/solutions/92580/java-solution-using-bfs-topological-sort/
     * 很直觀的 拓樸排序 但是參與了很多拓墣細節確認
     * 但要驗證 sequence 合法與否, 驗證只能有一種 拓樸排序
     * 1. 還是要建立
     * - outNodes: Map<Integer, List<Integer>> graph = new HashMap<>();
     * - indegree count: Map<Integer, Integer> indegree = new HashMap<>();
     * 2. bfs 把 indegree count == 0 的點都放入 bfs
     * 3. bfs 如何確認是否真的唯一？
     * - if(bfs.size() > 1) return false; - 每次都只有一個點要去拓樸
     * - if(nums[idx++] != cur) return false; - 正在拓樸的點 也正好在 nums 期待的位置上, 從 offset 0 .. n-1 照順序拓樸
     * - return idx == nums.length; 最後是否有真的拓樸完
     * */
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (List<Integer> seq : sequences) {
            for (int i = 0; i < seq.size(); i++) {
                if (!graph.containsKey(seq.get(i))) graph.put(seq.get(i), new ArrayList<>());
                if (!indegree.containsKey(seq.get(i))) indegree.put(seq.get(i), 0);
                if (i > 0) {
                    graph.get(seq.get(i - 1)).add(seq.get(i));
                    indegree.put(seq.get(i), indegree.get(seq.get(i)) + 1);
                }
            }
        }
        if (nums.length != indegree.size()) return false;
        Queue<Integer> bfs = new LinkedList<>();
        for (Integer i : indegree.keySet()) if (indegree.get(i) == 0) bfs.offer(i);
        int idx = 0;
        while (bfs.size() > 0) {
            if(bfs.size() > 1) return false;
            int cur = bfs.poll();
            if(nums[idx++] != cur) return false;
            for(Integer nb:graph.get(cur)) {
                indegree.put(nb, indegree.get(nb)-1);
                if(indegree.get(nb) == 0) bfs.offer(nb);
            }
        }
        return idx == nums.length;
    }
}
