package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC166FractionToRecurringDecimal extends BasicTemplate {

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC166FractionToRecurringDecimal();
//        LC.fractionToDecimal(2, 3);
        LC.fractionToDecimal(4, 333);
    }

    /**
     * 好像大概知道邏輯是怎樣, 但話太多時間在處理商數餘數, 乾脆直接參考他人clean solution
     * https://leetcode.com/problems/fraction-to-recurring-decimal/discuss/51106/My-clean-Java-solution
     * 要注意的地方:
     * 1. 負數處理
     * -> 先判定結果是否為正數or負數, 在string 先補上 - 負號
     * -> 把 numerator & denominator 轉 Math.abs -> 然後還要轉 long
     * -> 因為如果給的是Integer.MIN_VALUE -> Math.abs 會有問題
     * 2. Map是 key = 餘數, value = 第一次遇到該餘數的 string length, 之後再遇到用這index 來 insert (, )
     * 3. 步驟
     * -> 第一次邏輯:
     * sb.append(num / den);
     * num %= den;
     * while(num != 0)
     * -> num *= 10;
     * -> sb.append(num / den);
     * -> num %= den;
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder sb = new StringBuilder();
        sb.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        sb.append(num / den);
        num %= den;
        if (num == 0) return sb.toString();

        sb.append(".");
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(num, sb.length());
        while (num != 0) {
            num *= 10;
            sb.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                sb.insert(index, "(");
                sb.append(")");
                break;
            } else {
                map.put(num, sb.length());
            }
        }
        return sb.toString();
    }
}
