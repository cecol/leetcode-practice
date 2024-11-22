package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC383RansomNote extends BasicTemplate {
    public static void main(String[] args) {
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.length() == 0) return true;
        if (magazine == null || magazine.length() == 0) return false;
        int[] v = new int[26];
        for (char c : magazine.toCharArray()) {
            v[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            v[c - 'a']--;
            if (v[c - 'a'] < 0) return false;
        }
        return true;
    }

}
