package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC356LineReflection extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/line-reflection/discuss/82970/Simple-java-hashset-solution
     * 題意完全想錯
     * 我以為只能有一條線, 其實是可以很多條線, 只要能讓兩點 對稱 就好
     * 關鍵文字其實是
     * In other words, answer whether or not if there exists a line that after reflecting all points over the given line,
     * the original points' set is the same as the reflected ones.
     *
     * 每個點都找得到 對稱 就好
     * 所以 自己可以 對稱 自己
     * 或者 奇數點, 其中兩個對稱, 剩下一點自己跟自己 對稱
     *
     * 但透過找 max/min 來找  實在完全沒想過
     * Find max and min Point(x). The reflection line must be x = (max + min)/2.
     * min------p1--|--p2------max
     * For each point:
     * p1(x)-min = max - p2(x) => p1(x) + p2(x) = min + max
     * So the problem becomes two sum. To solve it, you can use HashSet or pre-sort array by x.
     *
     * Check all points that:
     * p1(x) = sum - p2(x) and p1(y) = p2(y)
     * So the key of Set can be "x, y"
     *
     */
    public boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<String> set = new HashSet<>();
        for (int[] p : points) {
            max = Math.max(max, p[0]);
            min = Math.min(min, p[0]);
            set.add(p[0] + "," + p[1]);
        }
        int sum = max + min;
        for (int[] p : points) {
            String reflected = (sum - p[0]) + "," + p[1];
            if(!set.contains(reflected)) return false;
        }

        return true;
    }
}
