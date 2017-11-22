package leetcode_old;

import java.util.LinkedList;
import java.util.Queue;

public class Matrix01 {
    public static void main(String[] a) {
        int[][] n1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        updateMatrix(n1);
    }

    public static int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return matrix;
        int m = matrix.length, n = matrix[0].length;
        Queue<int[]> bfs = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    bfs.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!bfs.isEmpty()) {
            int[] cell = bfs.poll();
            for (int[] d : direction) {
                int leftRight = cell[0] + d[0];
                int upDown = cell[1] + d[1];
                if (leftRight < 0 || leftRight >= m || upDown < 0 || upDown >= n || matrix[leftRight][upDown] <= matrix[cell[0]][cell[1]] + 1) continue;
                bfs.add(new int[]{leftRight, upDown});
                matrix[leftRight][upDown] = matrix[cell[0]][cell[1]] + 1;
            }
        }

        return matrix;
    }
}
