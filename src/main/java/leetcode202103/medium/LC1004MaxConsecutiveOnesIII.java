package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1004MaxConsecutiveOnesIII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1004MaxConsecutiveOnesIII();
        LC.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2);
    }

    /**
     * 因為先解過424所以理解這樣問題的基本解法
     * 基本概念就是 sliding window 的總計算再配上K 是合適的就看一下是否是max 然後繼續擴增
     * 如果sliding window 的總計算再配上K 是不合適的, 代表要縮表
     * */
    public int longestOnes(int[] A, int K) {
        int s = 0, i = 0, res = 0;
        for (int j = 0; j < A.length; j++) {
            s += A[j];
            while (j - i + 1 - s > K) s -= A[i++];
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
