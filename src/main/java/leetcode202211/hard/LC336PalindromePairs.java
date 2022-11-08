package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC336PalindromePairs extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC336PalindromePairs();
    }


    /**
     * https://leetcode.com/problems/palindrome-pairs/discuss/2585763/Java-100-149ms-or-HashMap-based-solution-w-Video-Explanation
     * 這個解一開始看不懂, 沒有 Trie 一開始邏輯這麼清晰, 但 Trie 細節看不懂後回來看這個解 發現這個更直觀
     * 而且速度更快更沒有TLE
     * <p>
     * 1. 建立個 word to idx map
     * 2. 建立word 長度的 set -> 很重要 必用 TreeSet
     * 3. 所有 palindrome cases:
     * 1. 該 word 的 revered 存在 -> 把 word 反轉後直接去 word2Idx map 找
     * Ex: "abcd", reversed: "dcba", 2者直接concat 就是palindrome
     * 2. 該word 的 Reverse 在減少一定長度後是 palindrome, 所以把減少的那邊找出來  就可以組出 palindrome
     * Ex "lls", reverse = "sll"
     * case 1. sll 減去尾巴
     * case 2. sll 減去頭部
     * 重點在能減多少 再去檢查 palindrome? 就是減去比自己長度的少的所有 words 長度, 所以才要建立 TreeSet<Integer> lenSet
     * 因為 TreeSet iterator 是從最小開始, 直到長度大於目前檢查字串長度 就可以離開了(大於自己拿來減 沒意思了)
     * 所以 找到 iLen == 1
     * case 1. sll 留下頭部 1: sl -> not palindrome
     * case 2. sll 留下尾巴 1: ll -> palindrome -> 代表是有可能有長度 1 的字串加上去後 可以成為 palindrome,
     * 所以拿掉尾巴(substring, 因為尾巴已是palindrome, 所以要找頭部concat 上來變成palindrome) 去找wordIdx 找, 有找到就是
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> wordIdx = new HashMap<>();
        TreeSet<Integer> lenSet = new TreeSet<>();

        for (int i = 0; i < words.length; i++) {
            wordIdx.put(words[i], i);
            lenSet.add(words[i].length());
        }

        for (int i = 0; i < words.length; i++) {
            int wLen = words[i].length();
            String reverse = new StringBuilder(words[i]).reverse().toString();
            if (wordIdx.containsKey(reverse) && wordIdx.get(reverse) != i)
                res.add(List.of(i, wordIdx.get(reverse)));

            for (Integer iLen : lenSet) {
                if (iLen == wLen) break;
                if (isP(reverse, 0, wLen - iLen - 1)) {
                    String s1 = reverse.substring(wLen - iLen);
                    if (wordIdx.containsKey(s1)) res.add(List.of(i, wordIdx.get(s1)));
                }
                if (isP(reverse, iLen, wLen - 1)) {
                    String s2 = reverse.substring(0, iLen);
                    if (wordIdx.containsKey(s2)) res.add(List.of(wordIdx.get(s2), i));
                }
            }
        }
        return res;
    }

    boolean isP(String s, int left, int right) {
        while (left < right)
            if (s.charAt(left++) != s.charAt(right--))
                return false;
        return true;
    }

    /**
     * https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure
     * 暴力解當然可以很容易解, 完全沒想到正解是 Trie !!
     * <p>
     * 1. str1 = "ba", 要找到他的 str2 -> str1 + str2 == "baab", str2 = "ab", 所以 Trie 是要每個 word 從尾巴建立起來
     * 2. str1 = "a", 可以跟自己成為 palindrome (str1 + str1 = "aa"), 但這非法, 所以這個 Trie node要記載 word index
     * 3. str1 = "a" , str2 = "aaa", str1 只有一個 a , 所以 Trie 找尋只會走一格, 這樣找不到 aaa 可使之成為 palindrome
     * 所以 Trie node 要多記載一個 List of index:
     * 1. iWord 有這個 Trie node 目前的 suffix
     * 2. iWord 剩餘的 substring 是 palindrome
     * 這邊看不懂
     * Trie 裡面的 list 的關係我一直沒看出來
     * 先放棄了
     * 而且這個解會TLE
     */
    class Trie {
        Trie[] ch;
        int idx;
        List<Integer> list;

        Trie() {
            ch = new Trie[26];
            idx = -1;
            list = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairsTrie(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Trie rt = new Trie();
        for (int i = 0; i < words.length; i++) buildTrie(rt, words[i], i);
        for (int i = 0; i < words.length; i++) searchP(res, rt, words[i], i);
        return res;
    }

    void searchP(List<List<Integer>> res, Trie rt, String s, int idx) {
        for (int i = 0; i < s.length(); i++) {
            if (rt.idx >= 0 && rt.idx != idx && isP(s, i, s.length() - 1)) {
                res.add(Arrays.asList(idx, rt.idx));
            }
            rt = rt.ch[s.charAt(i) - 'a'];
            if (rt == null) return;
        }

        for (int i : rt.list) if (idx != i) res.add(List.of(idx, i));
    }

    void buildTrie(Trie rt, String s, int idx) {
        for (int i = s.length() - 1; i >= 0; i--) {
            if (rt.ch[s.charAt(i) - 'a'] == null) rt.ch[s.charAt(i) - 'a'] = new Trie();
            if (isP(s, 0, i)) rt.list.add(idx);
            rt = rt.ch[s.charAt(i) - 'a'];
        }
        rt.list.add(idx);
        rt.idx = idx;
    }
}
