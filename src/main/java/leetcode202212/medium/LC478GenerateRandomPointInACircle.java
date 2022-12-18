package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.Stack;

public class LC478GenerateRandomPointInACircle extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC478GenerateRandomPointInACircle();
    }

    class Solution {


        /**
         * https://leetcode.com/problems/generate-random-point-in-a-circle/solutions/1115153/standard-java-solution/
         * 這題還是 2 個做法
         * 1. reject sampling, 隨機選 x/y 然後看有沒有在圈內 有的話就回傳
         * 但我直觀這樣做會 TLE, 反而這個做法不會
         * 它是限制產生的 x/y 一定落在剛好 circle 的 正方形內
         * 所以比較容易讓結果 落在 circle 內
         * 先求  circle 的 正方形 的 最左下角 座標
         * - double leftX = x - radius, leftY = y - radius;
         * 在 random x/y 剛好落在 circle 的 正方形範圍內
         * - double randX = Math.random() * radius * 2 + leftX;
         * - Math.random() 產生 0 to 1.0 之間數字
         * - radius * 2 正方形 邊長
         * - + leftX -> 移動到正方形內
         *
         * 2. 用極座標概念, 不過不太懂就跳過
         * */
        double x, y, radius;

        public Solution(double radius, double x_center, double y_center) {
            x = x_center;
            y = y_center;
            this.radius = radius;
        }

        public double[] randPoint() {
            double leftX = x - radius, leftY = y - radius;
            while (true) {
                double randX = Math.random() * radius * 2 + leftX;
                double randY = Math.random() * radius * 2 + leftY;
                double dist = Math.sqrt(Math.pow(randX - x, 2) + Math.pow(randY - y, 2));
                if(dist < radius) return new double[]{randX, randY};
            }
        }

    }
}
