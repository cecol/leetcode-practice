package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LC981981TimeBasedKey_ValueStore extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 秒解, 因為只要想到 Map + TreeMap 就可以很好解了
    public void TimeMap() {

    }
    Map<String, TreeMap<Integer, String>> kv = new HashMap<>();
    public void set(String key, String value, int timestamp) {
        if (!kv.containsKey(key)) {
            kv.put(key, new TreeMap<>());
        }
        kv.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!kv.containsKey(key)) {
            return "";
        }
        TreeMap<Integer, String> tm = kv.get(key);
        if (tm.floorKey(timestamp) == null) return "";
        return tm.floorEntry(timestamp).getValue();
    }
}
