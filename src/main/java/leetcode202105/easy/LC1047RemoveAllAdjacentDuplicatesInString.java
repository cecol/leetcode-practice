package leetcode202105.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1047RemoveAllAdjacentDuplicatesInString extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1047RemoveAllAdjacentDuplicatesInString();
    }

    /**
     * 很直觀的 用一個stack 檢查 peek 跟新進來的有無重複就建立起來了
     * 如果不用 Stack, 直接用 int[] sk = new int[n];
     * int idx = -1; 算是自己實作 stack 會更快
     * Runtime: 4 ms, faster than 92.76%
     */
    public String removeDuplicates(String s) {
        int n = s.length();
        if (n == 1) return s;
        int[] sk = new int[n];
        int idx = -1;
        for (char c : s.toCharArray()) {
            if (idx >= 0 && sk[idx] == c) idx--;
            else sk[++idx] = c;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= idx; i++) sb.append((char) (sk[i]));
        return sb.toString();
    }
}
