package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class LC497RandomPointInNonOverlappingRectangles extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC497RandomPointInNonOverlappingRectangles();
    }

    class Solution {

        /**
         * https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/solutions/805219/simple-short-and-clean-java-solution/
         * 原本以為這題是用 reject samping, 但會 TLE, 因為要一個一個 矩形都拿去檢查能不能用
         *
         * 結果這題是
         * 先把所有矩形 area 算起來, preSum 加總 放到 TreeMap<Integer, int[]>
         * key = area preSum, value = 矩形 座標
         * 然後 rand.nextInt(areaSum + 1); 選取 areaSum 區間的隨機數 用 TreeMap 得知是落在哪個矩形
         * 然後在那個矩形內 隨機取得
         * x = rand.nextInt(rect[2] - rect[0] + 1) + rect[0];
         * 就是答案
         */
        TreeMap<Integer, int[]> tm = new TreeMap<>();
        int areaSum = 0;
        Random rand = new Random();

        public Solution(int[][] rects) {
            for (int[] r : rects) {
                areaSum += (r[2] - r[0] + 1) * (r[3] - r[1] + 1);
                tm.put(areaSum, r);
            }
        }

        public int[] pick() {
            int randArea = rand.nextInt(areaSum + 1);
            int[] selectedRect = tm.ceilingEntry(randArea).getValue();
            int x = rand.nextInt(selectedRect[2] - selectedRect[0] + 1) + selectedRect[0];
            int y = rand.nextInt(selectedRect[3] - selectedRect[1] + 1) + selectedRect[1];
            return new int[]{x, y};
        }
    }
}
