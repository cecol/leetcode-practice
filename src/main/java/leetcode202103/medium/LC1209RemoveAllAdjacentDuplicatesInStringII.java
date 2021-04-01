package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class LC1209RemoveAllAdjacentDuplicatesInStringII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1209RemoveAllAdjacentDuplicatesInStringII();
        LC.removeDuplicates("abcd", 2);
        LC.removeDuplicates("deeedbbcccbdaa", 3);
        LC.removeDuplicates("pbbcggttciiippooaais", 2);
    }

    /**
     * 自己用兩個 stack來做有過, 一個記載當前的 character, 另一個記載當前的 duplicate counter
     * 兩個一起增減, 雖然會過, 但速度有夠慢
     */
    public String removeDuplicates(String s, int k) {
        Deque<Character> qc = new LinkedList<>();
        Deque<Integer> qn = new LinkedList<>();
        for (char c : s.toCharArray()) {
            int n = 1;
            while (qn.size() > 0 && qn.getLast() == k) {
                int kk = k;
                while (kk > 0) {
                    qn.pollLast();
                    qc.pollLast();
                    kk--;
                }
            }
            if (qc.size() > 0) {
                if (qc.getLast() == c) {
                    n = qn.getLast() + 1;
                }
            }
            qc.add(c);
            qn.add(n);
        }

        while (qn.size() > 0 && qn.getLast() == k) {
            int kk = k;
            while (kk > 0) {
                qn.pollLast();
                qc.pollLast();
                kk--;
            }
        }
        StringBuilder sb = new StringBuilder("");
        while (qc.size() > 0) sb.append(qc.pollFirst());
        return sb.toString();
    }

    /**
     * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/discuss/392933/JavaC%2B%2BPython-Two-Pointers-and-Stack-Solution
     * 因為我們總是要記載目前進入 stack的 duplicate size, 我原本多用個stack 來記載duplicate size根本是想太多
     * 基本上只要一個int[s.length()] 就好, 就可以知道當前 duplicate size
     * 多用個stack 記載還得一起 push pop, 時間變, 2N就差很多了
     */
    public String removeDuplicates2(String s, int k) {
        int[] count = new int[s.length()];
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c);
            int last = sb.length() - 1;
            if (last > 0 && sb.charAt(last) == sb.charAt(last - 1)) count[last] = count[last - 1] + 1;
            else count[last] = 1;
            if (count[last] >= k) sb.delete(sb.length() - k, sb.length());
        }
        return sb.toString();
    }
}
