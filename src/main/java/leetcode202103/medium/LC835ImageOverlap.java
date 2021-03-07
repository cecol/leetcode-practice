package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class LC835ImageOverlap extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC835ImageOverlap();
        LC.largestOverlap(new int[][]{{1, 1, 0}, {0, 1, 0}, {0, 1, 0}}, new int[][]{{0, 0, 0}, {0, 1, 1}, {0, 0, 1}});
    }

    /**
     * 這題讓我有點訝異, 我只有想到 每移動一次就去檢查 overlap, 想說這個方法不會過
     * 結果找了一下答案, 前面確實在討論 O(N^4) 效果不好
     * 這篇有說sparse matrix 效果很差
     * https://leetcode.com/problems/image-overlap/discuss/130623/C%2B%2BJavaPython-Straight-Forward
     * 所以他有優化
     * 但後來找到最無腦的一開始解法的答案 Java most intuitive solution
     * https://leetcode.com/problems/image-overlap/discuss/161851/Java-most-intuitive-solution
     * 只是它是從左下角開始移動 img2 來比對, 但也有 faster than 55.90%
     * 覺得神奇: To my surprise, this O(N ^ 4) approach is faster than the hashed/HashMap one.
     * 我後來覺得就學好暴力解就好 因為優化的解法, 可能也真的考試候不見得想得到
     */
    public int largestOverlap(int[][] img1, int[][] img2) {
        int res = 0;
        int n = img1.length;
        for (int rOffset = -n; rOffset < n; rOffset++) {
            for (int cOffset = -n; cOffset < n; cOffset++) {
                res = Math.max(over(rOffset, cOffset, img1, img2), res);
            }
        }
        return res;
    }

    private int over(int r, int c, int[][] m1, int[][] m2) {
        int n = m1.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = i + r, y = j + c;
                if (x >= n || y >= n || x < 0 || y < 0) continue;
                else if (m1[x][y] == 1 && m2[i][j] == 1) res++;
            }
        }
        return res;
    }
}
