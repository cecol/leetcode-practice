package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.HashAttributeSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC1577NumberOfWaysWhereSquareOfNumberIsEqualToProductOfTwoNumbers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * 看起來跟 3 sum tow pointer 很相似
     * 但細節處理上在 nums2[j] * nums2[k] == nums1[i] * nums1[i] 時候很難處理
     * 因為不是單純 j++, k-- 就好
     * 這邊有解釋
     * https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/1577.Number-of-Ways-Where-Square-of-Number-Is-Equal-to-Product-of-Two-Numbers
     * 1. nums2[j] == nums2[k] 區間k相同 RES += k*(k-1)/2
     * 2. nums2[j] != nums2[k] , 找出x個同等 nums2[j], y個同等 nums2[k], res += x*y
     * <p>
     * hashmap更好解
     * 找到 nums1[i] * nums1[i] / nums2[j] 是否在 map, 所以得紀錄 nums2 每個元素在 MAP 出現次數
     * 但有幾個要點
     * 因為要 nums1[i]^2 == nums2[j] * nums2[k] &&  0 <= j < k < nums2.length
     * 1. 所以 hashmap 計數nums2 得在走過 nums2 檢查  nums1[i] * nums1[i] / nums2[j] 時候邊計數
     * 不能先把 map 先建立起來後 去找 nums1[i]^2 == nums2[j] * nums2[k]
     * 這樣會找到重複 case (i, j ,k) & (i, k ,j)
     * 2. 得先檢查 nums1[i] * nums1[i] % nums2[j] == 0, 不然直接 nums1[i] * nums1[i] / nums2[j] 會誤加上到非整除 CASE
     */
    public int numTripletsTwo(int[] nums1, int[] nums2) {
        int res = 0;
        res += perCaseHashMap(nums1, nums2);
        res += perCaseHashMap(nums2, nums1);

        return res;
    }

    int perCaseHashMap(int[] nums1, int[] nums2) {
        int res = 0;

        for (long n : nums1) {
            Map<Long, Integer> m = new HashMap<>();
            for (long n2 : nums2) {
                if (n * n % n2 == 0) {
                    res += m.getOrDefault(n * n / n2, 0);
                }
                m.put(n2, m.getOrDefault(n2, 0) + 1);
            }
        }
        return res;
    }

    public int numTripletsTwoPointers(int[] nums1, int[] nums2) {
        int res = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        res += perCase(nums1, nums2);
        res += perCase(nums2, nums1);

        return res;
    }

    int perCase(int[] nums1, int[] nums2) {
        int res = 0;
        for (long x : nums1) {
            for (int j = 0, k = nums2.length - 1; j < k; ) {
                long p = (long) nums2[j] * (long) nums2[k];
                if (p == x * x) {
                    if (nums2[j] == nums2[k]) {
                        int r = k - j + 1;
                        res += r * (r - 1) / 2;
                        break;
                    } else {
                        int j0 = j, k0 = k;
                        while (j + 1 < nums2.length && nums2[j + 1] == nums2[j]) j++;
                        while (k - 1 < nums2.length && nums2[k - 1] == nums2[k]) k--;
                        res += (j - j0 + 1) * (k0 - k + 1);
                        j++;
                        k--;
                    }

                } else if (p < x * x) j++;
                else k--;
            }
        }
        return res;
    }
}
