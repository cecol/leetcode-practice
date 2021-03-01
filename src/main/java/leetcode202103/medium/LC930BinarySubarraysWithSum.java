package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC930BinarySubarraysWithSum extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC930BinarySubarraysWithSum();
        LC.numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2);
    }

    /**
     * https://leetcode.com/problems/binary-subarrays-with-sum/discuss/186683/C%2B%2BJavaPython-Sliding-Window-O(1)-Space
     * atMost的含義是, array中只有 0, 1
     * 所以atMost s是計算 所有subarray的sum < s的個數
     * subarray就是sliding window,
     * sliding window pointer i, j, s =2
     * i固定, j一直擴大, 邊擴大邊算sum, 過程中 就計算到
     * s = 0的可能性的subarray sum
     * s = 1的可能性的subarray sum
     * s = 2的可能性的subarray sum
     * 因為計算的是一直累加, array(sliding window) 每擴大一次就res加上去
     * 直到超過s=2, i 就往前縮表
     * 因此這是 atMost的含義
     * 所以 atMost(3) 包含 0, 1, 2 case -> 因此要扣除 atMost(2)
     * 所以 atMost的含義就是
     * 1. sliding window 在長大過程中一直累加可能性, 把子可能性都加總出來, 直到超過上限, 開始縮表
     * 2. 這樣區間內的可能性都有算到, 但也多算到 小於要求的s 因此需要再扣除掉
     * 特點就是利用sliding window 都是從小開始往外長得特性來解決此問題
     *
     * 所以看得出來如果要算 有多少種可能性subarray(sliding window)符合某條件, 就可以用atMost
     * 但如果是其他版本的sliding window(max, min)可能就不是 atMost可以解了
     */
    public int numSubarraysWithSum(int[] A, int S) {
        return atMost(A, S) - atMost(A, S - 1);
    }

    private int atMost(int[] A, int s) {
        if (s < 0) return 0;
        int res = 0, i = 0;
        for (int j = 0; j < A.length; j++) {
            s -= A[j];
            while (s < 0) s += A[i++];
            res += j - i + 1;
        }
        return res;
    }
}
