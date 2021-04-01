package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC539MinimumTimeDifference extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC539MinimumTimeDifference();
//        LC.findMinDifference(List.of("23:59", "00:00"));
        LC.findMinDifference(List.of("01:01", "02:01"));
    }

    /**
     * 這題除了倆倆互減計算時間差, 沒想出有什麼更有效率的辦法
     * 後來看了答案 才想到確實就是 finding the shortest distances between two elements in a circular array.
     * 有兩個解法 一個是直接把時間轉換成分 存在 list中並排序, 下去找 O(nlogn)
     * 另一個是其實只有 1440 個分鐘, 要分成 1440, buckets
     * 用 boolean[1440] 來做就好, 放好就已經排好 -> O(N)
     * https://leetcode.com/problems/minimum-time-difference/discuss/100640/Verbose-Java-Solution-Bucket
     * 在 for (int i = 0; i < 1440; i++) 之中要的都只是 i 跟 prev比較, 會有 first && last
     * 是為了
     * 最後結果回傳前再確認一下 min = Math.min(min, 24 * 60 - last + first);
     * 就是說最後一個時間往前走到第一個時間是否更短 -> circular
     */
    public int findMinDifference(List<String> timePoints) {
        boolean[] b = new boolean[24 * 60];
        for (String time : timePoints) {
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            if (b[h * 60 + m]) return 0;
            b[h * 60 + m] = true;
        }

        int prev = -1, res = 1440, first = -1, last = -1;
        for (int i = 0; i < 1440; i++) {
            if (b[i]) {
                if (first == -1) first = i; // 設定第一個時間
                last = i; //最後是設定在最後一個時間
                if (prev != -1) { // 開始有前一個時間就可以互相比較
                    res = Math.min(res, i - prev);
                }
                prev = i; //進入下一次前, 先記著前一個
            }
        }
        res = Math.min(res, 24 * 60 - last + first);
        return res;
    }
}
