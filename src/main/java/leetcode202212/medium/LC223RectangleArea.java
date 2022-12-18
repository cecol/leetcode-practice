package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC223RectangleArea extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC223RectangleArea();
    }

    /**
     * https://leetcode.com/problems/rectangle-area/solutions/62138/my-java-solution-sum-of-areas-overlapped-area/
     * 其實是很直觀的 算出 areaA areaB 減去 overlap
     * 不過這種找出 overlap 的找法要寫乾淨 其實沒這麼直觀
     *
     * 我們知道 左邊都是在 ax1, bx1, 右邊是在 ax2, bx2
     * 所以找 overlap left 就是 ax1/bx1 往內縮, 所以是找 max(ax1, bx1)
     * 所以找 overlap right 就是 ax2/bx2 往內縮, 所以是找 min(ax2, bx2)
     * 所以找 overlap top 就是 ay2/by2 往內縮, 所以是找 min(ay2, by2)
     * 所以找 overlap down 就是 ax1/bx1 往內縮, 所以是找 max(ax1, bx1)
     *
     * 如果 left > right && down > top  代表沒有 overlap
     * */
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int areaA = (ax2 - ax1) * (ay2 - ay1);
        int areaB = (bx2 - bx1) * (by2 - by1);

        int left = Math.max(ax1, bx1);
        int right = Math.min(ax2, bx2);
        int down = Math.max(ay1, by1);
        int top = Math.min(ay2, by2);

        int overlap = 0;
        if (right > left && top > down) overlap = (right - left) * (top - down);
        return areaA + areaB - overlap;
    }
}
