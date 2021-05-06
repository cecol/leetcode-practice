package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC42TrappingRainWater extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC42TrappingRainWater();
    }

    /**
     * https://leetcode.com/problems/trapping-rain-water/discuss/17391/Share-my-short-solution.
     * 第一次看到這題也是傻了, 完全沒頭緒
     * 後來看到解答發現其實解法很有意思, 只是我不熟悉這種題型與思考方式
     * 1. 從最左跟最右開始內縮, 由小的一邊開始內縮 -> 因為小的那邊才可以積水 -> 左 < 右 所以一定積水在左邊
     * 2. 每內縮一次, 就計算一次這次內縮可以積多少水回來
     * ex: height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * -> 其實概念很簡單, 一開始 int leftMax = height[0] = 0; leftMax 小, 所以 left內縮
     * -> left一開是1, 會拿 leftMax, height[left] 比 max, 如果
     * -> 1. height[left] 贏, 代表根本沒積水, water+=0, leftMax 變成 height[left] -> water += leftMax - height[left] = 0;
     * -> 2. 原本的leftMax 贏, 現在leftMax右邊可以積水(因為更前面檢查過 leftMax <= rightMax, 所依定最右邊有靠山可以當著)
     * -> -> 所以可以積水的就是  water += leftMax - height[left]; height[left] 是地面, 地面跟 leftMax的高度差就是可以積水的總量
     * 所以就此類推下去
     * 每次 left or right 內縮都會檢查並更新 leftMax & rightMax, 並且跟網內縮的 left & right過對比較看看內縮一格是否能積水
     * 注意！！ while (left <= right) 這才是對的
     * while (left < right) 是錯的 -> 因為會少算一次可積水的狀況
     * */
    public int trap(int[] height) {
        if (height.length < 3) return 0;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int water = 0;
        int left = 1;
        int right = height.length - 2;
        while (left <= right) {
            if (leftMax <= rightMax) {
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }
}
