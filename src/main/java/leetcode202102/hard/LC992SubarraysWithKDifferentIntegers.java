package leetcode202102.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC992SubarraysWithKDifferentIntegers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC992SubarraysWithKDifferentIntegers();
        var s = LC.subarraysWithKDistinct(null, 0);
    }

    /**
     * 這題真難, 還在參透中
     * https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/523136/JavaC%2B%2BPython-Sliding-Window
     *
     * */
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMost(A, K) - atMost(A, K - 1);
    }

    private int atMost(int[] a, int K) {
        int i = 0, res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int j = 0; j < a.length; j++) {
            if (count.getOrDefault(a[j], 0) == 0) K--;
            count.put(a[j], count.getOrDefault(a[j], 0) + 1);
            while (K < 0) {
                count.put(a[i], count.get(a[i]) - 1);
                if (count.get(a[i]) == 0) K++;
                i++;
            }
            res += j - i + 1;
        }
        return res;
    }
}
