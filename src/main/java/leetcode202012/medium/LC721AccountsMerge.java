package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC721AccountsMerge extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC721AccountsMerge();
        var s = LC.accountsMerge(null);
    }

    /**
     * https://leetcode.com/problems/accounts-merge/discuss/109157/JavaC%2B%2B-Union-Find
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (List<String> a : accounts) {
            for (int i = 1; i < a.size(); i++) {
                parents.put(a.get(i), a.get(i));
                owner.put(a.get(i), a.get(0));
            }
        }
        for (List<String> a : accounts) {
            String p = find(a.get(1), parents);
            for (int i = 2; i < a.size(); i++) {
                parents.put(find(a.get(i), parents), p);
            }
        }
        for (List<String> a : accounts) {
            String p = find(a.get(1), parents);
            if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
            for (int i = 1; i < a.size(); i++) {
                unions.get(p).add(a.get(i));
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList(unions.get(p));
            emails.add(0, owner.get(p));
            res.add(emails);
        }
        return res;
    }

    public String find(String s, Map<String, String> p) {
        return p.get(s).equals(s) ? s : find(p.get(s), p);
    }
}
