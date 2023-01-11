package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LC624MaximumDistanceInArrays extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC624MaximumDistanceInArrays();
    }

    /**
     * https://leetcode.com/problems/maximum-distance-in-arrays/solutions/104613/java-solution-min-and-max/
     * 其實是很直觀的找到每個 arr[i] 的  min/max
     * max/min 互減就是
     * 可是如果 剛好找到的 max/min 是在同個 arr[i]?
     *
     * 這個算法直接避開
     * 每當走到 arr[i], 都是拿當前的 arr[i] 的 min/max 去跟之前累計的 min/max 互比來更新 res,
     * 所以 result 一定都是 來自兩個 arr[i/j]
     * 然後當 arr[i] 結束前, 也要更新 min/max 讓下一個 i+1 繼續比較
     * 所以 min/max 是 i-1 前的結果  跟當前 arr[i] 比較就好
     * */
    public int maxDistance(List<List<Integer>> arr) {
        int res = Integer.MIN_VALUE;
        int min = arr.get(0).get(0);
        int max = arr.get(0).get(arr.get(0).size() - 1);

        for (int i = 1; i < arr.size(); i++) {
            res = Math.max(res, Math.abs(max - arr.get(i).get(0)));
            res = Math.max(res, Math.abs(min - arr.get(i).get(arr.get(i).size() - 1)));
            max = Math.max(max, arr.get(i).get(arr.get(i).size() - 1));
            min = Math.min(min, arr.get(i).get(0));
        }
        return res;
    }
}
