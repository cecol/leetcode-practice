package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC212WordSearchII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC212WordSearchII();
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    /**
     * https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)
     * 一直沒有很搞清楚要把Trie 應用在 board 上面, 原本以為就是把 board 每個字元開始去建各種Trie, 但覺得也不太可能
     * 後來看到答案才理解邏輯是反過來的
     * 把要找的字串組成 Trie -> 然後dfs board每一格, 讓dfs邊走邊搜尋Trie, 如果Trie 有走下去且最後走到葉子就是要加入的答案
     * DFS中 如果當前這格的 char c 跟當前的 p.children[c - 'a'] != null 代表可以走下去
     * 走過的時候把 board[i][j] = '#' , 讓後面的卡住, 遞迴回來後要把 board[i][j] 還原成原本的 char(backtracking)
     * 如果當前的 p.isWord -> 已經走到葉子 -> 要找的字串, 直接加入, 然後 p.word = null; -> 重複的不會再被走到
     *
     * 之如果當前board[i][j] == '#' -> 代表走過 or p.children[c - 'a'] == null Trie無路可走, 沒這個字
     * */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode r = build(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, res, i, j, r);
            }
        }
        return res;
    }

    private void dfs(char[][] b, List<String> res, int i, int j, TrieNode p) {
        char c = b[i][j];
        if (c == '#' || p.children[c - 'a'] == null) return;
        p = p.children[c - 'a'];
        if (p.word != null) {
            res.add(p.word);
            p.word = null;
        }
        b[i][j] = '#';
        if (i > 0) dfs(b, res, i - 1, j, p);
        if (j > 0) dfs(b, res, i, j - 1, p);
        if (i < b.length - 1) dfs(b, res, i + 1, j, p);
        if (j < b[0].length - 1) dfs(b, res, i, j + 1, p);
        b[i][j] = c;
    }

    private TrieNode build(String[] ws) {
        TrieNode r = new TrieNode();
        for (String s : ws) {
            TrieNode p = r;
            for (char c : s.toCharArray()) {
                if (p.children[c - 'a'] == null) p.children[c - 'a'] = new TrieNode();
                p = p.children[c - 'a'];
            }
            p.word = s;
        }
        return r;
    }
}
