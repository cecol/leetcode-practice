package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC85MaximalRectangle extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC85MaximalRectangle();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Stack/085.Maximal-Rectangle
     * https://github.com/wisdompeak/LeetCode/blob/master/Stack/085.Maximal-Rectangle/85-Maximal-Rectangle.cpp
     * 完全沒想過這題會跟 LC84LargestRectangleInHistogram 一模一樣, 只是 2維版本
     * 1. 每一 row 當作水平下去計算出 Histogram 然後丟入 LC84LargestRectangleInHistogram 來找
     * 2. 如果過程中 row 當作水平下去計算出 Histogram, 遇到 0, 該 col Histogram 歸 0
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] hist = new int[n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') hist[j]++;
                else hist[j] = 0;
            }
            res = Math.max(res, maxArea(hist));
        }
        return res;
    }

    int maxArea(int[] hist) {
        Stack<Integer> sk = new Stack<>();
        int res = 0;
        for (int i = 0; i <= hist.length; i++) {
            int v = i == hist.length ? 0 : hist[i];
            while (sk.size() > 0 && v < hist[sk.peek()]) {
                int h = hist[sk.pop()];
                int w = i - (sk.size() == 0 ? 0 : sk.peek() + 1);
                res = Math.max(res, h * w);
            }
            sk.push(i);
        }
        return res;
    }
}
