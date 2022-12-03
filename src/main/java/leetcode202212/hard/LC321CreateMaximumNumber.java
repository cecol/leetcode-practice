package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class LC321CreateMaximumNumber extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC321CreateMaximumNumber();
        LC.maxNumber(new int[]{2, 5, 6, 4, 4, 0}
                , new int[]{7, 3, 8, 0, 6, 5, 7, 6, 2}, 15);
    }

    /**
     * https://github.com/wisdompeak/LeetCode/blob/master/Stack/321.Create-Maximum-Number/321.Create-Maximum-Number.cpp
     * 觀念沒有很難  但很多細節處理在 JAVA 沒有像 C++ 有直接可以用的 lib, 沒做好會 TLE
     * 1. nums1 中間找 i 個, 依序最大的序列 -> 觀念跟 LC402RemoveKDigits 一樣 只是找 max
     * 2. nums2 中間找 k-i 個, 依序最大的序列 -> 觀念跟 LC402RemoveKDigits 一樣 只是找 max
     * 後面做法我自己做會 TLE, 參考下面才通過
     * 3. merge nums1_max, nums2_max 這兩個 array
     * https://leetcode.com/problems/create-maximum-number/discuss/77299/divide-to-three-subproblem-solution-beat-98
     * - merge func 就好比 nums1 轉成字串, num2 轉成字串, 兩者比較, Ex: n1.string > n2.string, 取 n1[0], n1 = n1[1:n-1]
     * - 上面做法會 TLE, C++ 可以直接兩個 array 比較有如同 string 比較效果
     * - JAVA 是自己寫個 compare2Arrays 來一個個比較, 還要處理其中一個是 empty array case
     * 4. 比較看看當前 merge 結果是否是目前找到的最大
     * - int[] max(int[] n1, int[] n2)
     * - 比較直觀, 就是兩者比較
     * */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        if(nums1.length + nums2.length < k) return res;
        else if(nums1.length + nums2.length == k) return merge(nums1, nums2, k);
        for (int i = 0; i <= k; i++) {
            if (i > nums1.length) continue;
            if (k - i > nums2.length) continue;
            int[] p1 = findMax(nums1, i);
            int[] p2 = findMax(nums2, k - i);
            int[] merged = merge(p1, p2, k);
            res = max(res, merged);
        }
        return res;
    }

    int[] max(int[] n1, int[] n2) {
        for (int i = 0; i < n1.length && i < n2.length; i++) {
            if (n1[i] > n2[i]) return n1;
            else if (n2[i] > n1[i]) return n2;
        }
        return n1.length > n2.length ? n1 : n2;
    }

    int[] merge(int[] n1, int[] n2, int k) {
        int[] res = new int[k];
        int idx1 = 0, idx2 = 0;
        int idx = 0;
        while (idx < k) {
            if (compare2Arrays(n1, idx1, n2, idx2)) res[idx] = n1[idx1++];
            else res[idx] = n2[idx2++];
            idx++;
        }
        return res;
    }

    boolean compare2Arrays(int[] n1, int idx1, int[] n2, int idx2) {
        int l1 = n1.length - idx1;
        if (l1 <= 0) return false;
        int l2 = n2.length - idx2;
        if (l2 <= 0) return true;
        int len = Math.max(l1, l2);
        for (int i = 0; i < len; i++) {
            int d1 = idx1 + i < n1.length ? n1[idx1 + i] : 0;
            int d2 = idx2 + i < n2.length ? n2[idx2 + i] : 0;
            if (d1 != d2) return d1 > d2;
        }
        return true;
    }

    int[] findMax(int[] A, int k) {
        int[] res = new int[k];
        if (k == 0) return res;
        int n = A.length;
        int drop = n - k;
        Deque<Integer> sk = new LinkedList<>();
        for (int a : A) {
            while (sk.size() > 0 && sk.peekLast() < a && drop > 0) {
                sk.pollLast();
                drop--;
            }
            sk.offerLast(a);
        }
        int i = 0;
        while (i < k) res[i++] = sk.pollFirst();
        return res;
    }
}
