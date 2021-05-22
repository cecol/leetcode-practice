package leetcode202105.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC541ReverseStringII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC541ReverseStringII();
    }

    /**
     * 很直觀的 iterate s string 解過去, 當前i 算出 k1End, k2End 就有答案了
     * 不過有看到更簡單的答案
     * https://leetcode.com/problems/reverse-string-ii/discuss/100866/Java-Concise-Solution
     * 先把 s 變成 char[] 然後 只針對 first half k 去 swap
     */
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int i = 0;
        while (i < n) {
            int j = Math.min(i + k - 1, n - 1);
            swap(arr, i, j);
            i += 2 * k;
        }
        return String.valueOf(arr);
    }

    private void swap(char[] arr, int i, int j) {
        while (i < j) {
            char t = arr[i];
            arr[i++] = arr[j];
            arr[j--] = t;
        }
    }


    public String reverseStrSlow(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; ) {
            int k2End = i + 2 * k;
            int k1End = i + k;
            StringBuilder f1k = new StringBuilder();
            for (int j = i; j < k1End && j < n; j++) f1k.append(s.charAt(j));
            sb.append(f1k.reverse().toString());
            StringBuilder f2k = new StringBuilder();
            for (int j = k1End; j < k2End && j < n; j++) sb.append(s.charAt(j));
            i = k2End;
        }
        return sb.toString();
    }
}
