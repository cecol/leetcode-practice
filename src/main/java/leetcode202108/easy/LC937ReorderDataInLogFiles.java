package leetcode202108.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC937ReorderDataInLogFiles extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC937ReorderDataInLogFiles();
        LC.reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"});
    }

    /**
     * 自己有解出來, 但太慢了
     * https://leetcode.com/problems/reorder-data-in-log-files/discuss/193872/Java-Nothing-Fancy-15-lines-2ms-all-clear.
     * 這個真的快很多, 直接做一個Array sort的邏輯直接去sort
     */
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] log1s = log1.split(" ", 2);
            String[] log2s = log2.split(" ", 2);

            boolean isDigit1 = Character.isDigit(log1s[1].charAt(0));
            boolean isDigit2 = Character.isDigit(log2s[1].charAt(0));

            if(!isDigit1 && ! isDigit2) {
                if(log1s[1].equals(log2s[1])) return log1s[0].compareTo(log2s[0]);
                return log1s[1].compareTo(log2s[1]);
            } else if(isDigit1 && isDigit2) return 0;
            else if(isDigit1 && !isDigit2) return 1;
            else return -1;
        });
        return logs;
    }

    /**
     * 邏輯一開始有想出來, 但是實作細節卡了一陣子
     * Runtime: 6 ms, faster than 31.82% of Java online submissions for Reorder Data in Log Files.
     */
    public String[] reorderLogFilesOld(String[] logs) {
        PriorityQueue<String> pq = new PriorityQueue<String>((x, y) -> {
            String xc = x.substring(x.indexOf(' ') + 1, x.length());
            String yc = y.substring(y.indexOf(' ') + 1, y.length());
            if (xc.equals(yc)) return -x.substring(0, x.indexOf(' ')).compareTo(y.substring(0, y.indexOf(' ')));
            return -xc.compareTo(yc);
        });
        LinkedList<String> dl = new LinkedList<>();
        for (String log : logs) {
            boolean isDigitLog = true;
            String[] ele = log.substring(log.indexOf(' ') + 1, log.length()).split(" ");
            for (String e : ele) {
                if (!isDigitLog) break;
                for (char c : e.toCharArray())
                    if (!Character.isDigit(c)) {
                        isDigitLog = false;
                        break;
                    }
            }
            if (isDigitLog) {
                dl.add(log);
            } else {
                pq.offer(log);
            }
        }
        while (pq.size() > 0) {
            dl.addFirst(pq.poll());
        }
        return dl.toArray(new String[dl.size()]);
    }
}
