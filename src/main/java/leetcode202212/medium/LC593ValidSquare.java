package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LC593ValidSquare extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC593ValidSquare();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Math/593.Valid-Square
     * 其實蠻多解法的  但這個比較直觀
     * 把 所有邊 兩兩算距離放進 set, 最終應該只會有兩種可能  且不帶 0 length
     * */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<Integer> len = new HashSet<>();
        len.add(dist(p1, p2));
        len.add(dist(p1, p3));
        len.add(dist(p1, p4));
        len.add(dist(p2, p3));
        len.add(dist(p2, p4));
        len.add(dist(p3, p4));
        if (len.contains(0) || len.size() != 2) return false;
        return true;
    }

    int dist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
