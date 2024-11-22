package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC67AddBinary extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 太久沒做, 純粹邏輯簡化還是回去看舊的 code 才回想起來
    // 關鍵是 carry, sum, StringBuild + reverse()
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (; i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i) - '0';
            if (j >= 0) sum += b.charAt(j) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry == 1) sb.append(1);
        return sb.reverse().toString();
    }

}
