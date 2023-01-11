package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC1272RemoveInterval extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1272RemoveInterval();
    }

    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] i : intervals) {
            if (i[1] < toBeRemoved[0] || i[0] > toBeRemoved[1]) res.add(List.of(i[0], i[1]));
            else {
                if (toBeRemoved[0] - i[0] > 0) res.add(List.of(i[0], toBeRemoved[0]));
                if (i[1] - toBeRemoved[1] > 0) res.add(List.of(toBeRemoved[1], i[1]));
            }
        }
        return res;
    }
}
