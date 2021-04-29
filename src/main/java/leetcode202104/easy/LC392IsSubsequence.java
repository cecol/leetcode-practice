package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC392IsSubsequence extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC392IsSubsequence();
    }

    /**
     * tag是用 binary search, dp, greedy
     * 但我直接用index來解就夠快了 faster than 77.32%
     * binary search的解法反而比較慢, 可能建立適合 binary search 環境的 overhead太貴了,
     * 除非有多個 String s 要比對, 這樣拿 t 來建立 binary search 環境的才可以被攤銷
     * */
    public boolean isSubsequence(String s, String t) {
        if(s.length() > t.length()) return false;
        int idxS = 0;
        int idxT = 0;
        while(idxS < s.length() && idxT < t.length()) {
            if(t.charAt(idxT) == s.charAt(idxS)) {
                idxT++;
                idxS++;
            } else idxT++;
        }
        return idxS == s.length();
    }
}
