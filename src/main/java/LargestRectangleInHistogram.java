import java.util.Stack;

/**
 * lee code question address
 * https://leetcode.com/problems/largest-rectangle-in-histogram/#/description
 */

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] c1 = {2, 1, 5, 6, 4, 3};
        int[] c2 = {2, 0, 2};
        int[] c3 = {0, 9};
        int[] c4 = {2, 1, 2};
        int[] c5 = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        int[] c6 = {2, 2, 2, 2, 2, 2, 2, 2, 2, 100};
        int[] c7 = {2, 1, 4, 5, 1, 3, 3};
        int[] c8 = {1, 2, 3, 4, 5, 6, 7};
        int[] c9 = {3, 6, 5, 7, 4, 8, 1, 0};
        int[] c10 = {10, 9, 8, 7, 6, 5, 4, 3};
        int[][] cc = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
        for (int i = 0; i < cc.length; i++) {
            int r = largestRectangleArea2(cc[i]);
            System.out.println("case:" + (i + 1) + ", result: " + r);
        }
    }

    public static int largestRectangleArea(int[] heights) {
        int max = 0;
        int count = 0;
        boolean isAscending = true;

        if (heights.length == 0) return max;
        if (heights.length == 1) return heights[0];
        /**
         * check for case: [1,2,3,4,5,6,7,8,9 ....]
         * improve performance, this is BigO(n)
         * */
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] == 0) continue;
            if (i != heights.length - 1 && heights[i] > heights[i + 1]) {
                isAscending = false;
                break;
            }
            int sum = heights[i] * (heights.length - i);
            if (sum > max) max = sum;
        }

        /**
         * for normal case: [2, 1, 5, 6, 2, 3]
         * you need to check each value, check left Histograms and ckeck right Histograms
         * BigO(n^2)
         * */
        if (!isAscending) {
            max = 0;
            for (int i = 0; i < heights.length; i++) {
                if (heights[i] == 0) continue;
                //go left
                for (int j = i - 1; j >= 0; j--) {
                    if (heights[j] == 0 || heights[j] < heights[i]) break;
                    else count += 1;
                }
                //go right
                for (int j = i; j < heights.length; j++) {
                    if (heights[j] == 0 || heights[j] < heights[i]) break;
                    else count += 1;
                    //for case [1,1,1,1,1,1,1,1....] can just jump to last
                    if (heights[j] == heights[i] && j == i + 1) i = j;
                }
                int sum = heights[i] * count;
                if (sum > max) max = sum;
                count = 0;
            }
        }
        return max;
    }

    //better solution
    public static int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) return 0;
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : height[i]);
            if (s.isEmpty() || h >= height[s.peek()]) {
                s.push(i);
            } else {
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }
}
