package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC470ImplementRand10UsingRand7 extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC470ImplementRand10UsingRand7();
    }

    int rand7() {
        return 1;
    }

    /**
     * 這題是難在 要理解 rejection sampling
     * https://stats.stackexchange.com/questions/29878/what-is-the-probability-of-rejection-in-rejection-sampling
     * rand7 產生 1,2...7, 每個 機率 都是 1/7
     * 但如果我們只取 1,2,..5, 投擲到 6,7 都捨棄 直到得到 [1,2,..5] 這時候 取得 [1,2..5] 機率 是 1/5,  無視你投擲幾次
     * 我沒有很懂背後數學原因, 不過在數學證明上就是如此
     * <p>
     * 所以 rand7 只取 [1,2..5] 1/5 機會
     * 然後再 rand7 只取 [1,3,5] or [2,4,6] 1/2 機會
     * 取 odd/even,
     * odd -> 剛剛 1/5 機會的值 直接回傳,
     * even -> 剛剛 1/5 機會的值 += 5, (注意這邊不是 x2, x2 機率是非平均的)
     * 就會得到 [1..10]
     * 為什麼 捨棄 7? 因為 odd/even 要機率各半, rand7 出 odd 會比 even 多一個機率 所以 rejection sampling 捨去 7
     * 才會 1/2 機率取得 odd or even
     * <p>
     * 兩者相乘 就是 1/10 機率取得 [1,..,10]
     * https://github.com/wisdompeak/LeetCode/tree/master/Math/470.Implement-Rand10--Using-Rand7
     * java 解法
     * https://leetcode.com/problems/implement-rand10-using-rand7/solutions/816420/combine-rand5-and-rand6-java/
     */
    public int rand10() {
        int first = rand7();
        while (first > 5) first = rand7();
        int second = rand7();
        while (second > 6) second = rand7();
        if (second % 2 == 0) first += 5;
        return first;
    }
}
