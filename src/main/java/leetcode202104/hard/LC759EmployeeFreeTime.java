package leetcode202104.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LC759EmployeeFreeTime extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC759EmployeeFreeTime();
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

    /**
     * https://leetcode.com/problems/employee-free-time/discuss/113134/Simple-Java-Sort-Solution-Using-(Priority-Queue)-or-Just-ArrayList
     * 這題其實蠻直觀的,
     * 1. 先把所有 Intervals 攤平放在起sort by start
     * 2. 拿出第一個後開始類似 interval merge的概念一直找下去, 如果找到 下一個start > 當前merge interval 的 end, 這個區間就是空閑時間
     * 我覺得一開始沒看出來大概是因為腦袋太混亂且沒有先想通攤平來看就好
     * 攤平來看就是很直觀的 interval merge
     */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> ss = new ArrayList<>();
        List<Interval> res = new ArrayList<>();
        for (List<Interval> s : schedule) ss.addAll(s);
        Collections.sort(ss, (x, y) -> x.start - y.start);
        Interval cur = ss.get((0));
        for (Interval nn : ss) {
            if (nn.start > cur.end) {
                res.add(new Interval(cur.end, nn.start));
                cur = nn;
            } else {
                cur.end = Math.max(nn.end,cur.end);
            }
        }
        return res;
    }
}
