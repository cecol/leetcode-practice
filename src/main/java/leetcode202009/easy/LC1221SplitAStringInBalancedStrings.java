package leetcode202009.easy;


import leetcode202009.BasicTemplate;

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
