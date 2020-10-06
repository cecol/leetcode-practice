package leetcode202009.easy;


import leetcode202009.BasicTemplate;

public class LC1281SubtractTheProductAndSumOfDigitsOfAnInteger extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1281SubtractTheProductAndSumOfDigitsOfAnInteger();
        LC.subtractProductAndSum(5678);
    }

    public int subtractProductAndSum(int n) {
        int p = 1;
        int s = 0;
        var digits = Integer.toString(n);
        for (int i = 0; i < digits.length(); i++) {
            s += (digits.charAt(i) - '0');
            p *= (digits.charAt(i) - '0');
        }
        return p - s;
    }

    public int subtractProductAndSumStandard(int n) {
        int p = 1;
        int s = 0;
        while (n > 0) {
            p *= n%10;
            s += n%10;
            n /= 10;
        }
        return p - s;
    }

}
