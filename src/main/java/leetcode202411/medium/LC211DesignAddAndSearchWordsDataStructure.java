package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC211DesignAddAndSearchWordsDataStructure extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 用 trie 可以解 但 search . 的案例, 實在卡太久了;
    public void WordDictionary() {

    }

    class Node {
        Node[] ch = new Node[26];
        boolean isW;
    }

    Node rt = new Node();

    public void addWord(String word) {
        Node cur = rt;
        for (char c : word.toCharArray()) {
            if (cur.ch[c - 'a'] == null) cur.ch[c - 'a'] = new Node();
            cur = cur.ch[c - 'a'];
        }
        cur.isW = true;
    }

    public boolean search(String word) {
        return backtrack(word, rt, 0);
    }


    boolean backtrack(String word, Node rt, int i) {
        if (rt == null) return false;
        if (i == word.length()) return rt.isW;
        char c = word.charAt(i);
        if (c == '.') {
            boolean f = false;
            for (Node cc : rt.ch) f |= backtrack(word, cc, i + 1);
            return f;
        } else return backtrack(word, rt.ch[c - 'a'], i + 1);
    }
}
