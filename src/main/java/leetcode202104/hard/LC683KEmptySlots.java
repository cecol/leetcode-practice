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
     * bulb[i]中 i是第i天, bulb[i]就是i天打開第幾個bulb
     * 這個是先用 days[i]中 i 是第幾個bulb, days[i] 就是該bulb 那一天turn on
     * 我們要的是 兩個打開的bulb區間的bulb 都還沒打開 -> 所以 right - left 一定是 k, 固定的寬度window
     * sliding window 概念
     * 然後 day[left], day[right] 之間的值都要在他們兩者之外,
     * 1. 如果day[i], left < i < right 的值也是 day[left] < day[i] < day[right] (or day[left] > day[i] > day[right] )
     * -> 目前 left to right 區間的 bulb沒有辦法有k個 turn off, 所以 left = i, right = left +k + 1, window往前移再繼續找
     * 2. 如果 left < i < right 但是 不是day[left] < day[i] < day[right] (or day[left] > day[i] > day[right] )
     * -> 這就是 if (days[i] > days[left] && days[i] > days[right]) continue;
     * -> 代表目前看起來 left to right 區間還有可能有k個 turn off
     * -> 所以繼續 continue,
     * -> 1. 直到if (i == right) 找完了 -> 結算結果 res = Math.min(res, Math.max(days[left], days[right]));
     * ->    (注意 days[right] 並沒有一定大於 days[left], right offset 一定大於 left, 但真實值, 也就是開燈的時間可能左右對調)
     * ->    Math.max(days[left], days[right]) 真實發生的那一天當然是最大的那一天
     * ->    window的區間是 left/right offset -> 而非 days[left]/days[right]
     * -> 2. 或者新的i 沒有採到  if (days[i] > days[left] && days[i] > days[right]) continue; 且尚未
     * ->    if (i == right) res = Math.max(res, Math.max(days[left], days[right]));
     * ->    所以代表當前i 打破了 當前window left/right 有k turn off 的可能性 -> window 往前移找下一個可能性
     * */
    public int kEmptySlots(int[] bulbs, int k) {
        int[] days = new int[bulbs.length];
        for (int i = 0; i < bulbs.length; i++) days[bulbs[i] - 1] = i + 1;
        int left = 0, right = k + 1, res = Integer.MAX_VALUE;
        for (int i = 1; right < days.length; i++) {
            if (days[i] > days[left] && days[i] > days[right]) continue;
            if (i == right) res = Math.min(res, Math.max(days[left], days[right]));
            left = i;
            right = left + k + 1;
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
