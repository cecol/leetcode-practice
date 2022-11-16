package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC1524NumberOfSubarraysWithOddSum extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * 蠻直觀的, preSum, 但一般都是找確定值 in HashMap
     * 這次要找加總是 odd
     * 加總是 odd, 就是當前 sum
     * 1. 如果是 even, 那前面有出現過 preSum 是 odd count 就是加上去
     * 2. 如果是 odd, 那前面有出現過 preSum 是 count count 就是加上去,
     * 這邊要多加 1, 因為 odd 自己本身也算 1, 就是offset 0 到自己全取的 subarray sum 也是 odd
     * */
    public int numOfSubarrays(int[] arr) {
        int oc = 0, ec = 0;
        int res = 0, sum = 0;
        int mod = (int) 1e9 + 7;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (sum % 2 == 0) {
                res = (res + oc) % mod;
                ec++;
            } else {
                res = (res + ec) % mod + 1;
                oc++;
            }
        }
        return res;
    }
}
