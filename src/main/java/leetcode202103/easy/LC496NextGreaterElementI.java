package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC496NextGreaterElementI extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC496NextGreaterElementI();
        LC.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
    }

    /**
     * 原本以為我不會, 後來才發現是題目敘述太爛,
     * 我看成以為 int[] nums1 element x 下一的大的得在 nums2 同位置之後開始找, 但事實上不用
     * https://leetcode.com/problems/next-greater-element-i/discuss/97614/Confusing-statement
     *
     * 基本上就是維持一個 monotonic decrease stack
     * 如果遇到 nums2[i] > stack.top, 代表可以兌現前面的元素
     * 就找出該兌現是否出現在 nums1, 有就更新 兌現 的 next greater
     * */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> q = new LinkedList<>();
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            while (q.size() > 0 && q.getLast() < nums2[i]) {
                Integer gi = q.pollLast();
                m.put(gi, nums2[i]);
            }
            q.add(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = m.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }
}
