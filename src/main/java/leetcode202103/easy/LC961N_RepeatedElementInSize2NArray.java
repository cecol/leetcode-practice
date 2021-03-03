package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC961N_RepeatedElementInSize2NArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC961N_RepeatedElementInSize2NArray();
        LC.repeatedNTimes(new int[]{1, 2, 3, 3});
    }

    public int repeatedNTimes(int[] A) {
        Set<Integer> m = new HashSet<>();
        for (int a : A) {
            if(m.contains(a)) return a;
            else m.add(a);
        }
        return 0;
    }
}
