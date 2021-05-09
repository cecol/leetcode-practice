package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1053PreviousPermutationWithOneSwap extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1053PreviousPermutationWithOneSwap();
    }

    /**
     * https://leetcode.com/problems/previous-permutation-with-one-swap/discuss/299244/Similar-to-find-previous-permutation-written-in-Java
     * 看來這題是很相似 next permutation
     * 但我mock test當下沒有辦法好好辨識出來
     * next permutation 是找下一個遞增 -> 從尾巴找出:遞增 -> 遞減 pattern
     * -> 所以遞減那個換成大的就是 next (後面還要 rev, 因為要把後面變成遞減)
     * pre permutation 是找下一個遞減 -> 從尾巴找出:遞減 -> 遞增 pattern -> 所以遞增那個換成小的就是 pre
     * pre permutation 不用後續 rev, 因為只有 遞增元素換成小一號的 largest, 後面就都是遞減, 所以省去 rev後續
     * -> 只是再找 小一號的 largest時候得處理 duplicate, 因為越往前換, 越接近 pre max
     * ->   因為我們是從tail往回找, ex [3,1,1,3] ,如果不檢查 duplicate 就會變成 [1,1,3,3] 而非 [1,3,1,3]
     */
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        if (n < 2) return arr;
        int j = n - 2;
        while (j >= 0 && arr[j] <= arr[j + 1]) j--;
        if (j >= 0) {
            int i = n - 1;
            while (arr[j] <= arr[i] || arr[i] == arr[i - 1]) i--;
            int t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
        }
        return arr;
    }
}
