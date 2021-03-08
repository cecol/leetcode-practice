package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC208ImplementTrie_PrefixTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC208ImplementTrie_PrefixTree();
    }

    /**
     * https://leetcode.com/problems/implement-trie-prefix-tree/discuss/58832/AC-JAVA-solution-simple-using-single-array
     * 第一次知道Trie實作, 其實蠻簡單的 做好一個 TrieNode, 裡面都存 child = TrieNode[26]
     * child[c - 'a'] == null 指示出有無該字元 可否搜尋到
     * insert 就一直往下 child[c - 'a'] = new TrieNode();
     * */
    class TrieNode {
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];

        public TrieNode() {
        }

    }

    private TrieNode root;

    public void Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) ws.children[c - 'a'] = new TrieNode();
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode ws = root;
        for (char c : word.toCharArray()) {
            if (ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode ws = root;
        for (char c : prefix.toCharArray()) {
            if (ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }
}
