package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

public class LC7ReverseInteger extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 直觀解, 特別處理
    // x == Integer.MIN_VALUE && res > Integer.MAX_VALUE / 10 && res * 10 > Integer.MAX_VALUE - x % 10
    // 不過看原本解答, 有不同思路
    // int newRes = res * 10 + tail;
    // if ((newRes - tail) / 10 != res) return 0;
    // 新的答案如果 OVERFLOW, 也還原不回去了
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) return 0;
        int sign = x > 0 ? 1 : -1;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            if (res > Integer.MAX_VALUE / 10) return 0;
            if (res * 10 > Integer.MAX_VALUE - x % 10) return 0;
            res = res * 10 + x % 10;
            x = x / 10;
        }
        return res * sign;
    }
}
