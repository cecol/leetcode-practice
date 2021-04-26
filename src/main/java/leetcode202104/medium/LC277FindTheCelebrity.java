package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class LC277FindTheCelebrity extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC277FindTheCelebrity();
    }

    /**
     * https://leetcode.com/problems/find-the-celebrity/discuss/71227/Java-Solution.-Two-Pass
     * 其實這題有幾個關鍵認知
     * 1. Celebrity 不知道任何人
     * 2. 其他人都知道 Celebrity
     * <p>
     * 1. loop one 逐一檢查, 只要當前 candidate 知道當前i, 那當前 candidate 一定不是Celebrity
     * -> candidate 改成當前i,  最後留下來的 candidate 是唯一有可能的 candidate
     * 2. loop two 確保其他人真的知道這隻 candidate, 且這隻 candidate 不知道其他人
     */
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) if (knows(candidate, i)) candidate = i;
        for (int i = 0; i < n; i++) if (i != candidate && (!knows(i, candidate) || knows(candidate, i))) return -1;
        return candidate;
    }

    boolean knows(int a, int b) {
        return true;
    }

}
