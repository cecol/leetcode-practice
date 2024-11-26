package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC49GroupAnagrams extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 秒解,直觀想到解法跟以前一樣
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();
        for (String str : strs) {
            int[] cc = new int[256];
            for (char c : str.toCharArray()) cc[c]++;
            String code = Arrays.toString(cc);
            if (!m.containsKey(code)) m.put(code, new ArrayList<>());
            m.get(code).add(str);
        }
        List<List<String>> res = new ArrayList<>();
        for (String k : m.keySet()) res.add(m.get(k));
        return res;
    }
}
