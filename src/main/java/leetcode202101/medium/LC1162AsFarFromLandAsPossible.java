package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.LinkedList;
import java.util.Queue;

public class LC1162AsFarFromLandAsPossible extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1162AsFarFromLandAsPossible();
        var s = LC.maxDistance(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}});
    }

    /**
     * 這題是蠻基本的BFS, 但我第二次還是沒做對
     * 因為我方向想錯了
     * grid中 0 -> 水, 1 -> 土
     * 找出水距離土最遠的
     * 由土開始BFS擴散往外找 -> 所以土要改成0
     * 這樣土往外的值如果是水就是土的0+1 然後再把該水的座標加入 bfs queue
     * 而水要初始成Integer.MAX_VALUE
     * 已走過的就用 grid[mx][my] <= grid[v[0]][v[1]]來判定
     * 最遠水是被其他水給包住了
     *
     * 原本我以為水初始化應該是 -1
     * 然後用 grid[mx][my] > grid[v[0]][v[1]]來判定
     * 但這樣應該會造成水與土的距離是算錯的, 水旁邊有土就一定是 1, 被其他水包住的水才有機會距離更遠 -> BFS
     *
     * 正確思考方式應該是, 反過來是計算出每個水離土的距離, 然後拿出max
     * */
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> bfsVisited = new LinkedList<>();
        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    bfsVisited.offer(new int[]{i, j});
                } else grid[i][j] = Integer.MAX_VALUE;
            }
        }
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        while (!bfsVisited.isEmpty()) {
            int[] v = bfsVisited.poll();
            for (int[] m : dirs) {
                int mx = v[0] + m[0];
                int my = v[1] + m[1];
                if (mx < 0 || my < 0 || mx == n || my == n || grid[mx][my] <= grid[v[0]][v[1]]) continue;
                bfsVisited.add(new int[]{mx, my});
                grid[mx][my] = grid[v[0]][v[1]] + 1;
                max = Math.max(max, grid[mx][my]);
            }
        }
        return max;
    }
}
