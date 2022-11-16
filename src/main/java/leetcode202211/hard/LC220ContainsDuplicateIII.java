package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;
import java.util.TreeSet;

public class LC220ContainsDuplicateIII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC220ContainsDuplicateIII();
    }

    /**
     * 沒想過這題可以直接暴力解 O(n^2)
     * https://leetcode.com/problems/contains-duplicate-iii/discuss/2730823/Java-Solution-with-O(n2)-time-and-O(1)-space
     * <p>
     * 我對於題目是否可以暴力解實在很難直接看出來
     * <p>
     * https://leetcode.com/problems/contains-duplicate-iii/discuss/61655/Java-O(N-lg-K)-solution
     * 另一個解法是 O(n Log(K))
     * indexDiff 就是 window size
     * 用 TreeSet 來維持這個 window size 內的值
     * 所以當前 i 要加入 TreeSet, 如果 i >= k, 就要 remove window 以外的值
     * 當前 num[i] 去找是否有 valueDiff 的範圍
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> window = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer floor = window.floor(num);
            Integer ceiling = window.ceiling(num);
            if ((floor != null && Math.abs(floor - num) <= valueDiff) ||
                    (ceiling != null && Math.abs(ceiling - num) <= valueDiff))
                return true;
            window.add(num);
            if (i >= indexDiff) window.remove(nums[i - indexDiff]);
        }
        return false;
    }
}
