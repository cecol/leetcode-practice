package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC499TheMazeIII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC499TheMazeIII();
//        LC.findShortestWay(
//                new int[][]{{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}},
//                new int[]{4, 3},
//                new int[]{0, 1});
        LC.findShortestWay(
                new int[][]{{0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 1, 0}, {0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 1}},
                new int[]{0, 4},
                new int[]{2, 0});
    }

    class Node {
        int x, y, dist;
        String ins;

        Node(int i, int j, int d, String s) {
            x = i;
            y = j;
            dist = d;
            ins = s;
        }
    }

    /**
     * LC505TheMazeII 延伸 觀念 95% 相同, 只是要比較除了距離還有累計的走向字串
     * minheap 是長這樣
     * - PriorityQueue<Node> bfs = new PriorityQueue<>((x, y) -> {
     * -          if (x.dist != y.dist) return x.dist - y.dist;
     * -          else return x.ins.compareTo(y.ins);
     * -      });
     * 還有如果 一直往前走時候
     * - while (xx > 0 && maze[xx - 1][y] != 1) {
     * -   xx--;
     * -   if (xx == hole[0] && y == hole[1]) break;
     * - }
     * 得注意是否走到 hole 就得先暫離
     * <p>
     * 我花很多時間在於 debug, 因為以為 x 移動是 l/r 但 x 移動其實是 u/d 完全寫反！！
     * x 是 row 是 up/down
     * y 是 col 是 left/right
     */
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        PriorityQueue<Node> bfs = new PriorityQueue<>((x, y) -> {
            if (x.dist != y.dist) return x.dist - y.dist;
            else return x.ins.compareTo(y.ins);
        });
        bfs.offer(new Node(ball[0], ball[1], 0, ""));
        boolean[][] vs = new boolean[m][n];
        while (bfs.size() > 0) {
            Node cur = bfs.poll();
            vs[cur.x][cur.y] = true;
            if (cur.x == hole[0] && cur.y == hole[1]) return cur.ins;
            int x = cur.x, y = cur.y, xx = cur.x, yy = cur.y;

            while (xx > 0 && maze[xx - 1][y] != 1) {
                xx--;
                if (xx == hole[0] && y == hole[1]) break;
            }
            if (!vs[xx][y]) bfs.offer(new Node(xx, y, cur.dist + (x - xx), cur.ins + "u"));

            xx = x;
            while (xx < m - 1 && maze[xx + 1][y] != 1) {
                xx++;
                if (xx == hole[0] && y == hole[1]) break;

            }
            if (!vs[xx][y]) bfs.offer(new Node(xx, y, cur.dist + (xx - x), cur.ins + "d"));

            while (yy > 0 && maze[x][yy - 1] != 1) {
                yy--;
                if (x == hole[0] && yy == hole[1]) break;
            }
            if (!vs[x][yy]) bfs.offer(new Node(x, yy, cur.dist + (y - yy), cur.ins + "l"));

            yy = y;
            while (yy < n - 1 && maze[x][yy + 1] != 1) {
                yy++;
                if (x == hole[0] && yy == hole[1]) break;
            }
            if (!vs[x][yy]) bfs.offer(new Node(x, yy, cur.dist + (yy - y), cur.ins + "r"));
        }
        return "impossible";
    }
}
