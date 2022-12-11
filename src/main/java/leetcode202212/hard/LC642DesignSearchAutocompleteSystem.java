package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC642DesignSearchAutocompleteSystem extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC642DesignSearchAutocompleteSystem();
    }


    /**
     * https://leetcode.com/problems/design-search-autocomplete-system/solutions/105376/java-solution-trie-and-priorityqueue/
     * 很基本的 Trie 要加上幾個修正
     * 1. child 用 Map<Character, TrieNode> ch 記載
     * 2. Map<String, Integer> counts, 走到當前 child char, 的哪些 String, 這邊要存完整的 String, 因為 String 後面代表潛在可以拿出來的 搜尋結果
     *
     * 所以在 build Trie 時候
     * 1. 得 把當前 String count 加到當前 cur.count
     * - cur.counts.put(s, cur.counts.getOrDefault(s, 0) + count);
     * 在搜尋時候
     * 1. 當遇到 #, 得把當前累計的 prefix, 加到 Trie 加到當前紀錄中, return empty, 因為搜尋結束
     * 2. 遇到 char c != '#', prefix 得加總記錄下來, 用當前 prefix 從 root 一路找到最後一個 child char
     * 如果中途 child == null, return empty list
     * 最後回傳最後 cur.child.count, 用 PriorityQueue 排出前三個
     *
     * */
    public void AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) {
            add(sentences[i], times[i]);
        }
    }

    class TrieNode {
        Map<Character, TrieNode> ch = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();
        boolean isWord;
    }

    TrieNode root = new TrieNode();
    String prefix = "";

    void add(String s, int count) {
        TrieNode cur = root;
        for (char c : s.toCharArray()) {
            if (cur.ch.get(c) == null) {
                cur.ch.put(c, new TrieNode());
            }
            cur = cur.ch.get(c);
            cur.counts.put(s, cur.counts.getOrDefault(s, 0) + count);
        }
        cur.isWord = true;
    }

    public List<String> input(char c) {
        if (c == '#') {
            add(prefix, 1);
            prefix = "";
            return new ArrayList<>();
        }

        prefix = prefix + c;
        TrieNode cur = root;
        for (char cc : prefix.toCharArray()) {
            TrieNode next = cur.ch.get(cc);
            if (next == null) return new ArrayList<>();
            cur = next;
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((x, y) ->
                x.getValue() == y.getValue() ? x.getKey().compareTo(y.getKey()) : y.getValue() - x.getValue());
        pq.addAll(cur.counts.entrySet());

        List<String> res = new ArrayList<>();
        while (res.size() < 3 && pq.size() > 0) res.add(pq.poll().getKey());
        return res;
    }
}
