package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC745PrefixAndSuffixSearch extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC745PrefixAndSuffixSearch();
    }

    /**
     * https://leetcode.com/problems/prefix-and-suffix-search/solutions/1189333/java-trie/
     * 其實就是很直觀的建立 兩棵 Trie
     * 一棵 t1 forward 是 從 String s.toCharArray(): 0 to s.length 建立
     * 一棵 t2 backward 是 從 String s.toCharArray(): s.length to 0 建立
     * 然後每一個 Trie.ch 過程中都記載看到的 idx
     *
     * 最後在 find 就得 prefix 到 t1 forward tree 找
     * 最後在 find 就得 suffix 到 t2 backward tree 找
     * 想者找到的 idx list, 就從尾巴開始比對  比對到的 match idx 就可以回傳
     * 我這邊 回傳的 idx 用 兩個TreeSet interesction 去找實在太慢  會 TLE
     * 以為 TreeSet.last 會快很多, 結果 ArrayList 回頭找還是比較快
     * */
    class WordFilter {

        class Trie {
            Trie[] ch = new Trie[256];
            List<Integer> idx = new ArrayList<>();
        }

        void add(String s, int idx, Trie rt) {
            Trie cur = rt;
            for (char c : s.toCharArray()) {
                if (cur.ch[c] == null) cur.ch[c] = new Trie();
                cur = cur.ch[c];
                cur.idx.add(idx);
            }
        }

        List<Integer> find(String s, Trie rt) {
            Trie cur = rt;
            for (char c : s.toCharArray()) {
                if (cur.ch[c] == null) return new ArrayList<>();
                cur = cur.ch[c];
            }
            return cur.idx;
        }

        public WordFilter(String[] words) {
            for (int i = 0; i < words.length; i++) {
                add(words[i], i, pre);
                add(new StringBuilder(words[i]).reverse().toString(), i, suf);
            }
        }

        Trie pre = new Trie();
        Trie suf = new Trie();

        public int f(String pref, String suff) {
            List<Integer> preIdx = find(pref, pre);
            List<Integer> sufIdx = find(new StringBuilder(suff).reverse().toString(), suf);
            if (preIdx.size() == 0 || sufIdx.size() == 0) return -1;
            int f = preIdx.size() - 1;
            int b = sufIdx.size() - 1;
            while (f >= 0 && b >= 0) {
                if (preIdx.get(f).equals(sufIdx.get(b))) return preIdx.get(f);
                else if (preIdx.get(f) > sufIdx.get(b)) f--;
                else b--;
            }
            return -1;
        }
    }
}
