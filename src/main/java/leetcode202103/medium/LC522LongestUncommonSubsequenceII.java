package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class LC522LongestUncommonSubsequenceII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC522LongestUncommonSubsequenceII();
    }

    /**
     * 我其實一直沒有很能理解 Uncommon Subsequence II 的意思
     * https://leetcode.com/problems/longest-uncommon-subsequence-ii/discuss/99443/Java(15ms)-Sort-%2B-check-subsequence
     * 看了好久總算理解題意, 我一開始以為是要找出一個 Subsequence 都不存在給的String[] strs
     * 想說這三洨, 這樣有無限種可能吧
     * 但其實要的是 某個Subsequence 只屬於 String[] strs中的一個, 其他 str沒有該 Subsequence
     * 而一個str的最長Subsequence就是自己, 然後他自己不要成為其他人的 Subsequence
     * 基本概念就是 如果有個str 是最長的, 沒人跟他一樣長, 那他就是答案
     * 通常這時候 不會只有一個 str最長, 可能最長的有n個 且duplicated, 那麼就得往短一點的往下找了
     * 如果n個最長長度都一樣但字串沒重複, 那任一個最長的都是答案, 因為他們自己的Subsequence只有自己有
     * 所以長度n 如果要是答案 -> 他一定沒有 duplicate + 比他長的都不包含他Subsequence
     * 所以一定要先把
     * 1. String[] strs -> 排序 由長度最長的開始檢查
     * 2. 排序完檢查 duplicated, 後續互相找Subsequence 可以直接跳過 duplicated str
     * 3. 所以從排好的 String[] strs 由長往短的找下去, 如果沒有 duplicated
     * -> 先看看他是不是最長, 是就回傳
     * -> 如果是比較短的字串x, 這樣就要回頭找比x長的是不是有包含x的Subsequence
     */
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (s1, s2) -> s2.length() - s1.length());

        Set<String> dup = getDup(strs);
        for (int i = 0; i < strs.length; i++) {
            if (!dup.contains(strs[i])) {
                if (i == 0) return strs[i].length();
                for (int j = 0; j < i; j++) {
                    if (isSub(strs[j], strs[i])) break;
                    if (j == i - 1) return strs[i].length();
                }
            }
        }
        return -1;
    }

    private boolean isSub(String longer, String shorter) {
        int i = 0, j = 0;
        while (i < longer.length() && j < shorter.length()) {
            if (longer.charAt(i) == shorter.charAt(j)) j++;
            i++;
        }
        return j == shorter.length();
    }

    private Set<String> getDup(String[] strs) {
        Set<String> ss = new HashSet<>();
        Set<String> dup = new HashSet<>();
        for (String s : strs) {
            if (ss.contains(s)) dup.add(s);
            ss.add(s);
        }
        return dup;
    }
}
