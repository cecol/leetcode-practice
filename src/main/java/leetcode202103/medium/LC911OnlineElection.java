package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TreeMap;

public class LC911OnlineElection extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC911OnlineElection();
    }

    /**
     * 整個題目一開市就不是看得很懂了, 直到看到解釋
     * https://leetcode.com/problems/online-election/discuss/180972/Anyone-else-just-find-this-question-really-confusing
     * 後來參考人家的做法, TreeMap就很漂亮
     * 用TreeMap key是 vote time, value 是當時的 winner
     * 一開始constructor建立好 TreeMap之後
     * 就透過 TreeMap的floorEntry with time, 可以得到在該時間點最近的winner
     */
    TreeMap<Integer, Integer> tt = new TreeMap<>();
    public void TopVotedCandidate(int[] persons, int[] times) {
        int[] c = new int[persons.length];
        for (int i = 0, max = -1; i < persons.length; i++) {
            c[persons[i]]++;
            if (max <= c[persons[i]]) {
                max = c[persons[i]];
                tt.put(times[i], persons[i]);
            }
        }
    }

    public int q(int t) {
        return tt.floorEntry(t).getValue();
    }
}
