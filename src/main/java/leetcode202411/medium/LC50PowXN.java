package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.LinkedList;

public class LC50PowXN extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 秒解
    public double myPow(double x, int n) {
        return myPow2(x, (long)n);
    }

    double myPow2(double x, long n) {
        if (n < 0) return myPow2(1 / x, -n);
        if (n == 0) return 1D;
        if (n == 1) return x;
        if (n % 2 == 1) return x * myPow2(x, n - 1);
        else return myPow2(x * x, n / 2);
    }
}
