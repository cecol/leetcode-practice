package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class
LC76MinimumWindowSubstring extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC76MinimumWindowSubstring();
        LC.minWindow("ADOBECODEBANC", "ABC");
    }

    /**
     * 是有解出來, 用兩個 HashMap記載, 一個是 String t 的字組, 一個是 String s window目前自組, 兩者互相比較, 是會過, 但是超級慢ＲＲＲ
     * https://leetcode.com/problems/minimum-window-substring/discuss/26835/Java-4ms-bit-97.6
     * 1. 只記載 t 的 char count, 用 int[256] 來做 -> 因為其實 s 其他不在 t的字元是沒意義的
     * 2. window j 往前走, 每走一次 都看看當前char 不是 t 的 char count, mt[s.charAt(j)] > 0 如果是, matchCount++
     * -> mt[s.charAt(j)]--; 已經中一個了, 要中的數目減少
     * while (matchCount == t.length()) 代表目前window 累積的count是足夠的
     * -> if (j - i + 1 < min) -> 計算 window size
     * -> mt[s.charAt(i)]++; t 的 char count 加回來, 代表要match之後要補回來
     * -> if (mt[s.charAt(i)] > 0) matchCount--; -> 補回來之後該字元變短缺了 -> matchCount 要減少 -> 讓之後 j 繼續擴大尋找下去
     * -> 難就在這, 因為mt[s.charAt(i)] 可能是負數 -> 代表
     * 1. 不需要的字元
     * 2. 是需要的字元, 但該字元在目前window中 有過多
     * 所以 mt[s.charAt(i)] 的範圍是 -x(s window中重複多次比t出現還多) - y(t中最多出現次數)
     * 我覺得難的地方在於, 每一次j 的字元是用 mt[s.charAt(j)]--; 如果是不需要的字元  他們永遠都是 負數 -> 只有需要的t字元才有機會變正
     */
    public String minWindow(String s, String t) {
        int[] mt = new int[256];
        for (char c : t.toCharArray()) mt[c]++;
        int min = Integer.MAX_VALUE;
        int i = 0, j = 0, matchCount = 0, minStart = 0;
        while (j < s.length()) {
            if (mt[s.charAt(j)] > 0) matchCount++;
            mt[s.charAt(j)]--;
            while (matchCount == t.length()) {
                if (j - i + 1 < min) {
                    min = j - i + 1;
                    minStart = i;
                }
                mt[s.charAt(i)]++;
                if (mt[s.charAt(i)] > 0) matchCount--;
                i++;
            }
            j++;
        }
        if (minStart + min > s.length()) return "";
        return s.substring(minStart, minStart + min);
    }
}
