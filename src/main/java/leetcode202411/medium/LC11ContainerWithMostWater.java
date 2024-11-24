package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.LinkedList;

public class LC11ContainerWithMostWater extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 竟然還是想歪要用 Double Ended Queue
    // 這題應該是左右雙指針 內縮 找到 MAX 即可, 內縮的就是矮的指針優先縮小
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            if (height[i] > height[j]) j--;
            else i++;
        }
        return res;
    }
}
