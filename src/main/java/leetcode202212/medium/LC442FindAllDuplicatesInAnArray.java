package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC442FindAllDuplicatesInAnArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC442FindAllDuplicatesInAnArray();
        LC.findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
    }

    /**
     * 跟 LC41FirstMissingPositive 一樣 直觀用 cycle swap 來換就好
     * cycle swap 的概念是 nums[i] 應該是 i + 1
     * - while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) swap(nums, nums[i] - 1, i);
     * - 如果不是 嘗試換到正確位置 - 直到沒地方換就好
     * */
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) swap(nums, nums[i] - 1, i);
        }
        for (int i = 0; i < n; i++) if (nums[i] != i + 1) res.add(nums[i]);
        return res;
    }

    void swap(int[] n, int i, int j) {
        int t = n[i];
        n[i] = n[j];
        n[j] = t;
    }
}
