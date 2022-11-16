package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC447NumberOfBoomerangs extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Hash/447.Number-of-Boomerangs
     * 解法很直觀, 但就是沒想到
     * 對每一個 i point, 都去計算他對其他點的 distCount
     * 自己也納入也不受影響
     * 然後對 distCount 裡面每一個 value, res += value * value-1
     * Why?
     * 比如說
     * 1. 自己對自己 距離 0 -> count = 1, 拿去算會得到 0 不受影響
     * 2. 自己對某點 距離 N -> count = 1, 其實也無法形成 Boomerangs, 但拿去算也會得到 0 不受影響
     * 3. 自己對某點 距離 N -> count = 5, 就是 C5 取 2, 但對調也算入, 所以是 C5 取 2 * 2 = 5 * 4 => 去展開公式就知道
     * i 對 j 跟 j 對 i 都是獨立事件 => 所以 i , j  都是 0 to points.length 去計數
     * */
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> disCount = new HashMap<>();
            int[] pi = points[i];
            for (int j = 0; j < points.length; j++) {
                int[] pj = points[j];
                int dis = (pi[0] - pj[0]) * (pi[0] - pj[0]) + (pi[1] - pj[1]) * (pi[1] - pj[1]);
                disCount.put(dis, disCount.getOrDefault(dis, 0) + 1);
            }
            for (Integer k : disCount.keySet())
                res += disCount.get(k) * (disCount.get(k) - 1);
        }
        return res;
    }
}
