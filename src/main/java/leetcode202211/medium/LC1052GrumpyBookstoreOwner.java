package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class LC1052GrumpyBookstoreOwner extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1052GrumpyBookstoreOwner();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/1052.Grumpy-Bookstore-Owner
     * 看到解釋才恍然大悟
     * 1. 先算基本盤 sum, 只要 grumpy[i] == 0, 就先加起來
     * 2. 維持一個 window size = minutes, 這裡面 grumpy[i] == 1 都是額外補上
     * window 往前走的時候, 得看看前面跑出 window 外的是否要補回去
     * 然後算 max
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int sum = 0;
        for (int j = 0; j < n; j++) if (grumpy[j] == 0) sum += customers[j];
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (grumpy[j] == 1) sum += customers[j];
            if (j >= minutes && grumpy[j - minutes] == 1) sum -= customers[j - minutes];
            res = Math.max(res, sum);
        }
        return res;
    }
}
