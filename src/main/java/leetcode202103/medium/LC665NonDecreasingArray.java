package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC665NonDecreasingArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC665NonDecreasingArray();
    }

    /**
     * https://leetcode.com/problems/non-decreasing-array/discuss/106826/JavaC%2B%2B-Simple-greedy-like-solution-with-explanation
     * 一直覺得這種array 題目, 很沒有一定的pattern, 在考你是否能夠觀察出該題的特徵
     * 雖然這答案有說服我, 但我真不知道一看到題目就這樣想的人是怎麼得到靈感的
     * nums[i-1] > nums[i] for some i, you will prefer to change nums[i-1]'s value, since a larger nums[i] will give you more risks
     * nums[i-1] > nums[i] -> 優先去換 nums[i-1], 因為是對nums[i-1]變小, nums[i]變大的話, 增加破壞後面遞增的可能性
     * 先用一個 int cnt = 0; 記載換過幾次
     * 遇到 nums[i - 1] > nums[i] 要換
     * cnt++; 先加加
     * if (i - 2 < 0 || nums[i - 2] <= nums[i]) nums[i - 1] = nums[i];
     * 如果當前i == 1 -> 直接nums[i - 1] = nums[i]; 把 nums[0] 下來跟nums[1] 確保沒有遞減, 反正0是盡頭
     * 或者 nums[i - 2] <= nums[i] -> nums[i - 1] 改成 nums[i] 還是比 nums[i - 2] 大於等於
     * else
     * 真沒得選只好升nums[i] 到 nums[i - 1] , 如果造成後面 nums[i] 跟後面又有遞減 -> 代表 cnt >= 2 -> 最後會回傳 false
     * 反省這題
     * 1. 我沒想到 nums[i-1] > nums[i] 時候先改誰的邏輯
     * 2. 我沒想到可以改就先改 nums 裡面的值, 因為 cnt 有記載改幾次, 所以其實可已改的, 如果我有想到可以改 nums, 應該可以導出1的邏輯
     * */
    public boolean checkPossibility(int[] nums) {
        int cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                cnt++;
                if (i - 2 < 0 || nums[i - 2] <= nums[i]) nums[i - 1] = nums[i];
                else nums[i] = nums[i - 1];
            }
        }
        return cnt <= 1;
    }
}
