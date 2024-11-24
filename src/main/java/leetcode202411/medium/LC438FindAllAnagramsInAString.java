package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC438FindAllAnagramsInAString extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 有想出是 2 POINTER, 但完整思路沒有解出來, 回去看舊的解釋
    // 1. 準備 p character count
    // 2. 準備 wc 代表看到幾個 p character count
    // 3. j 走過去 p character count --
    // 4. wc == p size 代表看夠多了(也不能看超過 P SIZE), 但只有 j-i+1 == p.size 才能是答案 (就算 i++ 也可能還是在這)
    // 5. cc[s.charAt(i)] 是 ++ 補回來 & wc-- 代表少了 p case
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        int[] cc = new int[256];
        for (char c : p.toCharArray()) cc[c]++;
        int wc = 0;
        int i = 0, j = 0;
        while (j < s.length()) {
            if (cc[s.charAt(j)] > 0) wc++;
            cc[s.charAt(j)]--;
            while (wc == p.length()) {
                if (j - i + 1 == p.length()) res.add(i);
                cc[s.charAt(i)]++;
                if (cc[s.charAt(i)] > 0) wc--;
                i++;
            }
            j++;
        }
        return res;
    }
}
