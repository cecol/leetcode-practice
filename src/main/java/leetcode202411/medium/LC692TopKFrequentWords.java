package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC692TopKFrequentWords extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 直觀解 pq + Map.Entry 可以解
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> m = new HashMap<>();
        for (String w : words) {
            m.put(w, m.getOrDefault(w, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((x, y) -> {
            if (!x.getValue().equals(y.getValue())) return y.getValue() - x.getValue();
            else return x.getKey().compareTo(y.getKey());
        });

        for (Map.Entry<String, Integer> en : m.entrySet()) pq.add(en);

        List<String> res = new ArrayList<>();
        while (k > 0 && !pq.isEmpty()) {
            res.add(pq.poll().getKey());
            k--;
        }
        return res;
    }
}
