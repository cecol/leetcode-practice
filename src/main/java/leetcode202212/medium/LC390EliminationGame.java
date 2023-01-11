package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC390EliminationGame extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC390EliminationGame();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Recursion/390.Elimination-Game
     * 假設 lastRemaining 稱之為 f(n)
     * [1,2...,9] 的結果為 f(9)
     * 第一輪進入第二輪 [2,4,6,8], 結果稱之為  f(4)
     * 其實 f(4) 應該是 [1,2,3,4], 但我們 這邊是 [2,4,6,8], -> 兩邊差兩倍
     * 所以 f(4) 結果要 x2, 而且這時候的 f(4) 是從右到左  是反向
     * [1,2,3,4] 的左到右 是 [2,4]
     * [1,2,3,4] 的右到左 是 [1,3]
     *
     * 所以要得到 [1,3] 就是 9/2+1 - [2,4] => [3,1]
     * (1/4 成一對, 2/3成一對)
     *
     * 所以就推導出公式 (n/2+1 - f(n/2)) * 2
     * */
    public int lastRemaining(int n) {
        if (n == 1) return 1;
        return (n / 2 + 1 - lastRemaining(n / 2)) * 2;
    }
}
