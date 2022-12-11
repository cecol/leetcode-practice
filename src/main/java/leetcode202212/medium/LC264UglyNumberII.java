package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC264UglyNumberII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC264UglyNumberII();
    }

    /**
     * https://leetcode.com/problems/ugly-number-ii/solutions/69359/super-neat-java-solution-in-11ms-with-explanation/
     * ugly number 的因數只會是 2 or 3 or 5
     * 所以後續更多的 ugly number 一定是 已知道的 x2 or x3 or x5
     * 那麼怎麼 從 0th - nth 依序找出 ugly?
     * 因為 x2, x3, x5 跳的距離是不一樣的, 同一個數 x2, x3, x5 可能跳到 5th, 8th, 9th
     * 所以要依序找到 next ugly 得維持 x2_idx, x3_idx, x5_idx 三個位置不一樣, 因為每個可能是不同時候的 next ugly
     * 當要找 ith ugly,
     * 要先確保當前 ugly[x2_idx] x 2 >= ugly[i-1]
     * 要先確保當前 ugly[x3_idx] x 3 >= ugly[i-1]
     * 要先確保當前 ugly[x5_idx] x 5 >= ugly[i-1]
     * 然後 i 就是 min of x2_idx x 2 or x3_idx x 3 or x5_idx x 5
     *
     * */
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int c2 = 0, c3 = 0, c5 = 0;
        for (int i = 1; i < n; i++) {
            while (ugly[c2] * 2 <= ugly[i - 1]) c2++;
            while (ugly[c3] * 3 <= ugly[i - 1]) c3++;
            while (ugly[c5] * 5 <= ugly[i - 1]) c5++;
            ugly[i] = Math.min(ugly[c2] * 2, Math.min(ugly[c3] * 3, ugly[c5] * 5));
        }

        return ugly[n - 1];
    }
}
