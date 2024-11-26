package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC658FindKClosestElements extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 有過, 先想到 pq + sort, 後想到左右縮減到目標之術
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if (Math.abs(x - a) == Math.abs(x - b)) return a - b;
            else return Math.abs(x - a) - Math.abs(x - b);
        });
        for (int a : arr) pq.add(a);
        List<Integer> res = new ArrayList<>();
        while (k > 0) {
            res.add(pq.poll());
            k--;
        }
        res.sort((a, b) -> a - b);
        return res;
    }

    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int i = 0, j = arr.length - 1;
        while (j - i + 1 > k) {
            if (Math.abs(arr[i] - x) == Math.abs(arr[j] - x)) {
                if (arr[i] < arr[j]) j--;
                else i++;
            } else {
                if (Math.abs(arr[i] - x) < Math.abs(arr[j] - x)) j--;
                else i++;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (i<=j) {
            res.add(arr[i++]);
        }
        return res;
    }
}
