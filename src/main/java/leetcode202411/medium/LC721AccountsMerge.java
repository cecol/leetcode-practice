package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC721AccountsMerge extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 完全忘記這題關鍵是
    // 1. unions find!!
    // 2. 準備好 parent table Map<String, String> parents for union find
    // 3. 準備好 owner table Map<String, String> for 最後掛名字回去
    // 4. 準備好 Map<String, TreeSet<String>> unios, 收集關聯
    // 5. 開始跑
    // 5-1. init   parent & owner
    // 5-2. unions find, 把 account 內的關聯 parent 都建立起來, idx = 1 是 idx 2.3.. 後面的 parent, 以第一個 email 找到 parent, 第 2,3.. email parent 也都掛給這個 parent
    // 5-3. 把 union find 結果寫入 unios, 就有結果了
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unios = new HashMap<>();
        for (List<String> a : accounts) {
            for (int i = 1; i < a.size(); i++) {
                parents.put(a.get(i), a.get(i));
                owner.put(a.get(i), a.get(0));
            }
        }
        for (List<String> a : accounts) {
            String p = find(a.get(1), parents);
            for (int i = 2; i < a.size(); i++)
                parents.put(find(a.get(i), parents), p);
        }
        for (List<String> a : accounts) {
            String p = find(a.get(1), parents);
            if (!unios.containsKey(p)) unios.put(p, new TreeSet<>());
            for (int i = 1; i < a.size(); i++)
                unios.get(p).add(a.get(i));
        }
        List<List<String>> res = new ArrayList<>();
        for (String p : unios.keySet()) {
            List<String> emails = new ArrayList<>(unios.get(p));
            emails.add(0, owner.get(p));
            res.add(emails);
        }
        return res;
    }

    String find(String s, Map<String, String> p) {
        return p.get(s).equals(s) ? s : find(p.get(s), p);
    }
}
