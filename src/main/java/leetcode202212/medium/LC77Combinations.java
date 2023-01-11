package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC77Combinations extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC77Combinations();
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        dfs(res, new ArrayList<>(), k, n, 1);
        return res;
    }

    void dfs(List<List<Integer>> res, ArrayList<Integer> cur, int k, int n, int s) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = s; i <= n; i++) {
            cur.add(i);
            dfs(res, cur, k, n, i + 1);
            cur.remove(cur.size()-1);
        }
    }
}
