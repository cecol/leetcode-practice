package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC1902DepthOfBSTGivenInsertionOrder extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1902DepthOfBSTGivenInsertionOrder();
    }

    /**
     * https://leetcode.com/problems/depth-of-bst-given-insertion-order/discuss/1278293/Java-Simple-Brute-Force-%2B-Recursion
     * 是用暴力法解了, 沒想到 TLE case 用 monotonic 檢查就好
     * */
    public int maxDepthBST(int[] order) {
        List<Integer> t = new ArrayList<>();
        for (int n : order) t.add(n);
        return dfs(t);
    }

    int dfs(List<Integer> t) {
        if (t.size() == 0) return 0;
        if(isMono(t)) return t.size();
        int rt = t.get(0);
        List<Integer> l = new ArrayList<>();
        List<Integer> r = new ArrayList<>();
        for (int n : t) {
            if (n > rt) r.add(n);
            else if (n < rt) l.add(n);
        }
        return 1 + Math.max(dfs(l), dfs(r));
    }

    boolean isMono(List<Integer> t) {
        int i = 0;
        while (i < t.size() - 1 && t.get(i) < t.get(i + 1)) i++;
        if (i == t.size() - 1) return true;

        i = 0;
        while (i < t.size() - 1 && t.get(i) > t.get(i + 1)) i++;
        if (i == t.size() - 1) return true;
        return false;
    }
}
