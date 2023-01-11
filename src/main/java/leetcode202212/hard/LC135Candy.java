package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC135Candy extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC135Candy();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/blob/master/Greedy/135.Candy/135.Candy.cpp
     * 先每人分配 1 個糖果
     * int[] res = new int[n];
     * Arrays.fill(res, 1);
     *
     * 針對每個 res[i] 從左往右掃 先確保 res[i] > res[i-1] 如果 rating[i] > rating[i-1]
     * 先確保每個 res[i] 至少低消滿足大於右邊 如果 rating[i] > rating[i-1]
     *
     * 再來右邊掃過去 先確保 res[i] > res[i+1] 如果 rating[i] > rating[i+1]
     * 確保每個 res[i] 至少低消滿足大於左邊邊 如果 rating[i] > rating[i-1]
     *
     * 两轮two pass保证了每个小朋友与相邻两个人做比较时都是公平的
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                res[i] = Math.max(res[i], res[i - 1] + 1);
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                res[i] = Math.max(res[i], res[i + 1] + 1);
            }
        }
        int sum = 0;
        for (int i : res) sum += i;
        return sum;
    }
}
