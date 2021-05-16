package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1237FindPositiveIntegerSolutionForAGivenEquation extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1237FindPositiveIntegerSolutionForAGivenEquation();
    }

    interface CustomFunction {
        public int f(int x, int y);
    }

    /**
     * 沒有時間好好想過這題, 看到題目就有點看不懂傻住, 應該要練習的就是好好的理解題目, 尤其是在高壓情況下
     * 而且每一題應該要配足夠時間去理解它, 而非過度把時間投入在以為解的出來的那題
     * 對於一開始看不太懂的題目就先放棄, 或許看起來複雜題目多花點時間理解背後要問的就很快解決了
     * <p>
     * 這題後來看到原題有提到 binary search 後來就很直觀的解了
     * 1. 既然有限定 x, y pair 的範圍是 [1 - 1000], 最差就是 x,y pair 的各種組合
     * 2. 配上 binary search 會更快
     * <p>
     * 但其實 binary search 是不必要的
     * x,y pair 是獨一無二的, 所以 x = 1, y = 1000 左右逼近就好
     */
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        int x = 1, y = 1000;
        while (x <= 1000 && y > 0) {
            int k = customfunction.f(x, y);
            if (k < z) x++;
            else if (k > z) y--;
            else res.add(List.of(x++, y--));
        }
        return res;
    }

    public List<List<Integer>> findSolutionSlow(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i < 1000; i++) {
            List<Integer> rr = new ArrayList<>();
            int l = 1, r = 1000;
            while (l < r) {
                int m = (l + r) / 2;
                int k = customfunction.f(i, m);
                if (k == z) {
                    rr.add(i);
                    rr.add(m);
                    break;
                }
                if (k < z) l = m + 1;
                else r = m;
            }
            if (rr.size() > 0) res.add(rr);
        }
        return res;
    }
}
