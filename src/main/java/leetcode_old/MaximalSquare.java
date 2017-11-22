package leetcode_old;

import java.util.Stack;


/**
 * https://leetcode.com/problems/maximal-square/#/description
 * basically: similar with LargestRectangleInHistogram
 */
public class MaximalSquare {
    public static void main(String[] args) {
    }

    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] h = new int[matrix[0].length];
        for (int i = 0; i < h.length; i++) {
            if (matrix[0][i] == '1') h[i] = 1;
            else h[i] = 0;
        }
        int max = maxSquareHistogram(h);
        for (int i = 1; i < matrix.length; i++) {
            h = nextHistogram(matrix[i], h);
            max = Math.max(max, maxSquareHistogram(h));
        }
        return max;
    }


    public static int[] nextHistogram(char[] matrixColumn, int[] h) {
        for (int i = 0; i < h.length; i++) {
            if (matrixColumn[i] == '1') h[i] += 1;
            else h[i] = 0;
        }
        return h;
    }

    public static int maxSquareHistogram(int[] h) {
        if (h == null || h.length == 0) return 0;
        int max = 0;
        Stack<Integer> s = new Stack<Integer>();
        for (int i = 0; i <= h.length; i++) {
            int hi = (i == h.length ? 0 : h[i]);
            if (s.isEmpty() || hi >= h[s.peek()]) {
                s.push(i);
            } else {
                int tp = s.pop();
                int l = (s.isEmpty() ? i : i - 1 - s.peek());

                max = Math.max(max, h[tp] * (l >= h[tp] ? h[tp] : 0));
                i--;
            }
        }

        return max;
    }
}
