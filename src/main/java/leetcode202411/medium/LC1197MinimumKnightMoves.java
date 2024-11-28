package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LC1197MinimumKnightMoves extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 記得大致上 BFS, 但忘記
    // 1. 可以 x = Math.abs(x);
    //        y = Math.abs(y);
    // 2. if (!visit.contains(code) && mx >= -1 && my >= -1) { 來縮限方向
    public int minKnightMoves(int x, int y) {
        int[][] dirs = new int[][]{{1, -2}, {-1, 2}, {-1, -2}, {1, 2}, {2, 1}, {-2, -1}, {-2, 1}, {2, -1}};
        x = Math.abs(x);
        y = Math.abs(y);
        Queue<int[]> bfs = new LinkedList<>();
        bfs.offer(new int[]{0, 0});
        int res = 0;
        Set<String> visit = new HashSet<>();
        visit.add("0,0");
        while (!bfs.isEmpty()) {
            int size = bfs.size();
            for (int i = 0; i < size; i++) {
                int[] v = bfs.poll();
                if (v[0] == x && v[1] == y) return res;
                for (int[] dir : dirs) {
                    int mx = v[0] + dir[0];
                    int my = v[1] + dir[1];
                    String code = mx + "," + my;
                    if (!visit.contains(code) && mx >= -1 && my >= -1) {
                        visit.add(code);
                        bfs.add(new int[]{mx, my});
                    }
                }
            }
            res++;
        }
        return -1;
    }
}
