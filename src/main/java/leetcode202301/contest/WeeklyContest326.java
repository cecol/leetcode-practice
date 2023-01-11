package leetcode202301.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Int;

import java.io.IOException;
import java.util.*;


public class WeeklyContest326 extends BasicTemplate {
    public static void main(String[] args) throws IOException {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new WeeklyContest326();
//        LC.distinctPrimeFactors(new int[]{5, 3});
        LC.minimumPartition("165462",
                60);
//        LC.minimumPartition("238182", 5);
    }

    public int countDigits(int n) {
        int res = 0;
        for (char c : String.valueOf(n).toCharArray()) {
            if (n % (c - '0') == 0) res++;
        }
        return res;
    }

    boolean[] primes = new boolean[1000];

    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> all = new HashSet<>();
        for (int n : nums) {
            Set<Integer> sub = getPrimes(n);
            all.addAll(sub);
        }
        return all.size();
    }

    Set<Integer> getPrimes(int n) {
        Set<Integer> all = new HashSet<>();
        int i = 2;
        while (i <= n) {
            if (n % i == 0) {
                n /= i;
                all.add(i);
                i = 2;
            } else i++;
        }
        return all;
    }

    Map<String, Integer> dp = new HashMap<>();

    public int minimumPartition(String s, int k) {
        for (char c : s.toCharArray()) if (c - '0' > k) return -1;
        return dfs(s, k);
    }

    int dfs(String s, int k) {
        if (s.length() == 0) return 0;
        if (dp.containsKey(s)) dp.get(s);
        int min = Integer.MAX_VALUE;


        int i = 0;
        for (; i < s.length() && Integer.parseInt(s.substring(0, i + 1)) <= k; i++) ;
        min = Math.min(min, dfs(s.substring(i), k) + 1);
        if (i - 1 >= 0) min = Math.min(min, dfs(s.substring(i - 1), k) + 1);
        dp.put(s, min);
        return min;
    }

    public int[] closestPrimes(int l, int r) {
        r++;
        boolean[] notPrime = new boolean[r];
        notPrime[1] = true;
        int sqrt = (int) Math.sqrt(r);
        for (int i = 2; i <= sqrt; i++) {
            if (!notPrime[i]) {
                for (int j = i * i; j < r; j += i) {
                    notPrime[j] = true;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int preP = -1;
        int[] res = new int[]{-1, -1};
        for (int i = l; i < r; i++) {
            if (!notPrime[i]) {
                if (preP == -1) preP = i;
                else {
                    if (i - preP < min) {
                        min = i - preP;
                        res[0] = preP;
                        res[1] = i;
                    }
                    preP = i;
                }
            }
        }
        return res;
    }
}