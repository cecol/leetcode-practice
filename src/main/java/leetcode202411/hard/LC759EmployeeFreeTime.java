package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC759EmployeeFreeTime extends BasicTemplate {
    public static void main(String[] args) {
    }

    class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }


    // 沒過
    // 攤平看就好, 不管有多少員工, 攤平的 空白時段, 就是大家的空白
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> ss = new ArrayList<>();
        List<Interval> res = new ArrayList<>();
        for (List<Interval> s : schedule) ss.addAll(s);
        Collections.sort(ss, (x, y) -> {
            return x.start - y.start;
        });
        Interval cur = ss.get(0);
        for (Interval nn : ss) {
            if (nn.start > cur.end) {
                res.add(new Interval(cur.end, nn.start));
                cur = nn;
            } else {
                cur.end = Math.max(cur.end, nn.end);
            }
        }
        return res;
    }
}
