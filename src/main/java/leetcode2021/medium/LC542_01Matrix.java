package leetcode2021.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.LinkedList;
import java.util.Queue;

public class LC542_01Matrix extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC542_01Matrix();
        var s = LC.updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}});
    }

    /**
     * https://leetcode.com/problems/01-matrix/discuss/101021/Java-Solution-BFS
     * 建立BFS visited queue -> 由'已知'的節點下去BFS擴散到其他'尚未觸及'的節點 -> 裡面是放visited過且是最小距離值的 x, y
     * 一開始先塞該位置是0的點 -> 0就是初始已知碰到0的節點
     * 其他點先塞 MAX,
     * 接著開始輪詢 bfs queue
     * 取出來的點 -> 看看4周 -> if 值 <= 目前點的距離 -> 跳過(因為該點已經最佳值)
     * if 值 > 目前點的距離 -> +1(因為就在最佳點旁邊) -> 把它放進bfs queue
     * */
    public int[][] updateMatrix(int[][] matrix) {
        int n = matrix.length;
        int n1 = matrix[0].length;
        Queue<int[]> bfsVisited = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n1; j++) {
                if (matrix[i][j] == 0) bfsVisited.offer(new int[]{i, j});
                else matrix[i][j] = Integer.MAX_VALUE;
            }
        }

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!bfsVisited.isEmpty()) {
            int[] v = bfsVisited.poll();
            for (int[] move : dirs) {
                int xm = v[0] + move[0];
                int ym = v[1] + move[1];
                if (xm < 0 || ym < 0 || xm >= n || ym >= n1 ||
                        matrix[xm][ym] <= matrix[v[0]][v[1]] + 1) continue;
                bfsVisited.add(new int[]{xm, ym});
                matrix[xm][ym] = matrix[v[0]][v[1]] + 1;
            }
        }
        return matrix;
    }
}
