package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC692TopKFrequentWords extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC692TopKFrequentWords();
    }

    /**
     * 是很直觀用Heap 來解, 塞進Heap 來取出k個, 4 ms, faster than 99.90%
     * 只是沒想到有 Trie + bucket 解, 但沒細看
     * 還是記著直觀解法就好
     * */
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        PriorityQueue<Map.Entry<String, Integer>> q = new PriorityQueue<>((x, y) -> y.getValue() != x.getValue() ? y.getValue() - x.getValue() : x.getKey().compareTo(y.getKey()));
        Map<String, Integer> m = new HashMap<>();
        for (String w : words) m.put(w, m.getOrDefault(w, 0) + 1);
        for (Map.Entry<String, Integer> en : m.entrySet()) q.add(en);
        for (; k > 0; k--) res.add(q.poll().getKey());
        return res;
    }
}
