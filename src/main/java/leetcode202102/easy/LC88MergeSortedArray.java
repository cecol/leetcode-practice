package leetcode202102.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC88MergeSortedArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC88MergeSortedArray();
//        LC.merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
        LC.merge(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
    }

    /**
     * https://leetcode.com/problems/merge-sorted-array/discuss/29704/My-clean-java-solution
     * 我一開始的思路就完全錯了, 我以為從頭開始比較 然後填回nums1, 中間透過swap
     * 但這樣做法 num1 後面的數字得一直往後移
     * 因為 nums1 的m+1後面是多出來的空間
     * 所應該是由nums1, nums2尾巴, 就是由max開始回填 填到0
     * 這樣也不需要swap
     * while (t1 >= 0 && t2 >= 0) 一定是要馬 nums1 先到底 or nums2 先到底 任一到底就代表該走完了
     * 但事實上可能是t1 先到底 -> t2還有, 那代表剩下的都是t2(這代表m以前的都處理掉了 都放在m以後的空間去了)
     * 那剩下的就t2 直接copy過去就好
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int t1 = m - 1;
        int t2 = n - 1;
        int f = m + n - 1;
        while (t1 >= 0 && t2 >= 0) nums1[f--] = nums1[t1] > nums2[t2] ? nums1[t1--] : nums2[t2--];
        while (t2>=0) nums1[f--] = nums2[t2--];
    }

}
