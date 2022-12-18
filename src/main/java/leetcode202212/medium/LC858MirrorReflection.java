package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC858MirrorReflection extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC858MirrorReflection();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Math/858.Mirror-Reflection
     * 很不直觀的一題, 主要是要先把特性把握清楚
     * 關鍵是要釐清 p/q 到底關係是什麼
     * 1. 這是一個 正方形內 的無限反射, "正方形邊長是 p" !!
     * - 想想如果 p = 2q, 就會反射到 接受器 1
     * - or p = kq, 就會反射到 接收器 1 or 2
     * 2. 但若 p 不是 q 倍數 or q > p, 就會變成上下反射
     * 這時候要鏡像處理
     * It can be expanded to this...
     * ...
     * |-0
     * | |
     * 2-1
     * | |
     * |-0
     * | |
     * 2-1
     * | |
     * |-0
     * you just need to know ?q=kp (?,k belong to Z)
     *
     * 很多解法都是算 gcd 之類的
     * 但我覺得這個比較直觀
     * https://leetcode.com/problems/mirror-reflection/solutions/141764/accepted-java-solution/
     * 就是看 p 每次減去 q 還剩多少, 如果超界(變成負值) 就在補 p (remain += p)
     * 然後用 boolean up = true, east = true; 控制左右 上下
     *
     * 每次反射 east = !east; 左右相反
     * if (remain < 0) 代表進入鏡射 上下相反
     * 直到 if (remain == 0)
     */
    public int mirrorReflection(int p, int q) {
        boolean up = true, east = true;
        int remain = p;
        while (true) {
            remain -= q;
            if (remain == 0) {
                if (up)
                    if (east) return 1;
                    else return 2;
                else return 0;
            }
            if (remain < 0) {
                remain += p;
                up = !up;
            }
            east = !east;
        }
    }
}
