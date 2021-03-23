package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC767ReorganizeString extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC767ReorganizeString();
//        LC.reorganizeString("aab");
//        LC.reorganizeString("vvvlo");
        LC.reorganizeString("zhmyo");
    }

    /**
     * 這題算過類似的Greedy, 但是最後怎麼組出合適的答案花了我很多時間
     * https://leetcode.com/problems/reorganize-string/discuss/232469/Java-No-Sort-O(N)-0ms-beat-100
     * 1. int[] hash = new int[26]; 來記載每個字元出現次數 -> 我有想到
     * 2. 找出最多次重複的 letter and 次數 , 用數學計算max重複是否真的組的出來 -> 我有想到用
     * 3. 我卡在最後怎麼組出字串卡很久, 最後想用 StringBuilder + insert
     * -> 答案是用 char[S.length] -> 我一開始有想到, 但沒繼續用
     * -> 其實答案用的 char[S.length] 插入法我想用 StringBuilder + insert 很像, 但有edge case 我處理不好
     * -> 果然還是一個細節用錯, 儘管邏輯類似, 但要走到一樣的結果複雜度有差
     * -> char[S.length] -> 先把 max letter 用 0, 2, 4 , 6 放到 char[S.length]
     * -> 後續剩下的就是區間跳著填進去
     * -> 我理解我沒想通的部分了 -> 難怪我字串插入沒做好
     * -> 最常重複的case max 一定不能超過 ceiling( S.length / 2) -> 鴿籠原理
     * 字串長度 6 最長的只能是3, 7是4,  所以說當用最長字串去填char[S.length] 他有可能從 0,2,4,6 一路填到 S.length - 1
     * 但是剩下來的字元總和一定另外一半(s.length/2) , 所以當idx到了S.length - 1, 直接換成 idx = 1, 在填下去填到S.length - 1就結束
     * 填兩輪就好, 0 -> 2 -> 4 -> S.length - 1,  1 -> 3 -> 4 -> S.length - 1
     * 我沒想到這一層, 導致我之前用 StringBuilder insert 沒做好, 如果可以這樣想應該也可以解出來一樣的
     */
    public String reorganizeString(String S) {
        int[] m = new int[26];
        int mx = 0;
        char mxC = 'a';
        for (char c : S.toCharArray()) {
            m[c - 'a']++;
            if (m[c - 'a'] > mx) {
                mx = m[c - 'a'];
                mxC = c;
            }
        }
        if (S.length() - mx < mx - 1) return "";
        char[] res = new char[S.length()];
        int idx = 0;
        while (m[mxC - 'a'] > 0) {
            res[idx] = mxC;
            m[mxC - 'a']--;
            idx += 2;
        }
        for (int i = 0; i < 26; i++) {
            while (m[i] > 0) {
                if (idx >= res.length) idx = 1;
                res[idx] = (char) (i + 'a');
                idx+=2;
                m[i]--;
            }
        }
        return String.valueOf(res);
    }
}
