package leetcode20200921to20201031.easy;


import leetcode20200921to20201031.BasicTemplate;

public class LC1221SplitAStringInBalancedStrings extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1221SplitAStringInBalancedStrings();
        var s = LC.balancedStringSplit("RLRRLLRLRL");
    }

    public int balancedStringSplit(String s) {
        int l = 0, r = 0, count = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') l++;
            else r++;
            if (l == r) count++;
        }
        return count;
    }
}
