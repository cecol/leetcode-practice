package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC833FindAndReplaceInString extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC833FindAndReplaceInString();
        LC.findReplaceString("abcd", new int[]{0, 2}, new String[]{"a", "cd"}, new String[]{"eee", "ffff"});
        LC.findReplaceString("abcd", new int[]{0, 2}, new String[]{"ab", "ec"}, new String[]{"eee", "ffff"});
        LC.findReplaceString("vmokgggqzp", new int[]{3, 5, 1}, new String[]{"kg", "ggq", "mo"}, new String[]{"s", "so", "bfr"});
    }

    /**
     * 很直觀的解出來了, 因為indexes不是遞增, 用priority queue 來先記載要處理的區間, 讓後續一直poll 可以一直處理下去
     * 然後就用 StringBuilder 根據記載好的區間下去處理
     * https://leetcode.com/problems/find-and-replace-in-string/discuss/130587/C%2B%2BJavaPython-Replace-S-from-right-to-left
     * 竟然比這個還要快, 可能因為他是先組好要建立的區間後 再sort
     * 沒想到priority queue的方法快不少
     *
     * 不過後來看到這個 O(n)
     * https://leetcode.com/problems/find-and-replace-in-string/discuss/134758/Java-O(n)-solution
     * 想不到可以快成這樣
     * 直接用 map 建立要用到的 index, 然後S 從頭換, 完全略過 sort的需求, 真的厲害多了, 應該用這種解法
     * */
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> p = new PriorityQueue<>(Comparator.comparingInt(i -> i[0]));
        for (int i = 0; i < indexes.length; i++) {
            if (S.startsWith(sources[i], indexes[i])) {
                p.offer(new int[]{indexes[i], indexes[i] + sources[i].length(), i});
            }
        }
        int h = 0;
        while (!p.isEmpty()) {
            int[] k = p.poll();
            sb.append(S.substring(h, k[0]));
            sb.append(targets[k[2]]);
            h = k[1];
        }
        if (h < S.length()) sb.append(S.substring(h, S.length()));

        return sb.toString();
    }
}
