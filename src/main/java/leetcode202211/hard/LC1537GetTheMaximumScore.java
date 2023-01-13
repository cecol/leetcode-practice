package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1537GetTheMaximumScore extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1537GetTheMaximumScore();
    }

    /**
     * 也是完全沒概念一題
     * https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/1537.Get-the-Maximum-Score
     * 1. nums1 nums2 相同數字可以視為傳送門, 還沒到傳送門之前只能往前走, 假設各自在傳送門之前加總為 s1, s2
     * 2. 到傳送門之後拿 max(s1, s2) 來繼續計算 傳送門之後的 max(s1,s2)
     * 3. 如何知道到達傳送門? 讓s1, s2 指針剛好停在傳送門?
     * 比較兩指針數值, 小的往前進就好, 最後都會一起到達傳送門
     * <p>
     * corner case 細節處理
     * 1. while (i1 < n1 || i2 < n2) 任一沒走完繼續走, 直到兩邊都走完
     * 配上條件
     * if (i1 == n1) s2 += nums2[i2++];
     * else if (i2 == n2) s1 += nums1[i1++];
     *
     * 以及當到達傳送門 直接
     * s1 = Math.max(s1, s2) + nums1[i1];
     * s2 = s1;
     * - 為什麼 s1 = Math.max(s1, s2) + nums1[i1]; 是加上 nums1[i1]?
     * - 然後給 s2 = s1;?
     * - 因為這時候 nums1[i1] == nums2[i2], 所以 Math.max(s1, s2) + nums2[i2]; 也是可以的, 取一就好, 繼續往前走
     *
     * 小的就放棄, 取max繼續往前累加 s1, s2
     * 直到最後回傳 做總結
     * (int) (Math.max(s1, s2) % m);
     */
    public int maxSum(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int i1 = 0, i2 = 0;
        int m = 1000000007;
        long s1 = 0, s2 = 0;
        while (i1 < n1 || i2 < n2) {
            if (i1 == n1) s2 += nums2[i2++];
            else if (i2 == n2) s1 += nums1[i1++];
            else if (nums1[i1] < nums2[i2]) s1 += nums1[i1++];
            else if (nums1[i1] > nums2[i2]) s2 += nums2[i2++];
            else {
                s1 = Math.max(s1, s2) + nums1[i1];
                s2 = s1;
                i1++;
                i2++;
            }
        }
        return (int) (Math.max(s1, s2) % m);
    }
}
