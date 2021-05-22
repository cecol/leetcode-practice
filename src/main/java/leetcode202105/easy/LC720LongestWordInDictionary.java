package leetcode202105.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LC720LongestWordInDictionary extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC720LongestWordInDictionary();
    }

    class Trie {
        Trie[] child = new Trie[26];
        String w = null;
    }

    /**
     * 這題是很直觀的用BFS解掉, 但效率不好, 要加速就是建立Trie, 然後 DFS下去
     */
    String res = "";

    public String longestWord(String[] words) {
        Trie root = new Trie();
        for (String w : words) {
            Trie cur = root;
            for (Character c : w.toCharArray()) {
                if (cur.child[c - 'a'] == null) cur.child[c - 'a'] = new Trie();
                cur = cur.child[c - 'a'];
            }
            cur.w = w;
        }
        dfs(root);
        return res;
    }

    private void dfs(Trie r) {
        if (r.w != null) {
            if (r.w.length() > res.length() ||
                    r.w.length() == res.length() && r.w.compareTo(res) <= 0) res = r.w;
        }
        for (Trie c : r.child) {
            if (c != null && c.w != null) dfs(c);
        }
    }

    public String longestWordSlow(String[] words) {
        String res = "";
        Set<String> ws = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        for (String w : words) {
            ws.add(w);
            if (w.length() == 1) q.offer(w);
        }

        while (q.size() > 0) {
            String cur = q.poll();
            if (cur.length() > res.length() ||
                    (cur.length() == res.length() && cur.compareTo(res) < 0)
            ) res = cur;
            for (int i = 0; i < 26; i++) {
                char nc = (char) (i + 'a');
                String k = cur + nc;
                if (ws.contains(k)) q.offer(k);
            }
        }
        return res;
    }
}
