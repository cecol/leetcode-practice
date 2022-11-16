package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Handler;

public class LC274H_Index extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC274H_Index();
    }

    /**
     * h-index 邏輯很不直觀, 這邊有比較直觀的解釋
     * https://leetcode.com/problems/h-index/discuss/70810/A-Clean-O(N)-Solution-in-Java
     * https://www.youtube.com/watch?v=o1zpbtMGcXY&ab_channel=%E5%B0%8F%E5%8D%97%E4%B9%A6
     * A scientist has an index h if h of their n papers have at least h citations each,
     * and the other n − h papers have no more than h citations each.
     * 一名科学家的h指数是指其发表的Np篇论文中有h篇每篇至少被引h次、而其余Np-h篇论文每篇被引均小于或等于h次。
     *
     * 來看看
     * [1,6,1,5,6] h = 3
     * 其實是在找
     * "largest" k, so we have k element not smaller than k
     * 排序, 然後從最後面找  largest 1, 2, 3, 4
     * [1,1,5,6,6]
     * largest 1 = [1,1,5,6,"6"] => 1[6] element not smaller than 1 => success, but maybe not enough
     * largest 2 = [1,1,5,"6",6] => 2[6,6] element not smaller than 2 => success, but maybe not enough
     * largest 3 = [1,1,"5",6,6] => 3[5,6,6] element not smaller than 3 => success, but maybe not enough
     * largest 4 = [1,"1",5,6,6] => 3[5,6,6] element not smaller than 4 => fail
     *
     * k 是找至少 k 個元素 >= k,
     * k = 1, 有6個, 所以合法  但還不是 largest
     * 直到 k = 4, 就找不到 k個 元素 >= 4, 所以 K 只能長到 3,
     * 這時候 k = 3 就是符合 h-index, h 個 citations >= h
     *
     * 然後這題有兩個解法
     * 1. Sort array , 用上述來找
     * 2. count sort
     * n = citations.length
     * int[] citationCount = new int[n+1];
     * 如果 citations[i] >= n 都放在 citationCount[n]
     * or citationCount[citations[i]]++;
     * 然後從最大回頭找 合法 h
     * i = n to 1 來找
     * n 的時候如果 citationCount[n] >= n 那就是
     * 不然就往 ｎ－１ 找 只要 citationCount[n] + citationCount[n-1] >= -1
     * 一路往下
     *
     * 如果 i = 1 to n 也是可以
     * 但你要一直檢查
     * i = 3, 就要加總 citationCount[3 to n] 時間複雜度會變成 n^2
     *
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] citationCount = new int[n + 1];
        for (int c : citations)
            if (c >= n) citationCount[n]++;
            else citationCount[c]++;
        int sum = 0;
        for (int i = n; i >= 0; i--) {
            sum += citationCount[i];
            if (sum >= i) return i;
        }
        return sum;
    }
}
