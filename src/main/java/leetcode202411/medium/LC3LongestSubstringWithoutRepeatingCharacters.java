package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

public class LC3LongestSubstringWithoutRepeatingCharacters extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 可能前面練習太多題了, 所以很快想起 雙指針解法, 且一次解開
    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0, n = s.length();
        int[] cs = new int[256];
        int res = 0;
        while (j < s.length()) {
            char c = s.charAt(j);
            cs[c]++;
            while (cs[c] > 1 && i < j) {
                char ic = s.charAt(i);
                cs[ic]--;
                i++;
            }
            res = Math.max(j - i + 1, res);
            j++;
        }
        return res;
    }
}
