package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

public class LC42TrappingRainWater extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 完全忘記, 直接看過去紀錄
    // 1. 左右內縮, 矮的先縮, 因為矮的那邊才是決定積水量, l = 1, r = size - 2 D9 G35L3
    // 2. 更新左右 MAX 內縮現況, max 跟當前比較(max 內縮有變, 代表積水重算, max 沒變 代表是積水區), 多出來的就是機會
    public int trap(int[] height) {
        if (height.length < 3) return 0;
        int leftMax = height[0], rightMax = height[height.length - 1];
        int water = 0;
        int l = 1, r = height.length - 2;
        while (l <= r) {
            if (leftMax <= rightMax) {
                leftMax = Math.max(leftMax, height[l]);
                water += leftMax - height[l];
                l++;
            } else {
                rightMax = Math.max(rightMax, height[r]);
                water += rightMax - height[r];
                r--;
            }
        }
        return water;
    }
}
