package leetcode_old;

import java.util.*;

public class LargestNumber {
    public static void main(String[] s) {
        int[] n1 = {3, 30, 34, 5, 9};
        System.out.println(largestNumber(n1));
        int[] n2 = {128, 12};
        System.out.println(largestNumber(n2));
        int[] n3 = {121, 12};
        System.out.println(largestNumber(n3));
        int[] n4 = {999999998, 999999997, 999999999};
        System.out.println(largestNumber(n4));
    }

    public static String largestNumber(int[] nums) {
        StringBuilder sb = new StringBuilder("");
        if (nums == null) return sb.toString();
        List<String> list = new LinkedList<>();
        for (int i : nums) list.add(Integer.toString(i));
        Collections.sort(list, (l, r) -> l.charAt(0) != r.charAt(0) ? -(l.charAt(0) - r.charAt(0)) : l.length() == r.length() ? -l.compareTo(r) : -(l + r).compareTo(r + l));
        for (String s : list) sb.append(s);
        return sb.toString().charAt(0) == '0' ? "0" : sb.toString();
    }
}
