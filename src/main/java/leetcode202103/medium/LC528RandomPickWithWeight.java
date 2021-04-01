package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC528RandomPickWithWeight extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC528RandomPickWithWeight();
        LC.Solution(new int[]{1, 3, 2, 4, 1});
    }

    /**
     * https://leetcode.com/problems/random-pick-with-weight/discuss/671445/Question-explained
     * 覺得沒有看懂這題
     * 1. pickIndex 的是 index, int[] w給的是每個index的機率
     * 2. 解答其實很精妙 double[] prob 是區間
     * w = [1, 3, 2, 4, 1]
     * sum = 11
     * w = [1, 4, 6, 10, 11]
     * probabilities=[1/11, 4/11, 6/11, 10/11, 11/11]
     * Visualize probabilities:
     * [-, ----, ------, ----------, -----------]
     * in Arrays.binarySearch(this.probabilities, Math.random()),
     * We generate a random number within range [0, 1].
     * The possibility to generate a random value between [1/11, 4/11] is (4-1)/11
     * <p>
     * 所以就是為什麼要累加到當前 i 的加總來當作機率, 因為是區間關係
     * double[] prob中 i 與 i+1 的差值就是 pickIndex 要拿的該index的機率, 越寬代表當初 w[i] 就越大
     * -> 所以Math.random() 產生的 0 -> 1.0 的值比較高機率落入 i 與 i+1 的寬度 -> 正好反映w[i]大機率越高
     * 所以 prob 不是記載每一格index的機率, 而是 prob中 i 與 i+1的差值才是 index 機率
     * 因此prob計算過程是用 w[i] 累加
     * -> 所以Math.random() 是平均產生 -> 但會不平均的落入在 double[] prob中某一區間
     * -> 所以用 binary search 來找應該要若入哪一格
     * -> 因為找到的值可能不在 double[] prob中, 所以要Math.abs再減一
     * <p>
     * 這是 ML 演算法
     * for picking samples with weight in * Boost Decision Tree. This is the best space optimized solution.
     */
    private double[] prob = null;

    public void Solution(int[] w) {
        double s = 0;
        this.prob = new double[w.length];
        for (int n : w) s += n;
        for (int i = 0; i < w.length; i++) {
            w[i] += i == 0 ? 0 : w[i - 1];
            prob[i] = w[i] / s;
        }
    }

    public int pickIndex() {
        return Math.abs(Arrays.binarySearch(this.prob, Math.random())) - 1;
    }
}
