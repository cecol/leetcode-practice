package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC981TimeBasedKeyValueStore extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC981TimeBasedKeyValueStore();
    }

    Map<String, TreeMap<Integer, String>> m = new HashMap<>();
    /**
     * https://leetcode.com/problems/time-based-key-value-store/discuss/282703/Java-binary-search-and-treemap-solution
     * 很直觀用 Map + TreeMap 來解
     * 但更快的是 自建資料結構來 binary search 找timestamp
     * 不過太複雜了
     * */
    public void TimeMap() {

    }

    public void set(String key, String value, int timestamp) {
        if (!m.containsKey(key)) m.put(key, new TreeMap<>());
        m.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!m.containsKey(key)) return "";
        TreeMap<Integer, String> ts = m.get(key);
        Integer kts = ts.floorKey(timestamp);
        if(kts == null) return "";
        else return ts.get(kts);
    }

}
