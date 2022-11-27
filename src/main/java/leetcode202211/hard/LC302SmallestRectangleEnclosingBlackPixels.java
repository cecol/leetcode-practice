package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC302SmallestRectangleEnclosingBlackPixels extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC302SmallestRectangleEnclosingBlackPixels();
    }

    /**
     * https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/solution/
     * 官方解答裏面有最基礎的線性解法 O(M*N)
     * 要找出 黑色區域的 4 個邊界, left, right, top and bottom
     * 但是我不知道解答為什麼是  (right - left) * (bottom - top) ?
     * <p>
     * 重新看了題目才看懂要問什麼
     * 基本上就是找個 最小矩形 可以把所有 黑色區域 覆蓋住
     * 原本我以為是找出 所有邊界包圍起來 黑色區域
     * <p>
     * 所以 最小矩形 自然就是 黑色區域 所踩到的 上下左右 邊界極值的面積
     * 自然是 (right - left) * (bottom - top)
     * <p>
     * 最直觀就是 O(M*N) 把 黑色區域 邊界極值找出來
     * DFS, BFS, 或者無腦掃過整個 image
     * <p>
     * 更進階就是 binary search 找出 黑色區域 邊界極值找出來
     * 關鍵是把 2D 投影成 1D, 這樣在那 1D 可以做 binary search 來快速找到 left, right, top and bottom:
     * (只要是 聯通的 黑色區域, 他的 x/y 投影都是聯通的 , 如果 x/y 投影都不是聯通的, 代表 黑色區域 不是聯通)
     * <p>
     * Binary search to find left in the row array within [0, y)
     * Binary search to find right in the row array within [y + 1, n)
     * Binary search to find top in the column array within [0, x)
     * Binary search to find bottom in the column array within [x + 1, m)
     * <p>
     * 可是投影 preprocess 也是 O(MN)
     * tricky 是邊做 binary search 過程中邊投影
     * Ex: 找 0 -> y 最左, 所以是 0 -> y 做 binary search, 但這個 binary search 得每一 row 檢查
     * 只要有一 row 是 黑色, 往左延伸, 只要所有 row 都是 白色, 往右延伸
     * 所以時間複雜度是 M * O(LogN)
     * <p>
     * 其他 right, top and bottom 也是如此
     * <p>
     * 整體時間複雜度是 M * O(LogN) + N * O(LogM)
     */
    public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int left = fc(image, 0, y, 0, m, true);
        int right = fc(image, y+1, n, 0, m, false);
        int top = fr(image, 0, x, left, right, true);
        int down = fr(image, x+1, m, left, right, false);
        return (right - left) * (down - top);
    }

    int fc(char[][] image, int s, int e, int r0, int r1, boolean whiteToBlack) {
        while (s != e) {
            int k = r0, mid = s + (e - s) / 2;
            while (k < r1 && image[k][mid] == '0') k++;
            if (k < r1 == whiteToBlack) e = mid;
            else s = mid + 1;
        }
        return s;
    }

    int fr(char[][] image, int s, int e, int c0, int c1, boolean whiteToBlack) {
        while (s != e) {
            int k = c0, mid = s + (e - s) / 2;
            while (k < c1 && image[mid][k] == '0') k++;
            if (k < c1 == whiteToBlack) e = mid;
            else s = mid + 1;
        }
        return s;
    }
}
