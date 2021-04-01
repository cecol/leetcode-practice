package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC904FruitIntoBaskets extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC904FruitIntoBaskets();
        //var s = LC.longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5});
        LC.totalFruit(new int[]{1, 2, 1});
        LC.totalFruit(new int[]{0, 1, 2, 2});
        LC.totalFruit(new int[]{1, 2, 3, 2, 2});
        LC.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4});
    }

    /**
     * https://leetcode.com/problems/fruit-into-baskets/discuss/170745/Problem%3A-Longest-Subarray-With-2-Elements
     * 這題的基本含義是 What is the length of longest subarray that contains up to two distinct integers?
     * 其實可以用hashmap解
     * 抽象題意是 Sliding Window for K Elements
     * https://leetcode.com/problems/fruit-into-baskets/discuss/170740/JavaC%2B%2BPython-Sliding-Window-for-K-Elements
     * sliding window 的題組, 但是這樣的space 會是 O(n)
     *
     * 這題因為k=2, 所以用two pointer就可以解
     * 核心題意是 最長subarray 其中只有2種 fruit
     * pointer 1 就是把tree中每一個f 都走過 然後過程中記載已走過的subarray算出來的max size
     * 關鍵在於 lastFCount的reset
     * 假設設目前的 f1 = 1 前一個看到的fruit type, f2 = 2最後看得到fruit type,
     * 先基本切問題
     * 1. 當前的fruit f -> f == f1 || f == f2
     * -> cur 一定繼續加加
     * -> if f == f2 -> 最後一個的 lastFCount 繼續用, 因為f2就是記載最後的fruit
     * -> else -> 最後一個的 lastFCount 要切回f1, 基本概念就是 swap(f1, f2), 現在的f1變成f2, lastFCount reset = 1
     * 2. 當前的fruit f -> f != f1 && f != f2
     * -> 代表 現在的f2要變成下一次計數的f1, 現在看到第三個fruit type變成f2
     * -> 那麼 cur 就是 拿lastFCount來計算(因為出現第三種 fruit, 所以當前計算的就是 原本的f2的lastFCount + 出現的第三種 fruit)
     * -> 所以 cur = lastFCount + 1;
     * -> lastFCount 也要 reset = 1 因為變成當前出現新的fruit 是最後一個fruit, 才剛出現第一次
     * 所以看得出來 要記載當前看到的 f1,f2 然後第二個 pointer就是 lastFCount
     * -> 因為每當看到新 f3 or f1 又變成當前fruit, 就代表下一次的 subarray max fruit 計算又要開始
     */
    public int totalFruit(int[] tree) {
        int res = 0, cur = 0, lastFCount = 0, f1 = -1, f2 = -1;
        for (int f : tree) {
            if (f == f1 || f == f2) {
                cur++;
                if (f == f2) lastFCount++;
                else {
                    lastFCount = 1;
                    f1 = f2;
                    f2 = f;
                }
            } else {
                cur = lastFCount + 1;
                lastFCount = 1;
                f1 = f2;
                f2 = f;
            }

            res = Math.max(res, cur);
        }
        return res;
    }
}
