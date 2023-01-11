package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC556NextGreaterElementIII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC556NextGreaterElementIII();
    }

    /**
     * 跟 LC31NextPermutation 幾乎一模一樣
     * Ex: 54321 -> 根本不可能找到 -> return -1, 從尾巴 idx 一路找到 -1
     * - while (j >= 0 && nc[j] >= nc[j + 1]) j--;
     * - if (j == -1) return -1;
     * <p>
     * Ex: 1214321 -> 下一個最小數字且大於 n 就是下一個 permutation -> 1221234
     * - 可以看到 121[4321] 尾巴來找 第一個遞減 就是 12[1]4321
     * - 所以第一個 遞減 12[1][4321] 要找前面遞增的 最小剛好大於 [1], 所以是 [4321] 中的 2
     * - [1]/[2] 互換,
     * - int i = nc.length - 1;
     * - while (nc[j] >= nc[i]) i--;
     * - swap(nc, i, j);
     * <p>
     * -  後面的 [4311] 還是從尾巴算起遞增, 要把他們全部反轉  才是下一個 min number
     * <p>
     * - rev(nc, j + 1, nc.length - 1);
     * - 所以變成 12[2][1134]
     */

    public int nextGreaterElement(int n) {
        char[] nc = String.valueOf(n).toCharArray();
        if (nc.length == 1) return -1;
        int j = nc.length - 2;
        while (j >= 0 && nc[j] >= nc[j + 1]) j--;
        if (j == -1) return -1;
        int i = nc.length - 1;
        while (nc[j] >= nc[i]) i--;
        swap(nc, i, j);
        rev(nc, j + 1, nc.length - 1);
        long res = Long.parseLong(new String(nc));
        return res > Integer.MAX_VALUE ? -1 : (int) res;
    }

    void rev(char[] nc, int i, int j) {
        while (i < j) swap(nc, i++, j--);
    }

    void swap(char[] nc, int i, int j) {
        char tmp = nc[i];
        nc[i] = nc[j];
        nc[j] = tmp;
    }
}
