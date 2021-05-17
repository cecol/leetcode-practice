package leetcode202105.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1032StreamOfCharacters extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1032StreamOfCharacters();
    }

    class Trie {
        Trie[] child = new Trie[26];
        boolean isW = false;
    }

    /**
     * 一開始直接無腦暴力解(直接每個字串下去 startWith)
     * 有想一下是否是 Trie, 但不是很確定, 直到看到解答果然就是Trie
     * 一開始還以為是 KMP(字串比對) 演算法, 結果只是Trie
     * Trie: Runtime: 82 ms, faster than 81.45%
     */
    Trie root = null;
    StringBuilder sb = new StringBuilder();

    public void StreamChecker(String[] words) {
        root = new Trie();
        for (String w : words) {
            Trie cur = root;
            for (int i = w.length() - 1; i >= 0; i--) {
                if (cur.child[w.charAt(i) - 'a'] == null) cur.child[w.charAt(i) - 'a'] = new Trie();
                cur = cur.child[w.charAt(i) - 'a'];
            }
            cur.isW = true;
        }
    }

    public boolean query(char letter) {
        sb.append(letter);
        Trie cur = root;
        for (int i = sb.length() - 1; i >= 0 && cur != null; i--) {
            cur = cur.child[sb.charAt(i) - 'a'];
            if (cur != null) if (cur.isW) return true;
        }
        return false;
    }
}
