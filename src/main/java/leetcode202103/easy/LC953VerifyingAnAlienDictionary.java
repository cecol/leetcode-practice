package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC953VerifyingAnAlienDictionary extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC953VerifyingAnAlienDictionary();
        LC.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz");
    }


    /**
     * 這題是解了, 但有點卡, 原來是我不太懂 String order compare
     * 事實上 String a,b 比較是
     * 只要a 從頭開始只要有任一字元小於b 之相同位置 就是true
     * 反之在還沒遇到 true之前遇到 大於就是false
     * 如果字元都依樣 但a比較長就是 false, 反之 true(這是 a,b其中為其他之substring)
     * */
    Map<Character, Integer> m = new HashMap<>();
    public boolean isAlienSorted(String[] words, String order) {
        int p = 1;
        for (char o : order.toCharArray()) m.put(o, p++);
        for (int i = 0; i < words.length - 1; i++) {
            if (!lessThan(words[i], words[i + 1])) return false;
        }
        return true;
    }

    private boolean lessThan(String a, String b) {
        for (int i = 0; i < a.length() && i < b.length(); i++) {
            if(a.charAt(i) == b.charAt(i)) continue;
            if (m.get(a.charAt(i)) > m.get(b.charAt(i))) return false;
            else return true;
        }
        return a.length() <= b.length();
    }
}
