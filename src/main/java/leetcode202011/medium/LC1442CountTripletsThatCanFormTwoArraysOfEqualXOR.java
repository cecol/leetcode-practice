package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR();
        LC.countTriplets(new int[]{2, 3, 1, 6, 7});
        LC.countTriplets2(new int[]{2, 3, 1, 6, 7});
    }

    /**
     * A ^ B == 0 => A == B
     * look for XOR[i .. k] == 0
     * XOR[i .. k] == 0
     *  A[0] ^ A[1] ^ ... ^ A[i-1]
     *      XOR
     *  A[0] ^ A[1] ^ ... ^ A[i-1] ^ A[i] ^ A[i+1] ^ ... ^ A[k]
     *  == 0
     * */
    public int countTriplets(int[] arr) {
        int c = 0;
        if (arr == null || arr.length < 2) return c;
        int[] xors = new int[arr.length + 1];
        for (int i = 1; i < xors.length; i++) xors[i] = xors[i - 1] ^ arr[i - 1];
        for (int i = 0; i < xors.length; i++) {
            for (int k = i + 1; k < xors.length; k++) {
                if ((xors[i] ^ xors[k]) == 0) c += (k - i - 1);
            }
        }
        return c;
    }

    public int countTriplets2(int[] arr) {
        int c = 0;
        if (arr == null || arr.length < 2) return c;
        Map<Integer, Integer> xorCount = new HashMap<>();
        Map<Integer, Integer> indexSum = new HashMap<>();
        xorCount.put(0, 1);
        int xorSum = 0;
        for (int i = 0; i < arr.length; i++) {
            xorSum ^= arr[i];
            int count = xorCount.getOrDefault(xorSum, 0);
            int sum = indexSum.getOrDefault(xorSum, 0);
            c += count * i - sum;
            xorCount.put(xorSum, count + 1);
            indexSum.put(xorSum, i + sum + 1);
        }
        return c;
    }
}
