package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LC11ContainerWithMostWater extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC11ContainerWithMostWater();
    }

    /**
     * 這題我想太複雜了, 竟然想到用Deque來解
     * 基本上因為 max result 受於 window size影響, 以及當下 window 頭尾的參數
     * 那基本上就是先用最大 window 來找,
     * 矮的往內找 -> 代表穩內找如果 window 縮小  但換來比較高的高度以及更大的 range
     * 大概這種以最大固定的可能性 往內縮題目沒解過, 所以想太複雜了
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = Math.max(res, Math.min(height[i], height[j]) * j - i);
            if (height[i] > height[j]) j--;
            else i++;
        }
        return res;
    }
}
