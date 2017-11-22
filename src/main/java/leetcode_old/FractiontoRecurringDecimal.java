package leetcode_old;

import java.util.HashMap;

public class FractiontoRecurringDecimal {
    public static void main(String[] a) {
        System.out.println(fractionToDecimal(1, 5));
        System.out.println(fractionToDecimal(1, 3));
        System.out.println(fractionToDecimal(1, 6));
        System.out.println(fractionToDecimal(1, 99));
        System.out.println(fractionToDecimal(1, 98));
        System.out.println(fractionToDecimal(1, 17));
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder sb = new StringBuilder();
        sb.append((numerator > 0) ^ (denominator > 0) ? "-" : "");
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        sb.append(num / den);
        num %= den;
        if (num == 0) return sb.toString();

        sb.append(".");
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(num, sb.length());

        while (num != 0) {
            num *= 10;
            sb.append(num / den);
            num %= den;
            if (map.get(num) != null) {
                sb.insert(map.get(num), "(");
                sb.append(")");
                num = 0;
                break;
            } else map.put(num, sb.length());
        }

        return sb.toString();
    }

    public static String check(String s) {
        for (int i = 1; i <= s.length() / 2; i++) {
            String s1 = s.substring(s.length() - i, s.length());
            String s2 = s.substring(s.length() - i * 2, s.length() - i);
            if (s1.equals(s2)) return s.substring(0, s.length() - i * 2) + "(" + s.substring(s.length() - i * 2, s.length() - i) + ")";
        }
        return s;
    }
}
