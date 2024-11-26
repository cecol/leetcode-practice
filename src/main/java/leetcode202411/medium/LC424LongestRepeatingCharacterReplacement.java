package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

public class LC424LongestRepeatingCharacterReplacement extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 有想到 two pointer, 但細節沒做對
    // 關鍵在  j - i + 1 - mc > k , 要開始縮表 i++
    // 只在乎 每個字元累計的 max count, 只要 j - i + 1 - max count < k, 就可以往前走
    public int characterReplacement(String s, int k) {
        int res = 0, n = s.length(), i = 0, j = 0, mc = 0;
        int[] cc = new int[256];
        while (j < n) {
            char v = s.charAt(j);
            cc[v]++;
            mc = Math.max(mc, cc[v]);
            if (j - i + 1 - mc > k) {
                char e = s.charAt(i);
                cc[e]--;
                i++;
            }
            res = Math.max(res, j - i + 1);
            j++;
        }
        return res;
    }
}
