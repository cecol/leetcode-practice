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
     */
    public int findMinDifference(List<String> timePoints) {
        boolean[] b = new boolean[1440];
        for (String t : timePoints) {
            int i = 60 *
                    ((t.charAt(0) - '0') * 10 + (t.charAt(1) - '0')) +
                    ((t.charAt(3) - '0') * 10 + (t.charAt(4) - '0'));
            if (b[i]) return 0;
            else b[i] = true;
        }

        int res = 1444;
        int s = -1;
        int e = -1;
        int fi = res;
        int li = -1;
        for (int i = 0; i < 1440; i++) {
            if (b[i]) {
                fi = Math.min(fi, i);
                li = Math.max(li, i);

                if (s == -1) s = i;
                else if (e == -1) {
                    e = i;
                    res = Math.min(res, e - s);
                }
                else {
                    s = e;
                    e = i;
                    res = Math.min(res, e - s);
                }
            }
        }
        res = Math.min(res, 1440 - li + fi);
        return res;
    }
}
