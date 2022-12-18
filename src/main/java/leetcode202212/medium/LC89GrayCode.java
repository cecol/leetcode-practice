package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC89GrayCode extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC89GrayCode();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Math/089.Gray-Code
     * 這題有個很有趣的組合
     * n = 3 可以從 n = 2 組出來
     * n = 2 有 00,01,11,10
     * n = 2 配上自己的 反轉
     * [00,01,11,10] [10,11,01,00]
     * 前半組前面加上 0 -> [000,001,011,010]
     * 後半組前面加上 1 ->             [110,111,101,100]
     * 就是答案
     */
    public List<Integer> grayCode(int n) {
        if (n == 1) return List.of(0, 1);
        List<Integer> res = new ArrayList<>();
        List<Integer> pre = grayCode(n - 1);
        res.addAll(pre);
        for (int i = pre.size() - 1; i >= 0; i--) {
            res.add(pre.get(i) | (1 << n - 1));
        }
        return res;
    }
}
