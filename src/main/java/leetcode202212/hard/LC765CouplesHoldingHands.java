package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC765CouplesHoldingHands extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC765CouplesHoldingHands();
    }

    /**
     * https://leetcode.com/problems/couples-holding-hands/solutions/113355/java-o-n-solution-using-hashmap/
     * 這題比較多的解法都是 Greedy 的 cyclic swap -> cyclic sort
     * 能以最少 swap 來達成 sorted 概念, 雖然不知道怎麼證明
     *
     * 反而 Union find 解法沒這麼直觀 - 看不懂 wisdompeak 放到 union find 的理由
     *
     * 總之 要找兩兩配對, 0/1 一對, 2/3, 4/5 一對
     * 先記錄每個數字所在的 offset - Map<Integer, Integer> map
     * 接著 i - 0 to n, i+=2, 每次跳兩格, 因為 當前 i/i+1 配對完 就可以去 i+2 了
     * - 1. if (row[i] / 2 != row[i + 1] / 2)  當前 i/i+1 沒有成為一對, 一對的 值/2 會得到一樣商數 like 4/2 == 5/2
     * -      所以不管是先遇到 4 or 5 透過這個 row[i] / 2 != row[i + 1] / 2 就可以簡單找到另一半
     * - 2. 用剛剛 map 找出期望的另一半, 就是看 row[i] % 2 == 0, 另一半就是會是另一種餘數
     * - 3. swap
     * - 4. 更新 idx in map
     * -       map.put(row[i + 1], i + 1);, row[i+1] 已經是 swap 後要的另一半 value, 自然要更新 idx 到 i+1 (原本是 coupleIdx)
     * -       map.put(row[coupleIdx], coupleIdx);, row[coupleIdx] 已經是 swap 後不要的原本 i+1 的值,
     * -               自然要更新 idx 到 e ;e; 剛剛找到的coupleIdx
     * - 5.  res++, 有 swap 就要計數
     * */
    public int minSwapsCouples(int[] row) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < row.length; i++) {
            map.put(row[i], i);
        }

        for (int i = 0; i < row.length; i += 2) {
            if (row[i] / 2 != row[i + 1] / 2) {
                int coupleIdx = 0;
                if (row[i] % 2 == 0) coupleIdx = map.get(row[i] + 1);
                else coupleIdx = map.get(row[i] - 1);
                swap(row, i + 1, coupleIdx);

                map.put(row[i + 1], i + 1);
                map.put(row[coupleIdx], coupleIdx);
                res++;
            }
        }
        return res;
    }

    void swap(int[] r, int i, int j) {
        int tmp = r[i];
        r[i] = r[j];
        r[j] = tmp;
    }
}
