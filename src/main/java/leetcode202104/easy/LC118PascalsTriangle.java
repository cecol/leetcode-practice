package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC118PascalsTriangle extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC118PascalsTriangle();
        LC.generate(5);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int e = 0; e < i + 1; e++) cur.add(1);
            if (i > 1) {
                List<Integer> pre = res.get(i - 1);
                for (int e = 1; e < cur.size() - 1; e++) {
                    cur.set(e, pre.get(e - 1) + pre.get(e));
                }
            }
            res.add(cur);
        }
        for (List<Integer> r : res) log.debug("{}", r);
        return res;
    }
}
