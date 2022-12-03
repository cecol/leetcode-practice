package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class LC316RemoveDuplicateLetters extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC316RemoveDuplicateLetters();
        LC.removeDuplicateLetters("bbcaac");
    }

    /**
     * 很直觀知道用 stack monotonic increase 來做
     * 如果遇到 s.charAt(i) < stack.top, 有機會 stack.pop 往前進, 創造更小 string
     * 原本想說用個 charCount + visited , 看看是否遇到 s.charAt(i) < stack.top, 有機會 stack.pop 真的可以往前進
     * 看要被 pop 是復可以真的被 pop
     * 但有細節一直沒處理好
     * 直到看的這個解法
     * https://leetcode.com/problems/remove-duplicate-letters/discuss/1859410/JavaC%2B%2B-DETAILED-%2B-VISUALLY-EXPLAINED-!!
     * 與其用 charCount, 乾脆看這個 char 最後出現位置, 如果後面還出現, 就是當前 i < lastIdx[char], 代表可以被 pop
     * visited 就很簡單的就是看當前 s.charAt(i) 有沒有, 有就跳過
     * */
    public String removeDuplicateLetters(String s) {
        int[] lastIdx = new int[26];
        for (int i = 0; i < s.length(); i++) lastIdx[s.charAt(i) - 'a'] = i;
        StringBuilder sb = new StringBuilder();
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (visited.contains(c)) continue;
            while (sb.length() > 0 && c < sb.charAt(sb.length() - 1) && lastIdx[sb.charAt(sb.length() - 1) - 'a'] > i) {
                visited.remove(sb.charAt(sb.length() - 1));
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(c);
            visited.add(c);
        }
        return sb.toString();
    }
}
