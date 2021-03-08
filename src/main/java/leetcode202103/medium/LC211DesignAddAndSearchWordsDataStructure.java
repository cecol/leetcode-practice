package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC211DesignAddAndSearchWordsDataStructure extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC211DesignAddAndSearchWordsDataStructure();
    }

    class TrieNode {
        public TrieNode[] c = new TrieNode[26];
        boolean iw = false;

        public TrieNode() {

        }
    }

    /**
     * 這題感覺就是無腦用 HashMap來做就好, 可是有出會跑 50000 add/search -> 如果用 HashMap 應該會很佔空間
     * 要省空間就是做一個Trie, 不過這題在 search 會有 . 的任意字元match模式, 我是沒想出什麼比較好的處理方式
     * 用Ｔrie 配上 遞迴search 來處理 . case , 是可以過
     * 但是速度普普(其實也贏 70%)
     * 這個是更快的實作
     * https://leetcode.com/problems/design-add-and-search-words-data-structure/discuss/59669/Java-Solution-easy-understand
     * 他是用 string length 來分賴存放在 HashMap, 大概是因為test case大部分的字串長度都是相近, 所以hashMap不會太大
     * 所以找字時候
     * 1. 先看長度拿出HashMap搜集到該長度的字串
     * 2. 拿到List<String> -> 先看要搜尋的是否是全字串, 還是有萬用字元 . ->
     * ->   如果全字串直接 List.contains(String) 來找
     * ->   如果有含萬用字元就得一個一個下去比對
     * 想不到這樣也算很快, 但前提就是他的測試自船長度都大致一樣, 不然mem可能會爆炸
     */
    TrieNode r = new TrieNode();

    public void WordDictionary() {

    }

    public void addWord(String word) {
        TrieNode tt = r;
        for (char c : word.toCharArray()) {
            if (tt.c[c - 'a'] == null) tt.c[c - 'a'] = new TrieNode();
            tt = tt.c[c - 'a'];
        }
        tt.iw = true;
    }

    public boolean search(String word) {
        return backtrack(r, 0, word);
    }

    private boolean backtrack(TrieNode t, int i, String w) {
        if (t == null) return false;
        if (i == w.length()) return t.iw;
        char c = w.charAt(i);
        if (c == '.') {
            boolean f = false;
            for (TrieNode cc : t.c) f |= backtrack(cc, i + 1, w);
            return f;
        } else return backtrack(t.c[c - 'a'], i + 1, w);
    }
}
