package leetcode202104.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TreeSet;

public class LC683KEmptySlots extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC683KEmptySlots();
        LC.kEmptySlots(new int[]{6, 5, 8, 9, 7, 1, 10, 2, 3, 4}, 2);
    }


    /**
     * TreeSet 是可以解出來, 但是很慢就是
     * https://leetcode.com/problems/k-empty-slots/discuss/107931/JavaC%2B%2B-Simple-O(n)-solution
     * bulb[i]中 是 ith day 開 ith bulb
     * days[i]中 是 ith bulb 開在 ith day
     * <p>
     * 我們要找的就是 bulb [i]th - [i+k]th 中間都沒人開
     * 就代表說 bulb [i]th - [i+k]th 中間的 bulb id [i to i+k] 開燈的 day 都是在他們之外
     * 所以我們假設 bulb left = 0/right = k+1 嘗試開始找看看
     * - for (int i = 1; right < days.length; i++)
     * -  因為 left 設定為 0th bulb, 要檢驗的 bulb 區間就是 [1 - k] (right = k+1)
     * -    if (days[i] > days[left] && days[i] > days[right]) continue;
     * -     如果當前 ith bulb 開燈的 day 確實 都是 left/right 區間 之外的日子, 那當前的 bulb i 並沒有破壞
     * 當前 left/right 區間維持 k 個沒開燈的假設, 所以往下個 i+1 檢查
     * -    if (i == right) res = Math.min(res, Math.max(days[left], days[right]));
     * -     如果當前 i 剛好是 left/right 邊界, 代表我們當前的 left/right bulb 保持 k 區間都沒有開燈, 取最小當作 res
     * -
     * -    上面都不符合代表 當前 bulb[i] 開燈時間  剛好在 left/right 之間
     * -    破壞了 left/right 的假設, 只好把 left = i 更新 left/right 假設, 再往後找
     * -
     */
    public int kEmptySlots(int[] bulbs, int k) {
        int[] bulbOnDays = new int[bulbs.length];
        for (int i = 0; i < bulbs.length; i++) bulbOnDays[bulbs[i] - 1] = i + 1;
        int leftBulbID = 0, rightBuldID = k + 1, res = Integer.MAX_VALUE;
        for (int bulbID = 1; rightBuldID < bulbOnDays.length; bulbID++) {
            if (bulbOnDays[bulbID] > bulbOnDays[leftBulbID] && bulbOnDays[bulbID] > bulbOnDays[rightBuldID]) continue;
            if (bulbID == rightBuldID) res = Math.min(res, Math.max(bulbOnDays[leftBulbID], bulbOnDays[rightBuldID]));
            leftBulbID = bulbID;
            rightBuldID = leftBulbID + k + 1;
        }
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }

    public int kEmptySlotsSlow(int[] bulbs, int k) {
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < bulbs.length; i++) {
            ts.add(bulbs[i]);
            if (ts.size() > 1) {
                if (ts.higher(bulbs[i]) != null && ts.higher(bulbs[i]) - bulbs[i] - 1 == k) return i;
                if (ts.lower(bulbs[i]) != null && bulbs[i] - ts.lower(bulbs[i]) - 1 == k) return i;
            }
        }
        return -1;
    }
}
