package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.ArrayList;
import java.util.List;

public class LC373FindKPairsWithSmallestSums extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://github.com/wisdompeak/LeetCode/blob/master/Binary_Search/373.Find-K-Pairs-with-Smallest-Sums/373.Find-K-Pairs-with-Smallest-Sums_v2.cpp
     * 這題基本思路有想出出來
     * 從 nums1, nums2 兩數加總的 min, max 中 binary search
     * 找出 nums1, nums2 裡面相加組合 <= mid 的總數
     * 相加組合 <= mid 的總數 >= k : r = m
     * else l = mid+1
     *
     * 但 corner case 一直沒處理好
     * 1. 兩數相加會超過 int size, 得用 long 處理
     * 2. 找出 nums1, nums2 裡面相加組合 <= mid 的總數, 算總數就好, 有總數再回頭找結果
     * 算總數就是 nums1 從 0 開始, nums2 從 j = nums2.length -1 開始
     * 如果相加超過 mid, j--,
     * count += j+1
     *
     * 3. 找到的mid 可能總數會 >= k,
     * 所以最後回頭算結果時, 先找出 nums1[i] + nums2[j] < mid
     * 再找出 nums1[i] + nums2[j] == mid, 且確保 res 長度 < k
     * */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        long l = Integer.MIN_VALUE, r = Integer.MAX_VALUE;
        List<List<Integer>> res = new ArrayList<>();
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (countLessEq(nums1, nums2, mid) >= k) {
                r = mid;
            } else l = mid + 1;
        }
        for (int i = 0; i < n1; i++)
            for (int j = 0; j < n2 && nums1[i] + nums2[j] < l; j++)
                res.add(List.of(nums1[i], nums2[j]));
        for (int i = 0; i < n1; i++)
            for (int j = 0; j < n2 && nums1[i] + nums2[j] <= l && res.size() < k; j++)
                if (nums1[i] + nums2[j] == l) res.add(List.of(nums1[i], nums2[j]));

        return res;
    }

    long countLessEq(int[] nums1, int[] nums2, long mid) {
        long c = 0;
        int j = nums2.length - 1;
        for (int i = 0; i < nums1.length; i++) {
            while (j >= 0 && nums1[i] + nums2[j] > mid) j--;
            c += j + 1;
        }
        return c;
    }
}
