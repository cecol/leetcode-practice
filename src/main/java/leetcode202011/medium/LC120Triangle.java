package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC120Triangle extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC120Triangle();
        var s = LC.minimumTotal(new ArrayList<List<Integer>>());
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> current = triangle.get(i);
            List<Integer> pre = triangle.get(i - 1);
            for (int j = 0; j < current.size(); j++) {
                int choose = 0;
                if (j == 0) choose = pre.get(0);
                else if (j == current.size() - 1) choose = pre.get(pre.size() - 1);
                else {
                    choose = Math.min(pre.get(j - 1), pre.get(j));
                }
                current.set(j, current.get(j) + choose);
            }
        }
        int min = triangle.get(triangle.size() - 1).get(0);
        for (Integer i : triangle.get(triangle.size() - 1)) min = Math.min(min, i);
        return min;
    }
}
