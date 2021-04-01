package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.Arrays;

public class LC1465MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1465MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts();
        LC.maxArea(8, 5, new int[]{5, 2, 6, 3}, new int[]{1, 4});
    }

    /**
     * https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/discuss/661644/C%2B%2BJava-Maximum-Gap-Between-Cuts
     * 想不到這麼直觀一提我解這麼久還解錯
     * 1. 根本不用去用一個 int[][] 去記載每一個切出來的寬度高度, 只要走過要切的, 個別記下最大值就好
     * -> 每一個 h,v切出來的都可以互相組合 -> 去畫圖就明白了
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int hn = horizontalCuts.length;
        int vn = verticalCuts.length;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int maxH = Math.max(horizontalCuts[0], h - horizontalCuts[hn - 1]);
        int maxV = Math.max(verticalCuts[0], w - verticalCuts[vn - 1]);
        for (int i = 0; i < hn - 1; i++) maxH = Math.max(maxH, horizontalCuts[i + 1] - horizontalCuts[i]);
        for (int i = 0; i < vn - 1; i++) maxV = Math.max(maxV, verticalCuts[i + 1] - verticalCuts[i]);
        return (int) ((long) maxH * maxV % 1000000007);
    }
}
