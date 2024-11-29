package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC271EncodeAndDecodeStrings extends BasicTemplate {
    public static void main(String[] args) {
    }


    // 直觀解
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append("#").append(s);
        }
        return sb.toString();
    }

    // 5#Hello5#world
    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() == 0) return res;
        while (s.length() > 0) {
            int idx = s.indexOf('#');
            int size = Integer.valueOf(s.substring(0, idx));
            res.add(s.substring(idx + 1, idx + size + 1));
            s = s.substring(idx + size + 1);
        }
        return res;
    }
}
