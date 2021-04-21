package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC283MoveZeroes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC283MoveZeroes();
    }

    /**
     * 自己有解出來, 用two pointer 但來有這題精簡, 換個方式去想更簡單
     * https://leetcode.com/problems/move-zeroes/discuss/72011/Simple-O(N)-Java-Solution-Using-Insert-Index
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int insertPos = 0;
        for (int num : nums) {
            if (num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    public void moveZeroesMine(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        while (j < n) {
            while (j < n && nums[j] == 0) j++;
            while (i < j && nums[i] != 0) i++;
            if (j < n) swap(nums, i, j);
            j++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
