package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

public class LC76MinimumWindowSubstring extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 有想到是雙指針, 但是細部處理還是都忘了
    // 1. 有個 MIN 來找出 目前找到的最短距離
    // 2. mc (match count) 記載目前找到多少, 跟目標長度比較
    // 3. mc++ mc-- 替換 min & minS (minStart) - 來找到最終答案
    public String minWindow(String s, String t) {
        int[] cc = new int[256];
        for (char c : t.toCharArray()) cc[c]++;
        int min = Integer.MAX_VALUE;
        int i = 0, j = 0, mc = 0, minS = 0;
        while (j < s.length()) {
            if (cc[s.charAt(j)] > 0) mc++;
            cc[s.charAt(j)]--;
            while (mc == t.length()) {
                if (j - i + 1 < min) {
                    min = j - i + 1;
                    minS = i;
                }
                cc[s.charAt(i)]++;
                if (cc[s.charAt(i)] > 0) mc--;
                i++;
            }
            j++;
        }
        if (minS + min > s.length()) return "";
        return s.substring(minS, minS + min);
    }
}
