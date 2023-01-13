package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC153FindMinimumInRotatedSortedArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC153FindMinimumInRotatedSortedArray();
    }

    /**
     * 沒想到 rotate array , 用很基本的 binary search 就可以解
     * 其實只要比 nums[mid] < nums[r] -> 代表一定遞增 -> 那麼最小 不可能是 m+1 -> r之間, worst case 也只會是 nums[m]
     * 而其他case 就是 l 要移動到 m+1
     * 後來再次思考是因為關鍵是遞增array 排序, 不管怎麼 rotate -> 右邊一定比自己大, 如果右邊沒有比自己大, 代表rotate過, 最小已經在右邊了
     * 1. 最右邊比自己大 -> 一路遞增過去 -> 代表最小一定不在右邊 h = m
     * 2. 最右邊比自己小 -> 遞增排序好的 array不會有這種情況-> 代表是rotate case -> 最小在右邊 l = m+1
     * */
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int l = 0, r = n - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] < nums[r]) {
                r = m;
            } else l = m + 1;
        }
        return nums[l];
    }
}
