package leetcode_old;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow {
    public static void main(String[] s) {
        int[][] n1 = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        List<int[]> res = pacificAtlantic(n1);
        for (int[] i : res) System.out.print("[" + i[0] + "," + i[1] + "] ");
    }

    static int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> list = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return list;
        int n = matrix.length, m = matrix[0].length;
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, m - 1);
        }
        for (int i = 0; i < m; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, n - 1, i);
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (pacific[i][j] && atlantic[i][j])
                    list.add(new int[]{i, j});
        return list;
    }

    public static void dfs(int[][] matrix, boolean[][] visited, int h, int x, int y) {
        int n = matrix.length, m = matrix[0].length;
        if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y] || h > matrix[x][y]) return;
        visited[x][y] = true;
        for (int[] i : dir) dfs(matrix, visited, matrix[x][y], x + i[0], y + i[1]);
    }
}
