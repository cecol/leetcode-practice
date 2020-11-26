package leetcode202011.hard;

import leetcode20200921to20201031.BasicTemplate;

public class LC546RemoveBoxes extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC546RemoveBoxes();
        var s = LC.removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1});
    }

    /**
     * 先看懂花花醬有解釋基本原理(但沒看懂後面的case 2)
     * https://www.youtube.com/watch?v=wT7aS5fHZhs&feature=emb_title&ab_channel=HuaHua
     * <p>
     * 這邊更完整的解釋dp[i][j][k]的解釋方式
     * https://leetcode.com/problems/remove-boxes/discuss/386810/Python-my-thoughts-on-DP-detailed-explanation
     * example case boxes = [1,2,1,1,4,3,1,3,1]
     * index:  [0,1,2,3,4,5,6,7,8]
     * 1. dp[i][j][k] 是記載i到j的最佳解
     * 且i左邊的元素都被刪光了, 只留下左邊k個與 boxes[i]相同的元素
     * 重點在於 i 左邊都當作考慮過了(dp算過了 -> dp切割的子問題 -> 左邊就是前面被切下已算過得子問題)
     * 所以這題的dp子問題切法就是 0 to i-1 與 i to j
     * ex dp[3][8][2] 的 3 to 8, 就是指 boxes: [1,2,1,1,4,3,1,3,1] 中的 index 3 to 8的最佳解
     * 這時boxes[3] = 1, 且子問題boxes[0 to 2] case 就當作已被考慮完了
     * [0-2]探討過且也記錄過, 然後有留下[0 to 2]間有元素等同於boxes[3] = 1的case, 共有2(k)個
     * 2. 這時候dp[i][j][k]有幾個選擇
     * 1. 先拿當前的k與i結算能拿到的基礎分數
     * 就是 (k + 1) * (k + 1) + rb(b, dp, i + 1, j, 0);
     * 因為我已知[0-2]以解完的子問題保有k個boxes[3], 再加上boxes[i]的情況
     * 有基本分數 k+1
     * 其實 [i+1-j]之前可能有更多boxes[3]=1的值可以繼續累加下去拿到更加解
     * 但目前先不考慮, 先當作[i+1 to j]沒有更多boxes[3]=1的值可以繼續累加
     * 算出一個基本值
     * 所以 (k + 1) * (k + 1) + rb(b, dp, i + 1, j, 0);
     * 中(k+1) 就是 [0 to i-1] 已累計的2個, 再加上第i個算出可以得到的分數
     * rb(b, dp, i + 1, j, 0) 就是剩下 i+1 to j 的子問題繼續解
     * 2. 真正考慮 [i+1 to j]之間還有更多boxes[3]=1的值可以繼續累加下去拿到更加解
     * 這種情境下就得在 [i+1 to j]中找到下一個值也等於boxes[3] = 2
     * 所以有個回圈 m 從i+1開始找直到j
     * 找到 boxes[m] == boxes[i]
     * 然後找到後就會切分左右子問題
     * 所以上面的dp[3][8][2], m就會從4開始找到6, boxes[6]==boxes[3]
     * 所以[3 to 8]最佳解可能是由下列子問題組成:
     * 左子問題 - 裡面沒有累計k, 因為都是boxes[m] != boxes[i]
     * [4 to 5] 這時k = 0,因為中間都不等於boxes[3]==1, k值重新計算
     * 所以要算出dp[4][5][0]
     * 與
     * 右子問題 - 裡面有累計k, 因為找到boxes[m] == boxes[i]
     * [6 to 8] 這時boxes[6]==boxes[3]==1, k值多一個 => 所以才有可能產出更加解
     * 所以要算出dp[6][8][3]
     * ([6 to 8]中是由[3 to 8]分出來的子問題, [0 to 2]已累計k=2,
     * 因此6 to 8計算中的k就是[3 to 8]的k=2多補上boxes[3]==1的案例, 因此是k+1)
     * <p>
     * 完整的java code參考下面
     * https://leetcode.com/problems/remove-boxes/discuss/101310/Java-top-down-and-bottom-up-DP-solutions
     * (這個是一開始看的解釋, 但沒有看得很懂)
     */
    public int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) return 0;
        if (boxes.length == 1) return 1;
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return rb(boxes, dp, 0, n - 1, 0);
    }

    public int rb(int[] b, int[][][] dp, int i, int j, int k) {
        if (i > j) return 0;
        if (dp[i][j][k] > 0) return dp[i][j][k];
        //優化步驟, 如果剛好boxes[i+1]==boxes[i], 先繼續往下找, 直到下一個不等於boxes[i], 才可以切出左右問題
        for (; i + 1 < j && b[i] == b[i + 1]; i++, k++) ;
        int res = (k + 1) * (k + 1) + rb(b, dp, i + 1, j, 0);
        for (int m = i + 1; m <= j; m++) {
            if (b[i] == b[m]) {
                res = Math.max(
                        res,
                        //左邊子問題 i+1 to m-1 前後都不等於boxes[i], 找出 i+1 to m-1最佳解
                        rb(b, dp, i + 1, m - 1, 0) +
                                //右邊子問題, boxes[m] == boxes[i], 找出 m to j最佳解
                                rb(b, dp, m, j, k + 1)
                );
            }
        }
        dp[i][j][k] = res;
        return res;
    }
}
