package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC845LongestMountainInArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC845LongestMountainInArray();
        //var s = LC.longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5});
        var s2 = LC.longestMountain(new int[]{875, 884, 239, 731, 723, 685});
    }

    /**
     * https://leetcode.com/problems/longest-mountain-in-array/discuss/165667/1-pass-Java-Two-Point-Solution
     * 我一開始有想到 two pointer 去記錄走到的 起點跟結束, 但是一直沒處理好
     * 後來看到別人標準才理解
     *
     * two pointer: left & right 去算出山的區間
     * 第一個while (left < n - 1 && arr[left] >= arr[left + 1]) left++; 跳過遞減段, 這應該只有是0開始會遇到
     * 第二個 while (right < n - 1 && arr[right] < arr[right + 1]) right++; 先爬升
     * 第三個 while (right < n - 1 && arr[right] > arr[right + 1]) right++; 下坡
     * -> 過程中更新最長距離 res = Math.max(res, right - left + 1);
     * -> 但我沒明白 我以為是下坡完再算長度就好, 但答案是錯的
     * -> 後來看到 [2,2,2], [0,1,2,3,4,5,6,7,8,9] fail case 我才意會到, 如果是沒有mountain
     * -> res = Math.max(res, right - left + 1); 會造成錯誤計算, 所以只有在可以遞減段才算長度
     * 最後更新 left = right;
     */
    public int longestMountain(int[] arr) {
        int n = arr.length;
        if (n < 3) return 0;
        int left = 0, right = 0, res = 0;
        while (left < n - 2) {
            while (left < n - 1 && arr[left] >= arr[left + 1]) left++;
            right = left + 1;
            while (right < n - 1 && arr[right] < arr[right + 1]) right++;
            while (right < n - 1 && arr[right] > arr[right + 1]) {
                right++;
                res = Math.max(res, right - left + 1);
            }
            left = right;
        }
        return res;
    }
}
