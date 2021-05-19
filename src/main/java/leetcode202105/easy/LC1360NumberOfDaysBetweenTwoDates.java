package leetcode202105.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1360NumberOfDaysBetweenTwoDates extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1360NumberOfDaysBetweenTwoDates();
    }

    /**
     * 這題看到的時候被閏年計算弄的很複雜, 第一次遇到要處理DateTime的問題, 要自己計算真的很容易出錯
     * 所以這題基本思路就是
     * 1. 計算給予的時間的 days since 1900
     * -> 所以要計算閏年 + 月份的日子
     * 2. 然後相減
     * https://leetcode.com/problems/number-of-days-between-two-dates/discuss/517575/Java-no-time-api-cheating
     * 優化細節
     * 1. 月份的日子 array 用累加的算上去
     * 2. 算年份就是 yDelta * 365 + yDelta / 4;
     * -> int yDelta = year - 1 - 1900; 因為當年度還沒結束, 所以 -1
     * -> yDelta / 4; 4年出現一次閏年
     * 3. int dm = mdays[month - 1]; 當月份還沒走完
     * 4. if (isLeap(year) && month - 1 >= 2) dm++; 如果當年度是閏年且月份超過2 -> 多補一天
     */
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(days(date1) - days(date2));
    }

    int[] mdays = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

    private int days(String d) {
        String[] ss = d.split("-");
        int year = Integer.parseInt(ss[0]);
        int month = Integer.parseInt(ss[1]);
        int day = Integer.parseInt(ss[2]);
        int yDelta = year - 1 - 1900;
        int dy = yDelta * 365 + yDelta / 4;
        int dm = mdays[month - 1];
        if (isLeap(year) && month - 1 >= 2) dm++;
        return dy + dm + day;
    }

    private boolean isLeap(int y) {
        return (y % 100 != 0 && y % 4 == 0) || (y % 100 == 0 && y % 400 == 0);
    }
}
