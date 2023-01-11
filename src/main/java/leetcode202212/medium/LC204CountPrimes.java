package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC204CountPrimes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC204CountPrimes();
    }

    /**
     * https://leetcode.com/problems/count-primes/solutions/57588/my-simple-java-solution/
     * 蠻訝異這題可以 O(n^2) 解掉..
     * 就是針對每找到一個 prime, 把他後面的倍數都 mark 不是 prime 就好
     * <p>
     * 不過 這題其實有個 算法
     * https://leetcode.com/problems/count-primes/solutions/57603/java-solution-inspired-from-sieve-of-eratosthenes-also-detailed-a-key-observation/
     * Sieve of Eratosthenes
     * 要得到自然数n以内的全部 primes，必须把不大于 sqrt(n) 的所有 primes 的倍数剔除，剩下的就是primes
     * 這看起來也是 O(N^2) 只是剪枝剪得比較快
     */
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (!notPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    notPrime[j] = true;
                }
            }
        }
        int count = ((n > 2) ? 1 : 0);
        for (int i = 3; i < n; i += 2) if (!notPrime[i]) count++;
        return count;
    }
}
