package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LC460LFUCache extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC460LFUCache();
    }

    Map<Integer, Integer> kv;
    Map<Integer, Integer> kCount;
    Map<Integer, LinkedHashSet<Integer>> countKeys;
    int cap;
    int minCount = -1;

    public void LFUCache(int capacity) {
        this.cap = capacity;
        kv = new HashMap<>();
        kCount = new HashMap<>();
        countKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!kv.containsKey(key)) return -1;
        int oldCount = kCount.get(key);
        countKeys.get(oldCount).remove(key);
        if (oldCount == minCount && countKeys.get(oldCount).size() == 0) minCount++;
        putCount(key, oldCount + 1);
        return kv.get(key);
    }

    private void putCount(int k, int count) {
        kCount.put(k, count);
        countKeys.computeIfAbsent(count, ignore -> new LinkedHashSet<>());
        countKeys.get(count).add(k);
    }

    private void evict(int k) {
        countKeys.get(minCount).remove(k);
        kv.remove(k);
    }

    public void put(int key, int value) {
        if(cap <= 0) return;
        if(kv.containsKey(key)) {
            kv.put(key, value);
            get(key);
            return;
        }
        if(kv.size() >= cap) evict(countKeys.get(minCount).iterator().next());
        minCount = 1;
        putCount(key, minCount);
        kv.put(key,value);
    }
}
