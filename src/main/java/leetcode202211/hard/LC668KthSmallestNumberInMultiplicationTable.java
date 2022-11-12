package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC668KthSmallestNumberInMultiplicationTable extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC668KthSmallestNumberInMultiplicationTable();
    }

    /**
     * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/discuss/1580533/C%2B%2BJavaPython-Short-Binary-Search-Solution-with-Explanation
     * 這題 binary search 上下邊界找 k 很直觀 , 但如何找出 matrix 中 哪些 <= mid ?
     * 當然我們可以 for loop m*x 把所有算出來 count, 但會 TLE
     * for (int i = 1; i <= m; i++) c += Math.min(n, mid / i); 這段怎麼解讀?
     * 其實就是走過所有 row, 每一 row, 每一個值 都是 row-i * column-j < mid
     * 所以 row-i 要嘛全拿 total n 個小於 或者 只拿到 mid/i => column-j 只會走到 mid/i, 再往後走 i*j > mid, 就不是要的了！！
     * 所以結果是拿 Math.min(n, mid / i)
     * 解釋: https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/discuss/106977/Java-solution-binary-search/109248
     * */
    public int findKthNumber(int m, int n, int k) {
        int l = 1, r = m * n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int c = 0;
            for (int i = 1; i <= m; i++) c += Math.min(n, mid / i);
            if (c >= k) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
