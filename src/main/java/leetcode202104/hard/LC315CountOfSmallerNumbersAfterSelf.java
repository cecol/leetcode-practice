package leetcode202104.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC315CountOfSmallerNumbersAfterSelf extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC315CountOfSmallerNumbersAfterSelf();
    }

    /**
     * 暴力解 O(n^2) 是很直覺, 但應該不是答案, 有想過從右邊找過去, nums[i-1] > nums[i], 那麼就可以繼承 count[i-1] = count[i] + 1
     * 但不知道怎麼去證明就是
     * 看了討論就是要去排序, 但是排序的結果是 offset
     * 比較多都是用 merge sort, 但我沒有想出來為什麼不是quick sort
     * 然後有些解法是建立 binary search tree, 只是因為給的test case 如果是skew, 會 TLE
     * 也是有從尾巴開始往頭找, 然後把找過的變成 sorted, 所以往右邊找的時候只要 binary search
     * <p>
     * https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/445769/merge-sort-CLEAR-simple-EXPLANATION-with-EXAMPLES-O(n-lg-n)
     * 這邊有解釋為什麼是 merge sort
     * Merge check for cases which this coding problem is asking for
     * Merge checks for numbers positioned to the right of the current element, that are smaller than the current element
     * <p>
     * 就是因為merge sort 處理的時候, 右邊都是處理好的, 就剛好是這題要問的, 有多少右邊比較小
     * merge sort 就是把 [5, 2, 6, 1] 切成 [5] [2] [6] [1]
     * 開始merge -> [5] [2] -> 2 勝出, 但 5在2左邊, 所以5的 count++
     * 接著變成 [2, 5] [1, 6] (1,6也同理) -> result count: 5(1) 2(0) 6(1) 1(0)
     * 接著merge [2, 5] [1, 6] -> 1勝出 -> [2, 5] [1, 6] ----------> [2, 5] [6] + merged= [1]
     * 1 勝出 但1在 [2,5] 右邊 -> 所以 2,5 count++
     * result count: 5(1) 2(0) 6(1) 1(0) -------> 5(2) 2(1) 6(1) 1(0)
     * 接著就是 2,5勝出, 但都在左邊, 接著就是結果
     * Merge sort is done now.
     * The final answer result count is [2,1,1,0], same as in the problem description
     */
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new LinkedList<>();
        int n = nums.length;
        int[] result = new int[n];

        ValIdx[] newNums = new ValIdx[n];
        for (int i = 0; i < n; i++) newNums[i] = new ValIdx(nums[i], i);
        mergeAndCount(newNums, 0, n - 1, result);
        List<Integer> res = new ArrayList<>();
        for (int i : result) res.add(i);
        return res;
    }

    private void mergeAndCount(ValIdx[] newNums, int s, int e, int[] result) {
        if (s >= e) return;
        int mid = (s + e) / 2;
        mergeAndCount(newNums, s, mid, result);
        mergeAndCount(newNums, mid + 1, e, result);
        int leftPos = s;
        int rightPos = mid + 1;
        LinkedList<ValIdx> merged = new LinkedList<>();
        int countRightLessLeft = 0;
        while (leftPos < mid + 1 && rightPos <= e) {
            if (newNums[leftPos].v > newNums[rightPos].v) {
                countRightLessLeft++;
                merged.add(newNums[rightPos]);
                rightPos++;
            } else {
                result[newNums[leftPos].idx] += countRightLessLeft;
                merged.add(newNums[leftPos]);
                leftPos++;
            }
        }

        while (leftPos < mid + 1) {
            result[newNums[leftPos].idx] += countRightLessLeft;
            merged.add(newNums[leftPos]);
            leftPos++;
        }
        while (rightPos <= e) {
            merged.add(newNums[rightPos]);
            rightPos++;
        }
        int pos = s;
        for (ValIdx m : merged) {
            newNums[pos] = m;
            pos++;
        }
    }

    class ValIdx {
        int v;
        int idx;

        ValIdx(int val, int offset) {
            v = val;
            idx = offset;
        }
    }
}
