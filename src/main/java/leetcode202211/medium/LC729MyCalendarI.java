package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.TreeMap;

public class LC729MyCalendarI extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    public void MyCalendar() {

    }

    TreeMap<Integer, Integer> tm = new TreeMap<>();
    /**
     * https://leetcode.com/problems/my-calendar-i/submissions/
     * 很基本的 TreeMap 練習
     * key = start, value = end
     * 要用 lowerKey
     * Returns the entry for the greatest key less than the specified key
     *
     * lowerKey 就是找 在當前 book 時間有沒有更早的 book, 有的話可能有 overlap
     * 如果 lowerKey == null , 代表前面根本沒 book or
     * low.value(end) <= start, 更早的 book 早結束
     *
     * 以外都是 overlap
     * */
    public boolean book(int start, int end) {
        Integer low = tm.lowerKey(end);
        if (low == null || tm.get(low) <= start) {
            tm.put(start, end);
            return true;
        }
        return false;
    }
}
