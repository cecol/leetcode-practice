package leetcode202105.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC1207UniqueNumberOfOccurrences extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1207UniqueNumberOfOccurrences();
    }

    /**
     * 很直觀的去算count 確認是否 duplicated, 但很慢 3 ms, faster than 10.61%
     * 但我看比較快的解法只是用 int[] c = new int[2001]; 取代 count map,
     * */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> mc = new HashMap<>();
        for(int n: arr) mc.put(n,mc.getOrDefault(n,0)+1);
        Set<Integer> c = new HashSet<>();
        for(Integer v: mc.values()) {
            if(c.contains(v)) return false;
            else c.add(v);
        }
        return true;
    }
}
