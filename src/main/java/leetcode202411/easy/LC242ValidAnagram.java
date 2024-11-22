package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;


public class LC242ValidAnagram extends BasicTemplate {
    public static void main(String[] args) {
    }

    public boolean isAnagram(String s, String t) {
        int[] sc = new int[26];
        for (Character c : s.toCharArray()) {
            sc[c - 'a']++;
        }
        for (Character c : t.toCharArray()) {
            sc[c - 'a']--;
        }
        for (int i : sc) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
