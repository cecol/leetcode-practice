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

    int[][] g = new int[][]{{1,0},{0,1}};

    /**
     * https://leetcode.com/problems/find-the-celebrity/discuss/71227/Java-Solution.-Two-Pass
     * 其實這題有幾個關鍵認知
     * 1. Celebrity 不知道任何人
     * 2. 其他人都知道 Celebrity
     * <p>
     * 1. loop one 逐一檢查, 只要當前 candidate 知道當前i, 那當前 candidate 一定不是Celebrity
     * -> candidate 改成當前i,  最後留下來的 candidate 是唯一有可能的 candidate
     * 2. loop two 確保其他人真的知道這隻 candidate, 且這隻 candidate 不知道其他人
     *
     * 再回來複習對於第一個 loop 還是沒有很理解, 後來看到下面討論解釋
     * The first loop is to find the candidate as the author explains.
     * In detail, suppose the candidate after the first for loop is person k, it means 0 to k-1 cannot be the celebrity,
     * because they know a previous or current candidate.
     * Also, since k knows no one between k+1 and n-1, k+1 to n-1 can not be the celebrity either.
     * Therefore, k is the only possible celebrity, if there exists one.
     * 如果目前 candidate 是 k
     * 條件一: 代表 0 -> k-1 是無法成為 celebrity, 比如說 1 knows 3, 3 knows 5
     * -> 1 knows 3, 1 一定不是 celebrity, 中間跳過的 2, 已經被 1,3跳過, 就算後面都knows 2, 1,3 也都不know 2, 2也不會是celebrity
     * -> 3 knows 5, 3 一定不是 celebrity, 中間跳過的 4, 已經被 3,5跳過, 就算後面都knows 4, 1,3,5 也都不know 4, 4也不會是celebrity
     * 條件二: k 不在 knows後面的 k+1 .. n-1, 那 k+1 .. n-1 也不可能是 celebrity
     * 最後只要檢查是否 all knows k就好
     */
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) if (knows(candidate, i)) candidate = i;
        for (int i = 0; i < n; i++) if (i != candidate && (!knows(i, candidate) || knows(candidate, i))) return -1;
        return candidate;
    }

    public int findCelebrity2(int n) {
        int[] know = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (knows(i, j)) {
                    know[i]++;
                }
            }
            if (know[i] <= 1) return i;
        }
        return -1;
    }

    boolean knows(int a, int b) {
        return true;
    }

}
