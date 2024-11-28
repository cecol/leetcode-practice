package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LC528RandomPickWithWeight extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 做一個 double[] prob 是目前的 w[sum of 0 to i]/sum w
    // 這樣就可以用 Math.random & binarySearch Find 找到 idx 且符合 weight
    double[] prob = null;

    public void Solution(int[] w) {
        double s = 0D;
        prob = new double[w.length];
        for (int n : w) s += n;
        for (int i = 0; i < w.length; i++) {
            w[i] += i == 0 ? 0 : w[i - 1];
            prob[i] = w[i] / s;
        }
    }

    public int pickIndex() {
        return Math.abs(Arrays.binarySearch(prob, Math.random())) - 1;
    }
}
