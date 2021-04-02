package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC412FizzBuzz extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC412FizzBuzz();
    }

    /**
     * 不知道在考什麼, 不過 easy 可能有時候沒什麼邏輯
     * https://leetcode.com/problems/fizz-buzz/discuss/89931/Java-4ms-solution-Not-using-%22%22-operation
     * 這題是有用 fizz count, buzz count來避免 % (也是根據 到3到5 reset)
     * 是說有比較快？ CPU處理 ++ 比較快？
     * */
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) res.add("FizzBuzz");
            else if (i % 3 == 0) res.add("Fizz");
            else if (i % 5 == 0) res.add("Buzz");
            else res.add(Integer.toString(i));
        }
        return res;
    }
}
