package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC336PalindromePairs extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 1. 建立 wordIdx: word -> idx map
    // 2. 建立 lenSet: word size set (TreeSet)
    // 3. 先看自己 reverse 在不在 wordIdx (要排除反轉後還是自己案例, eg: aaaa -> aaaa)
    // 4. 再看看 lenSet
    // 5. 如果 reverse 減去 尾巴 string of lenSet(i) is palindrome -> 去看看
    // 5-1. 因為要找鏡像 -> 一定都是 reverse 來反查
    // 6. lls -> reverse: sll, lenSet(i) = 1, isP(ssl, 0, 1)
    // 7. sll.substring(0, 3-1-1) -> s -> isP -> 找到 in wordIdx
    // 7-1. s2 = sll.substring(2): l
    // 8. sssll -> reverse: llsss, lenSet(i) = 3, isP(llsss, 3, 4)
    // 9. llsss.substring(3, 5-1) -> ss
    // 9-1. s2 = llsss.substring(0, 3) -> lls -> (lls, sssll)
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

    boolean isP(String s, int l, int r) {
        while (l < r)
            if (s.charAt(l++) != s.charAt(r--)) return false;
        return true;
    }
}
