package leetcode20200921to20201031.easy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class LC1512NumberOfGoodPairs {
  public static Logger logger = LoggerFactory.getLogger("LC1512NumberOfGoodPairs");
  public static void main(String[] args) {
    logger.info("result: {}", numIdenticalPairs2(new int[]{1,1,1,1}));
  }

  public static int numIdenticalPairs(int[] nums) {
    HashMap<Integer, Integer> h = new HashMap<>();
    for (int i : nums) {
      if (h.containsKey(i)) {
        int v = h.remove(i);
        h.put(i, v + 1);
      } else {
        h.put(i, 1);
      }
    }
    int s = 0;
    for (Integer i : h.values()) s+=(i-1)*i/2;

    return s;
  }

  public static int numIdenticalPairs2(int[] nums) {
    HashMap<Integer, Integer> h = new HashMap<>();
    int s = 0;
    for (int i : nums) {
      if (h.containsKey(i)) {
        int v = h.remove(i);
        h.put(i, v + 1);
      } else {
        h.put(i, 1);
      }
      s += h.get(i) - 1;
    }
    return s;
  }
}
