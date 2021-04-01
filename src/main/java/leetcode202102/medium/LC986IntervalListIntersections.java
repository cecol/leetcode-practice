package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC986IntervalListIntersections extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC986IntervalListIntersections();
    }

    /**
     * https://leetcode.com/problems/interval-list-intersections/discuss/231122/Java-two-pointers-O(m-%2B-n)
     * 覺得是two pointer 但自己寫的亂七八糟 思路不完整
     * 看到答案才恍然大悟 自己想得太複雜
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList == null || firstList.length == 0 || secondList == null || secondList.length == 0)
            return new int[][]{};
        int fi = 0, si = 0, lastBegin, earlyEnd;
        List<int[]> res = new ArrayList<>();

        while (fi < firstList.length && si < secondList.length) {
            lastBegin = Math.max(firstList[fi][0], secondList[si][0]);
            earlyEnd = Math.min(firstList[fi][1], secondList[si][1]);
            if (earlyEnd - lastBegin >= 0) res.add(new int[]{lastBegin, earlyEnd});
            if (firstList[fi][1] == earlyEnd) fi++;
            if (secondList[si][1] == earlyEnd) si++;
        }
        return res.toArray(new int[res.size()][2]);
    }
}
