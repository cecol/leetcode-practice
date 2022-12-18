package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC305NumberOfIslandsII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC305NumberOfIslandsII();
    }

    /**
     * https://leetcode.com/problems/number-of-islands-ii/solutions/75470/easiest-java-solution-with-explanations/
     * 想太複雜了 花一堆時間搞一些有的沒的 結果很簡單
     * 1. 每個 position x,y 轉成 x * n + y
     * 2. parent 是 int[] p = new int[m * n];
     * 3. parent p 初始都是 -1
     * 4. 帶一個 當前 island count 下去走過 positions
     * - 當前 positions[i], 轉成 pos[0] * n + pos[1]; 看看 p[pos[0] * n + pos[1]] == -1, 代表有無走過,
     * -    走過就是直接用 island count 放進 result list
     * - count++
     * - p[pos[0] * n + pos[1]] = pos[0] * n + pos[1];
     * -   看看當前 pos 四個方位 有無誰是 非 -1 代表有銜接,
     * -   檢查有無 parent 相同, 沒有的話就是 union && count--,
     * -   最後出去 island count 放進 result list
     * -
     * */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] p = new int[m * n];
        Arrays.fill(p, -1);
        int count = 0;
        List<Integer> res = new ArrayList<>();
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] pos : positions) {
            int root = pos[0] * n + pos[1];
            if (p[root] != -1) {
                res.add(count);
                continue;
            }
            count++;
            p[root] = root;
            for (int[] dir : dirs) {
                int mx = pos[0] + dir[0];
                int my = pos[1] + dir[1];
                if (mx < 0 || my < 0 || mx >= m || my >= n || p[mx * n + my] == -1) continue;
                int connected = mx * n + my;
                if(f(p, connected) != root) {
                    count--;
                    union(p, root, connected);
                }
            }
            res.add(count);
        }
        return res;
    }

    int f(int[] p, int i) {
        while (p[i] != i) {
            p[i] = p[p[i]];
            i = p[i];
        }
        return p[i];
    }

    void union(int[] p, int i, int j) {
        int pi = f(p, i);
        int pj = f(p, j);
        if (pi != pj) {
            p[pj] = pi;
        }
    }
}
