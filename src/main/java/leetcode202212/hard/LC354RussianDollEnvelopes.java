package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class LC354RussianDollEnvelopes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC354RussianDollEnvelopes();
    }

    /**
     * https://leetcode.com/problems/russian-doll-envelopes/solutions/1664228/treeset-java/
     * 這題其實先把 Russian Doll 依 Arrays.sort(envelopes, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
     * 高矮下去排序, 然後直接用 LIS O(N^2) 解就可以過
     * 但看來加了 test case 會導致 TLE
     * <p>
     * 所以得另一招
     * LC300LongestIncreasingSubsequence 經典題除了 O(N^2) 還有 O(N Log N) 解法 - TreeSet
     * 就是 直接走過 每個元素, 看當前元素是否造成 TreeSet 變大 nums[i] > ts.last()
     * 有的話就直接加入, 沒有也還是加入, 但 ts 比 nums[i] 大的元素要刪除
     * 最後 ts.size() 就是能找到的 longest subsequence
     * <p>
     * 這邊也是 Russian Doll 依  Arrays.sort(envelopes, (x, y) -> x[0] == y[0] ? y[1] - x[1] : x[0] - y[0]);
     * 注意 當 x[0] == y[0] ? y[1] - x[1] 當高度一樣 就是 依寬度來 降序 陪列
     * 所以當從 左走到右 一定是越來越高  但遇到一樣高 會是越來越 窄 或者更高, 會因為寬度 不一樣不見得可以 合併 Russian doll
     * 然後就可以一樣 TreeSet, 單純用是寬度 比較, 因為左走到右 高度已保證 升序 但寬度不一定, 所以窄的不會造成 TreeSet 變大
     *
     * - Integer widther = ts.ceiling(e[1]);
     * - if (widther != null) ts.remove(widther);
     * - ts.add(e[1]);
     *
     * 最後還是 TreeSet.size() 當答案
     */
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (x, y) -> x[0] == y[0] ? y[1] - x[1] : x[0] - y[0]);
        TreeSet<Integer> ts = new TreeSet<>();
        for (int[] e : envelopes) {
            Integer widther = ts.ceiling(e[1]);
            if (widther != null) ts.remove(widther);
            ts.add(e[1]);
        }
        return ts.size();
    }
}
