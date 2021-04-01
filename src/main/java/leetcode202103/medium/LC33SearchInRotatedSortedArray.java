package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC33SearchInRotatedSortedArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC33SearchInRotatedSortedArray();
        LC.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
    }

    /**
     * https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14435/Clever-idea-making-it-simple
     * 我是有想到 因為rotate, 所以當成兩個個別的sub sorted subarray下去看
     * 但是沒有想到要怎麼找到 兩個個別的sub sorted subarray下去 binary search
     * 直到看到這答案才發現原來我後續想太多
     * 就是 l = 0, h = 19
     * [12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
     * 找 14
     * 想成 [12, 13, 14, 15, 16, 17, 18, 19, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf]
     * 找 7
     * 想成 [-inf, -inf, -inf, -inf, -inf, -inf, -inf, -inf, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
     * <p>
     * 1. 重點
     * 其中 (nums[m] < nums[0]) == (target < nums[0]) 是用來判定 mid 跟target 是在同一邊
     * 要判定否超出界線!! 跟nums[0] 比是比小於, 跟nums[nums.length-1]比是要比大於
     * -> 要注意的是比較是否同一邊 跟 nums[0]要比小於, 因為這樣才代表超出左邊界線 -> 因為理論上nums[0]正常來說應當是最小的
     * (不能用  (nums[m] > nums[0]) == (target > nums[0]) 這個邏輯是錯誤的!!!)
     * 反之要注意的是比較是否同一邊 跟 nums[nums.length-1]要比大於, 因為這樣才代表超出右邊界線
     * for l=0, h=20, target = 14 in [12, 13, 14, 15, 16, 17, 18, 19],
     * -> but nums[10] = 1 is in [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
     * 不在同邊, 那麼 l or h 必有一邊是落在 包含target 的subarray之外(-Inf or Inf)
     * -> 因為 target > nums[0] -> 可以得知target 在
     * -> [12, 13, 14, 15, 16, 17, 18, 19, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf]
     * 那麼就是 h 落在 inf, 所以 h 要縮短
     * 變成下一輪只看到 [12, 13, 14, 15, 16, 17, 18, 19, inf, inf] 然後繼續
     *
     * 2. 重點
     * 因為是 while(l<r) 且最後是回傳return nums[l] == target ? l : -1;
     * 所以l移動是要精確的, 所以內層判定
     * 在同一邊要用: if (target > nums[m]) l = m + 1;
     * 不能用 : if (target < nums[m]) r = m - 1; 邏輯上是對的, 但最後答案會變成放在 r
     * 在不同邊要用: if (target < nums[0]) l = m + 1;
     * 不能用 if (target > nums[0]) r = m - 1; 邏輯上是對的, 但最後答案會變成放在 r
     * 因為拿來比對的那一邊*(l or r) 就是界定邊界, 既然答案最後用l, 那經確界定要用l來界定, 所謂的精確界定就是沒有 等於的比較
     */
    public int search(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = (l + h) / 2;
            if ((nums[m] < nums[0]) == (target < nums[0])) {
                // m 跟 target都在同一邊, 直接拿 target > nums[m] 比較就知道 是 l 要縮 還是 h
                if (target > nums[m]) l = m + 1;
                else h = m;
            } else {
                //  m 跟 target不同一邊, 只要用target < nums[0] 比較就知道target在哪邊, 也可以同時知道 m 在另一邊
                if (target < nums[0]) l = m + 1; // target < nums[0] 代表 target在右半部, m 一定還在左半部 -> l 要縮 m+1 (l 本身在左半部不可信了, 故要 m + 1)
                else h = m; // target >= nums[0] 代表 target在左半部, m 一定還在右半部 -> h 要縮
            }
        }
        return nums[l] == target ? l : -1;
    }
}
