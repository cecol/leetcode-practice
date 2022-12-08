package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC838PushDominoes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC838PushDominoes();
    }

    /**
     * https://leetcode.com/problems/push-dominoes/solutions/132314/simple-java-solution-using-two-pointers-with-explanation/
     * 不知道這題跟 BFS 有什麼關聯讓 wisdompeak 放在 BFS?
     *
     * 提議要先釐清 RR.L
     * 第一個 R 是無作用的, 因為他的下一個是R
     * we will consider that a falling domino expends no additional force to a falling or already fallen domino.
     *
     * 解答是很直觀的針對每個 str[i] = '.' 下去看
     * 1. pre 往左找到非 .
     * 2. next 往右找到非 .
     * 3. if dominoes.next != 'L', 因為只受到最近的 L 影響, 如果不是 就不用考慮
     * 4. if dominoes.pre != 'R', 因為只受到最近的 r 影響, 如果不是 就不用考慮
     * 5. 如果左右的 L, R 同等數量, 也不考慮
     * 6. 看 str[i] = '.' 離左邊的 R or 右邊的 L 哪個近就轉成誰
     *
     * 有個關鍵是, 每次都是拿 dominoes 去比對
     * 不是用前面已經把 str[i] = '.' 轉成 R or L 的結果繼續比對,
     * 因為L/R 是同時發生, 但我們再掃 str[i]是由左到右,
     * 非同等時間考慮所有倒下情況, 所以用 str[i]來判定 pre, next 是錯誤的
     *
     * There is one trick though. We are trying to find L on right side,
     * what if we find R first while scanning,
     * then even if find L after this R this is not useful as they will balance each other.
     * e.g .RL in this case L will have no effect on dot.
     * Same thing on left side if find L on left side, there is not point to find R after that
     *
     * 這邊說 str[i] = '.' 往右(next)找我們只看第一個 L, 如果是 RL 根本不必處理
     * 因為
     * a falling domino expends no additional force to a falling or already fallen domino.
     * */
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] str = dominoes.toCharArray();
        for (int i = 0; i < n; i++) {
            if (dominoes.charAt(i) == '.') {
                int pre = i - 1, next = i + 1;
                for (; pre >= 0 && dominoes.charAt(pre) != 'R' && dominoes.charAt(pre) != 'L'; pre--) ;
                for (; next < n && dominoes.charAt(next) != 'R' && dominoes.charAt(next) != 'L'; next++) ;
                if (next < n && dominoes.charAt(next) != 'L') next = n;
                if (pre >= 0 && dominoes.charAt(pre) != 'R') pre = -n;
                if (pre >= 0 && next < n && next - i == i - pre) continue;
                if (next < n && pre >= 0) {
                    str[i] = (next - i < i - pre) ? 'L' : 'R';
                } else if(next < n) {
                    str[i] = 'L';
                } else if (pre >= 0) {
                    str[i] = 'R';
                }
            }
        }
        return new String(str);
    }
}
