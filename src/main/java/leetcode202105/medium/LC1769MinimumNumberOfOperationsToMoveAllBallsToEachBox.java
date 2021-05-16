package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1769MinimumNumberOfOperationsToMoveAllBallsToEachBox extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1769MinimumNumberOfOperationsToMoveAllBallsToEachBox();
    }

    /**
     * 蠻直觀解法, 就是左右看哪些是1 可以移過來, 加總 offset
     * 143 ms, faster than 38.46%
     * 中途有想過, 應該可以參考左右邊, 算兩次應該更快, 只是當下想說先解出來看看
     * https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/discuss/1075595/Java%3A-O(n)-left-sum-and-right-sum
     * 分開 new int[] left = new int[n]; int[] right = new int[n]; 個別計算
     * 補充一下 為什麼 r[i] = r[i + 1] + c; or l[i] = l[i - 1] + c;
     * 因為 l[i] 一定是 l[i-1] 的那批再多走一步, 所以那批有多少個就走多少步, 多少個就是 count, 因此 l[i] = l[i - 1] + c
     *
     */
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] res = new int[n], l = new int[n], r = new int[n];
        int c = boxes.charAt(0) - '0';
        for (int i = 1; i < n; i++) {
            l[i] = l[i - 1] + c;
            c += boxes.charAt(i) - '0';
        }

        c = boxes.charAt(n - 1) - '0';
        for (int i = n - 2; i >= 0; i--) {
            r[i] = r[i + 1] + c;
            c += boxes.charAt(i) - '0';
        }
        for (int i = 0; i < n; i++) res[i] += l[i] + r[i];
        return res;
    }

    public int[] minOperationsSlow(String boxes) {
        int n = boxes.length();
        int[] res = new int[n];
        if (n == 1) return res;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (boxes.charAt(j) == '1') {
                    res[i] += j - i;
                }
            }

            for (int j = i - 1; j >= 0; j--) {
                if (boxes.charAt(j) == '1') {
                    res[i] += i - j;
                }
            }
        }

        return res;
    }
}
