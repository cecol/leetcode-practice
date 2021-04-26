package leetcode202104.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC149MaxPointsOnALine extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC149MaxPointsOnALine();
    }

    /**
     * https://leetcode.com/problems/max-points-on-a-line/discuss/47113/A-java-solution-with-notes
     * 其實是蠻直觀的解法, 透過斜率來找出是否在同線上 但還是有細節要注意
     * 1. 每一個點都要跟後面的點比對, 所以還是O(n^2)
     * 2. 有可能給的點都是同一點, 所以要計算duplicate, 最後拿出來統計
     * 3. Since a is a rational, there exists y0 and x0, y0/x0=(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a
     * -> 既然兩點之間有個斜率, 那們更存在 y0/x0, 就是GCD is used to map equal slopes to identical.
     * -> 就是說可以直接拿 deltaY/deltaX 來算出斜率, 但是要處理 deltaX == 0 case
     * -> 或者相對取出 deltaX, deltaY 最大公因數, 個別除以最大公因數 -> 就是保留 deltaY/deltaX in Map
     * -> 省去處理 deltaX == 0 case
     * 4. res = Math.max(res, max + dup + 1);
     * -> 其中 max + dup + 1 -> max是找出的點的斜率長度, dup是有多個i點都要納入, 1是i點自己
     * */
    public int maxPoints(int[][] points) {
        if (points == null) return 0;
        if (points.length < 3) return points.length;

        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> map = new HashMap<>();
            int dup = 0;
            int max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int deltaX = points[j][0] - points[i][0];
                int deltaY = points[j][1] - points[i][1];
                if (deltaX == 0 && deltaY == 0) {
                    dup++;
                    continue;
                }
                int gcd = gcd(deltaX, deltaY);
                int dX = deltaX / gcd;
                int dY = deltaY / gcd;
                String k = dX + "," + dY;
                map.put(k, map.getOrDefault(k, 0) + 1);
                max = Math.max(map.get(k), max);
            }

            res = Math.max(res, max + dup + 1);
        }
        return res;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
}
