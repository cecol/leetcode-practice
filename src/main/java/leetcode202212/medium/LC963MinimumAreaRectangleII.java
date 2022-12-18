package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LC963MinimumAreaRectangleII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC963MinimumAreaRectangleII();
    }

    /**
     * https://leetcode.com/problems/minimum-area-rectangle-ii/solutions/208470/java-o-n-3-bruteforce/
     * 其實這題在問找出所有能形成舉行的面積  然後回傳最小的
     * 這題沒什麼特別的, 是要有辦法找出矩形  要知道矩形的特性
     * <p>
     * 太久沒算矩形很多特型都不熟悉
     * 1. 其實就是 for loop 3層 都是走過 points - 找出所有 point 所有組合比對中 剛好
     * - dist(p1, p3) + dist(p2, p3) == dist(p1, p2),
     * - p1,p2,p3 若能形成 直角三角形 - 畢氏定理 - 就有機會 只剩下 p4 組出 矩形
     * - 如何找 p4?
     * - int x = p1[0] + p2[0] - p3[0];
     * - int y = p1[1] + p2[1] - p3[1];
     * - 矩形 對角線 中間點相同 x(p4[0]) + p3[0] = p1[0] + p2[0];
     * - 透過一開始把所有點都放進 set , 找出 p4 存在否, 就可以算面積
     */
    public double minAreaFreeRect(int[][] points) {
        Set<String> set = new HashSet<>();
        for (int[] p : points) {
            set.add(p[0] + " " + p[1]);
        }
        double res = Double.MAX_VALUE;
        for (int[] p1 : points) {
            for (int[] p2 : points) {
                if (p1[0] == p2[0] && p1[1] == p2[1]) continue;
                for (int[] p3 : points) {
                    if (dist(p1, p3) + dist(p2, p3) != dist(p1, p2)) continue;
                    int x = p1[0] + p2[0] - p3[0];
                    int y = p1[1] + p2[1] - p3[1];
                    if (!set.contains(x + " " + y)) continue;
                    double area = Math.sqrt(dist(p1, p3)) * Math.sqrt(dist(p2, p3));
                    if (Double.compare(area, 0) == 0) continue;
                    res = Math.min(res, area);
                }
            }
        }
        return Double.compare(Double.MAX_VALUE, res) == 0 ? 0 : res;
    }

    int dist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
