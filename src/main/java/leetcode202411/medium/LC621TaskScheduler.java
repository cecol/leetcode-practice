package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC621TaskScheduler extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 有想出要算 max & max count
    // 但最後要算。最加值算錯了
    // 1. 搞不好算完 tasks size 還是最長 (因為 n  == 1)
    // 2. 總值是 max(最多的先擺), n * (max - 1) 區間要擺, mc - 1 剩餘 mc 補上
    public int leastInterval(char[] tasks, int n) {
        int max = 0;
        int[] cc = new int[256];
        for (char c : tasks) {
            cc[c]++;
            max = Math.max(max, cc[c]);
        }
        int mc = 0;
        for (int c : cc) if (c == max) mc++;
        return Math.max(tasks.length, max + n * (max - 1) + mc - 1);
    }
}
