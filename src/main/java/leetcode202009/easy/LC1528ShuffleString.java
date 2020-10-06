package leetcode202009.easy;


import leetcode202009.BasicTemplate;

import java.util.Arrays;
import java.util.HashMap;

public class LC1528ShuffleString extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1528ShuffleString();
        var s = LC.restoreString("abc", new int[]{2, 1, 0});
    }

    public String restoreString(String s, int[] indices) {
        char[] c = new char[s.length()];
        for (int i = 0; i < s.length(); i++) c[indices[i]] = s.charAt(i);
        log.debug("result: {}", c);
        return new String(c);
    }

}
