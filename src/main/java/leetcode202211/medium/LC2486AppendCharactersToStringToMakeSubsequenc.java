package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC2486AppendCharactersToStringToMakeSubsequenc extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/append-characters-to-string-to-make-subsequence/discuss/2852101/JavaC%2B%2BPython-Two-Pointers
     * 是 two pointer
     * s idxS 從頭走到尾
     * 紀錄 idxT 的位置, 如果有碰到 s.charAt(idxS) == t.charAt(idxT) 就可以 idxT++
     * 最後看 t.length() - idxT
     *
     * contest 我的想法沒錯 完全是寫錯某個條件式
     * while (it < t.length() && s.charAt(is) == t.charAt(it)) it++;
     * 應該是 if (it < t.length() && s.charAt(is) == t.charAt(it)) it++;
     * (為什麼當下會一直覺得 while 是對的? 直到看到別人用 if 才醒悟?)
     *
     * 用成 while idxT 會多跳格數
     *
     * 這週 contest 這題鬼打牆導致分數降低  讓我感到很難過
     * 這也代表 得做更好準備
     * 不然一個小小的盲點 精神狀況不好 當下邏輯卡死 就會讓面試失敗
     * 就算這題你本來應該是練習很多次的
     *
     * 而且這題也正是因為遇到 subsequence 題目
     * 這類題目很容易讓我感到焦慮, 但來得多做練習
     *
     * 不過這也證實得多打競賽, 因為競賽只是在意分數就會失常, 那麼面試的時候不就更容易因為緊張而失常嗎
     * 得失心真的好重 到底要如何才能做到心流呢
     * */
    public int appendCharacters(String s, String t) {
        int is = 0, it = 0;
        for (; is < s.length(); is++) {
            if (it < t.length() && s.charAt(is) == t.charAt(it)) it++;
        }
        return t.length() - it;
    }
}
