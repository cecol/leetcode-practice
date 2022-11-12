package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC275H_IndexII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC275H_IndexII();
        log.debug("{}", LC.hIndex(new int[]{0, 0}));
    }

    /**
     * https://leetcode.com/problems/h-index-ii/discuss/71124/Java-binary-search-simple-and-clean
     * 基本的 H index 概念是
     * citations[index] >= length(citations) - index.
     * length(citations) - index 就是指有多少個 citations > h
     * <p>
     * n - m > citations[m] 是指右邊過多, 所以 l = m + 1 -> 往右逼近 減少右
     * n - m < citations[m] 是指右邊過少, 所以 r = m - 1 -> 往左逼近 增加右
     * n - m == citations[m] 找到我們要的
     * <p>
     * https://leetcode.com/problems/h-index-ii/discuss/71124/Java-binary-search-simple-and-clean/585438
     * 這時候找到的 l 就是 h - index, n - l == n - h 右半邊長度理當要 <= citations[l]
     * 所以要確保 citations[l] >= n - l, 如果不滿足得 l++, 縮短長度
     * 這樣想好了 l++, citations[l] 變大 且 n-l 變短, 接近我們要的 h-index 定義
     * Ex: [0, 0] cases
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (n - m <= citations[m]) r = m;
            else l = m + 1;
        }
        if (citations[l] < n - l) l++;
        return n - l;
    }
}
