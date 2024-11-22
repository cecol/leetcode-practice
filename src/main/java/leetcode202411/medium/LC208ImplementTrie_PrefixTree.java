package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC208ImplementTrie_PrefixTree extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒有初見殺, 雖然有想到 26個 兒子
    // 關鍵
    // 1. 建立 class with child [26]
    // 2. 建立 root, 然後就是從 root 開始換下去
    class TrieNode {
        TrieNode[] ch = new TrieNode[26];
        boolean isW;

        public TrieNode() {
        }
    }

    TrieNode rt;

    public void Trie() {
        rt = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = rt;
        for (char c : word.toCharArray()) {
            if (cur.ch[c - 'a'] == null) cur.ch[c - 'a'] = new TrieNode();
            cur = cur.ch[c - 'a'];
        }
        cur.isW = true;
    }

    public boolean search(String word) {
        TrieNode cur = rt;
        for (char c : word.toCharArray()) {
            if (cur.ch[c - 'a'] == null) return false;
            cur = cur.ch[c - 'a'];
        }
        return cur.isW;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = rt;
        for (char c : prefix.toCharArray()) {
            if (cur.ch[c - 'a'] == null) return false;
            cur = cur.ch[c - 'a'];
        }
        return true;
    }
}
