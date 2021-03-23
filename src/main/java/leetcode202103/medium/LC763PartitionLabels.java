package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.CallbackHandler;
import java.util.*;

public class LC763PartitionLabels extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC763PartitionLabels();
        LC.partitionLabels("caedbdedda");
    }

    /**
     * https://leetcode.com/problems/partition-labels/discuss/113259/Java-2-pass-O(n)-time-O(1)-space-extending-end-pointer-solution
     * two pointer這種解法我是真的沒想到, 也覺得想不太到...
     * 1. 先記載每個 char last index in S string
     * 2. 然後loop i -> S.length - 1;
     * -> 看看當前S.charAt(i) 記載中的 last 是哪
     * -> last = Math.max(last, m[S.charAt(i) - 'a']); -> 這邊會把 last 卡死在目前 start -> i 字串中的最遠char的 last
     * -> ex: ababcbacadefegdehijhklij, 會切出 ababcbaca, 過程是:
     * -> i = 0, S.char(i) = a , a的last == 8, 後面i走到的 b,c last 都比a小, 代表b,c都被包在a的partition 中
     * -> last = Math.max(last, m[S.charAt(i) - 'a']); 就是透過這段把當前還在找的字串 last 卡死在a結尾
     * -> 如果當前 i == last, 就是 start -> i 已經走到真正 partition last 就是最後一個a (b,c因為last比a小經過時候都被確認過了)
     * -> 就可以計算第一段parttion, 然後start往前移 往後繼續找 下一個partition last
     */
    public List<Integer> partitionLabelsTwoPointer(String S) {
        List<Integer> res = new ArrayList<>();
        if (S == null || S.length() == 0) return res;
        int[] m = new int[26];
        for (int i = 0; i < S.length(); i++) m[S.charAt(i) - 'a'] = i;
        int last = 0;
        int start = 0;
        for (int i = 0; i < S.length(); i++) {
            last = Math.max(last, m[S.charAt(i) - 'a']);
            if (last == i) {
                res.add(last - start - 1);
                start = last + 1;
            }
        }
        return res;
    }

    /**
     * 我看起來像是 interval overlap 來決定能怎麼 partition
     * 我先算每個character 的interval, 然後把 overlap都merge起來 -> 剩下獨立的 interval都是partition
     * faster than 30.81% of Java , less than 40.58%
     * 看來真的不是標準解法 畢竟是 O(nlogn)
     */
    public List<Integer> partitionLabels(String S) {
        Map<Character, int[]> m = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (m.containsKey(c)) m.get(c)[1] = i;
            else m.put(c, new int[]{i, i});
        }
        int[][] intervals = m.values().toArray(new int[m.size()][2]);
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        int[] ii = intervals[0];
        List<int[]> iir = new ArrayList<>();
        iir.add(ii);
        for (int[] i : intervals) {
            if (ii[1] >= i[0]) {
                ii[1] = Math.max(ii[1], i[1]);
            } else {
                ii = i;
                iir.add(ii);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int[] i : iir) res.add(i[1] - i[0] + 1);
        return res;
    }
}
