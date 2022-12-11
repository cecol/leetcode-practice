package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC2499MinimumTotalCostToMakeArraysUnequal extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2499MinimumTotalCostToMakeArraysUnequal();
    }

    /**
     * https://leetcode.com/problems/minimum-total-cost-to-make-arrays-unequal/solutions/2898020/java-greedy-approach/
     * 這題也是 greedy, 也是不知道怎麼觀察出這個 Greedy 策略
     * 別人解釋是
     * 先想看看 理想 case, 每個 duplicated ith 值兩兩互換,
     * https://leetcode.com/problems/minimum-total-cost-to-make-arrays-unequal/solutions/2897887/simple-solution-with-diagram-and-intuition-c-o-n-time-and-space/
     * Ex:
     * offset:  0,1,2,3,4
     * nums1 = [5,7,6,9,3]
     * nums2 = [8,7,5,9,1]
     * duplicate offset: 0 and 3 這兩個互換就結束了
     * 所以低消就是 duplicated ith 加總 1 + 3 = 4
     * 但有例外 case
     * 1. duplicated ith 不是偶數個, 兩兩互換完 還剩下一個沒得跟別人換
     * 2. duplicated ith 中 有些 值本身 是重複出現(duplicatedKeyMoreThanSwapHalf),
     * -  這些 值重複出現數(duplicatedKeyMoreThanSwapHalf) 大於 swapDuplicatedCount/2
     * <p>
     * case 1 就是把 0th 加入互換行列, 0th 不影響 cost -> cost + 0 = cost
     * case 2 再講的是
     * Ex:
     * offset:  0,1,2,3,4
     * nums1 = [5,7,6,7,3]
     * nums2 = [8,7,5,7,1]
     * duplicate offset: 0 and 3 都是 7, 互換還是 7, 所以不可能互換 得去跟別人換
     * duplicatedKeyMoreThanSwapHalf = 7
     * swapDuplicatedCount = 2
     * - 如果 值本身 是重複出現 count < duplicated count/2 -> 那就跟另外一半 互換就好
     * - 如果 值本身 是重複出現 count > duplicated count/2 -> 就會兩兩互換完 還是 duplicate value(自己跟自己互換)
     * 所以就要紀錄 boolean[] operated = new boolean[n]; 哪些還沒換過
     * 1 = 0 to n 拿還沒換過的來換看看, 可以換的話 就是 cost 加上去, 然後 swapDuplicatedCount++
     * 看看最後是否 if (counts.get(duplicatedKeyMoreThanSwapHalf) > swapDuplicatedCount / 2)
     * true -> 跟本沒其他可以換
     * false -> 有找到其他可以換 , (swapDuplicatedCount 過程中++ 過了)
     */
    public long minimumTotalCost(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        int duplicatedKeyMoreThanSwapHalf = 0, swapDuplicatedCount = 0, n = nums1.length;
        boolean[] operated = new boolean[n];
        long costs = 0;
        for (int i = 0; i < n; i++)
            if (nums1[i] == nums2[i]) {
                operated[i] = true;
                costs += i;
                swapDuplicatedCount++;
                counts.put(nums1[i], counts.getOrDefault(nums1[i], 0) + 1);
            }

        for (Map.Entry<Integer, Integer> count : counts.entrySet())
            if (count.getValue() > swapDuplicatedCount / 2) {
                duplicatedKeyMoreThanSwapHalf = count.getKey();
                break;
            }

        if (duplicatedKeyMoreThanSwapHalf > 0) {
            for (int i = 0; i < n && counts.get(duplicatedKeyMoreThanSwapHalf) > swapDuplicatedCount / 2; i++)
                if (!operated[i] && nums1[i] != duplicatedKeyMoreThanSwapHalf && nums2[i] != duplicatedKeyMoreThanSwapHalf) {
                    swapDuplicatedCount++;
                    operated[i] = true;
                    costs += i;
                }
            if (counts.get(duplicatedKeyMoreThanSwapHalf) > swapDuplicatedCount / 2) return -1;
        }
        return costs;
    }
}
