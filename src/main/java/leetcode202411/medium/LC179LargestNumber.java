package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.PriorityQueue;

public class LC179LargestNumber extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 直觀解
    public String largestNumber(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> {
            String xs = String.valueOf(x);
            String ys = String.valueOf(y);
            String xy = xs + ys;
            String yx = ys + xs;
            return -xy.compareTo(yx);
        });
        for(int n:nums) pq.offer(n);
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) sb.append(pq.poll());
        while (sb.length() > 1 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.toString();
    }
}
