package leetcode202108.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LC1396DesignUndergroundSystem extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1396DesignUndergroundSystem();
    }

    /**
     * https://leetcode.com/problems/design-underground-system/discuss/554879/JavaC%2B%2B-HashMap-and-Pair-Clean-code-O(1)
     * 蠻直觀解掉了, 花了點時間看懂題目就是
     * 但我的邏輯跟最佳解也差不多, 主要就是討論區裡面有提到 bloomberg's interview 會問後續 follow up question
     * 然後面試官問的可能是 misleading, 所以要懂得 challenge interviewer
     * */
    Map<Integer, Map.Entry<String, Integer>> userCheckIn = new HashMap<>();
    Map<String, Map.Entry<Integer, Integer>> aggre = new HashMap<>();
    public void UndergroundSystem() {

    }

    public void checkIn(int id, String stationName, int t) {
        userCheckIn.put(id, Map.entry(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Map.Entry<String, Integer> inStation = userCheckIn.remove(id);
        String k = inStation.getKey() + "," + stationName;
        aggre.computeIfAbsent(k, x -> Map.entry(0, 0));
        aggre.put(k, Map.entry(aggre.get(k).getKey() + t - inStation.getValue(), aggre.get(k).getValue() + 1));
    }

    public double getAverageTime(String startStation, String endStation) {
        String k = startStation + "," + endStation;
        return (double) aggre.get(k).getKey() / aggre.get(k).getValue();
    }
}
