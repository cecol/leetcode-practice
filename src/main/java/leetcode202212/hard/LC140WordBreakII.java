package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.io.StdIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class LC140WordBreakII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC140WordBreakII();
        LC.wordBreak("catsanddog", List.of("cat","cats","and","sand","dog"));
    }


    /**
     * 很直觀的 backtrack + Trie 走法
     * 1. 先把 List<String> wordDict 建立好 Trie
     * 2. dfs 帶入 String s, TrieNode rt, StringBuilder sb
     * - 每次 s 切 prefix 長度 i = 1 to s.length
     * - 切出來的有在 Trie 裏面就是加入 StringBuilder sb 然後 dfs 下去
     * - dfs 遇到 String s = empty -> 可以 return 總結
     * */
    class TrieNode {
        TrieNode[] ch = new TrieNode[256];
        String w;
    }

    void add(String s, TrieNode rt) {
        TrieNode cur = rt;
        for (char c : s.toCharArray()) {
            if (cur.ch[c] == null) cur.ch[c] = new TrieNode();
            cur = cur.ch[c];
        }
        cur.w = s;
    }

    boolean isW(String s, TrieNode rt) {
        TrieNode cur = rt;
        for (char c : s.toCharArray()) {
            if (cur.ch[c] == null) return false;
            cur = cur.ch[c];
        }
        return cur.w != null && cur.w.equals(s);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        TrieNode rt = new TrieNode();
        for (String w : wordDict) {
            add(w, rt);
        }
        List<String> res = new ArrayList<>();
        dfs(res, s, rt, new StringBuilder());
        return res;
    }

    void dfs(List<String> res, String s, TrieNode rt, StringBuilder sb) {
        if (s.length() == 0) {
            res.add(sb.toString());
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if (isW(sub, rt)) {
                int len = sb.length();
                if (len > 0) sub = " " + sub;
                sb.append(sub);
                dfs(res, s.substring(i), rt, sb);
                sb.delete(len, len + sub.length());
            }
        }
    }
}
