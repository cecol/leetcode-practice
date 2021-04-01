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
     * 後來解了幾題 atMost概念, 就完全理解這種 sybarray(sliding window)的計數就善用
     * window 從小長到大, 擴表, 邊長大只要條件還=<K,就邊計數(但這過程中計數到多餘的<K案例)
     * 如果條件不合, 就縮表直到條件符合
     *
     * 又花了時間進一步搞懂為什麼 res += j - i + 1
     * 解答的第一個回應有提到
     * 在長度是 4的 array 可以產出 1+2+3+4種 subarray
     * [1,2,1,2] will produce a total of 10 different contiguous subarrays:
     *
     * -> [1,2,1,2] (1 different contiguous subarrays with length 4)
     * -> [1,2,1], [2,1,2] (2 different contiguous subarrays with length 3)
     * -> [1, 2], [1,2], [2,1](3 different contiguous subarrays with length 2)
     * -> [1], [2], [1], [2] (4 different contiguous subarrays with length 1)
     *
     * 所以在 sliding window 擴大過程中都要一直 res += j - i - 1, 就是幫當前的 array的所有合法組成都納入計算
     * array從長度 1 -> 2 -> 3 -> 4 (都還在符合k個以內) 所以會有 1+2+3+4=10種都可以符合 k 的subarry組合
     * 所以 res += j-i+1 -> 在 j 的變長過程中, 是沒有疊加問題, 因為每次j變長, 計算的subarray次數是累加下去的
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
