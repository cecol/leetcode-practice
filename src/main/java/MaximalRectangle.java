import java.util.Stack;

/**
 * https://leetcode.com/problems/maximal-rectangle/#/description
 * reference solution: https://discuss.leetcode.com/topic/21772/my-java-solution-based-on-maximum-rectangle-in-histogram-with-explanation/2
 * basically: treat it as Largest Rectangle In Histogram, go through each row for Largest Rectangle
 */
public class MaximalRectangle {
    public static void main(String[] args) {
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        //calculate first row
        int height[] = new int[matrix[0].length];
        for (int i = 0; i < height.length; i++) {
            if (matrix[0][i] == '1') height[i] = 1;
        }
        //calculate rest row
        int max = LargestRectangleInHistogram.largestRectangleArea2(height);
        for (int i = 1; i < matrix.length; i++) {
            restHeight(matrix, height, i);
            max = Math.max(max, LargestRectangleInHistogram.largestRectangleArea2(height));
        }
        return max;
    }

    /**
     * height: height for x-th rows
     * idx: row position
     * */
    public static void restHeight(char[][] matrix, int[] height, int idx) {
        for (int i = 0; i < matrix[0].length; i++) {
            if(matrix[idx][i] == '1') height[i] += 1;
            else height[i] = 0; //if that char is '0', we don't need to care about area, cuz of it is calculated previous row
            //and if next row is '1', it need to restart from 1
        }
    }
}
