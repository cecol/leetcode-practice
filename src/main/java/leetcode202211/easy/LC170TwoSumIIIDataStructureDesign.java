package leetcode202211.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LC170TwoSumIIIDataStructureDesign extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC170TwoSumIIIDataStructureDesign();
        LC.add(0);
        LC.add(-1);
        LC.add(1);
        LC.find(0);
    }

    Map<Integer, Integer> ts = new HashMap<>();

    public void TwoSum() {

    }

    public void add(int number) {
        ts.put(number, ts.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {
        for (Integer k : ts.keySet()) {
            if (ts.containsKey(value - k)) {
                if (value - k != k) return true;
                else if(ts.getOrDefault(k, 0) > 1) return true;
            }
        }
        return false;
    }
}
