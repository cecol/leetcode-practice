package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC811SubdomainVisitCount extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC811SubdomainVisitCount();
        LC.subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"});
    }

    /**
     * 雖然我想到的思路是沒錯
     * 但想得太複雜, 參考答案 https://leetcode.com/problems/subdomain-visit-count/discuss/121738/C%2B%2BJavaPython-Easy-Understood-Solution
     * 才發現用 String.indexOf 配上 substring的特性應該會更簡潔
     * 因為 domain 只有第一個不要
     * 所以在 domain中找到 . 的位置後 就從該位置取 substring 下去繼續找會更有效率
     * 原本我以為是用 split("\\.") 但這樣做法實在效率不好 還得考慮各種corner case
     * 而且用 s.charAt(i) == '.' 一直往前找就會找到所有的 sub domain , 邏輯上比較直覺多了
     * */
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> c = new HashMap<>();
        for (String cc : cpdomains) {
            int index = cc.indexOf(' ');
            int n = Integer.parseInt(cc.substring(0, index));
            String s = cc.substring(index + 1);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '.') {
                    String d = s.substring(i + 1);
                    c.put(d, c.getOrDefault(d, 0) + n);
                }
            }
            c.put(s, c.getOrDefault(s, 0) + n);
        }
        List<String> res = new ArrayList<>();
        for (String d : c.keySet()) res.add(c.get(d) + " " + d);
        return res;
    }
}
