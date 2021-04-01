package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LC1197MinimumKnightMoves extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1197MinimumKnightMoves();
    }

    /**
     * https://leetcode.com/problems/minimum-knight-moves/discuss/401580/Clean-Java-BFS-solution
     * 沒想到這題還是沒解好
     * 1. BFS不夠熟, 忘記要設定 Set<String> visited
     * 2. 為了避免 TLE -> x, y 要先 Math.abs過, 因為其實往同一象限找比較快 所以乾脆直接x, y改成第一象限
     * -> 只有往第一象限內走才是最短路徑 -> mx >= -1 && my >= -1
     * 3. 有O(1)解, 雖然可能看得懂, 但我覺得面試應該想不出來
     * https://leetcode.com/problems/minimum-knight-moves/discuss/392053/Here-is-how-I-get-the-formula-(with-graphs)
     * 這是比較適合面試解
     */
    public int minKnightMoves(int x, int y) {
        int[][] dirs = new int[][]{{1, -2}, {-1, 2}, {-1, -2}, {1, 2}, {2, 1}, {-2, -1}, {-2, 1}, {2, -1}};
        x = Math.abs(x);
        y = Math.abs(y);
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        Set<String> v = new HashSet<>();
        v.add("0,0");
        int l = 0;
        while (q.size() > 0) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                int[] p = q.poll();
                if (p[0] == x && p[1] == y) return l;
                for (int[] dir : dirs) {
                    int mx = dir[0] + p[0];
                    int my = dir[1] + p[1];
                    String mm = mx + "," + my;
                    if (!v.contains(mm) && mx >= -1 && my >= -1) {
                        q.add(new int[]{mx, my});
                        v.add(mm);
                    }
                }
            }
            l++;
        }
        return -1;
    }
}
