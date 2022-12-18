package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class LC519RandomFlipMatrix extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC519RandomFlipMatrix();
    }

    /**
     * https://leetcode.com/problems/random-flip-matrix/solutions/?q=java&orderBy=most_relevant
     * 這題也適用 reject sampling 做
     * 但比較納悶的是
     *  Set<Integer> picked = new HashSet<>();
     *  來記載 取得的 int cnt = (int) (Math.random() * (m * n));
     *  會很快就過
     *
     *  但我原本是用 Set<String> 記載座標 就很慢 會 TLE...
     *
     *  原本以為 (Math.random() * (m * n) 是不對的, 因為會出現 i*j 相同 (2*3) == (3*2)
     *  但後來我想錯了 (Math.random() * (m * n) 是 i+j, 所以跟笨不會有相同 case
     *  這確實比 random 取2次 x/y 座標來 Set<String> 比較快多了..
     * */
    class Solution {
        int m, n;
        Set<Integer> picked = new HashSet<>();

        public Solution(int m, int n) {
            this.m = m;
            this.n = n;
        }

        public int[] flip() {
            while (true) {
                int cnt = (int) (Math.random() * (m * n));
                if (picked.add(cnt)) {
                    return new int[]{cnt / n, cnt % n};
                }
            }
        }

        public void reset() {
            picked = new HashSet<>();
        }
    }
}
