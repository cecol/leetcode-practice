package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.TreeSet;

public class LC475Heaters extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC475Heaters();
//        LC.findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4});
        LC.findRadius(
                new int[]{282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923},
                new int[]{823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612});
    }

    /**
     * 一直沒想什麼想法這個跟binary search 有關係
     * 後來看到的解答才意會到, 原來是每一間房子都拿去 heater 之間binary search尋找, 看是在哪些heater 之間, 然後取最小的半徑,
     * 最後再取所有房子間最大
     * https://leetcode.com/problems/heaters/discuss/95886/Short-and-Clean-Java-Binary-Search-Solution
     * 1. For each house, find its position between those heaters (thus we need the heaters array to be sorted).
     * 2. Calculate the distances between this house and left heater and right heater, get a MIN value of those two values. Corner cases are there is no left or right heater.
     * 3. Get MAX value among distances in step 2. It's the answer.
     * 正解有用 Arrays.binarySearch 並利用沒找到時候回傳的 負值 做2補數來應用
     * 但我覺得太tricky, 乾脆自己做 binary search
     * 後來自己做的 binary 都有corner case不符合
     * 參考了 TreeSet實作 感覺更簡單(在同一篇裡面的回應有人提出來)
     */
    public int findRadius(int[] houses, int[] heaters) {
        TreeSet<Integer> t = new TreeSet<>();
        for (int h : heaters) t.add(h);
        int res = 0;
        for (int h : houses) {
            int d1 = t.ceiling(h) == null ? Integer.MAX_VALUE : t.ceiling(h) - h;
            int d2 = t.floor(h) == null ? Integer.MAX_VALUE : h - t.floor(h);
            res = Math.max(res, Math.min(d1, d2));
        }
        return res;
    }
}
