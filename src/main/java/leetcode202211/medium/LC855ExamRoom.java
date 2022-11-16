package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class LC855ExamRoom extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    public void ExamRoom(int n) {
        this.cap = n;
    }

    int cap;
    TreeSet<Integer> seats = new TreeSet<>();

    /**
     * https://leetcode.com/problems/exam-room/discuss/1331530/Easy-to-Understand-Java-Solution-with-Explanation-TreeSet
     * 這題可以很直觀的用一個 TreeSet 來紀錄當前坐的位置,
     * leave 時候可以直接刪除 O(1)
     *
     * seat 時候就是 走過 TreeSet 來找 max dist 來放入 O(N)
     * 有幾個細節要注意
     * 1. 算 dist 他的初始值是 seats.first()
     * 因為 seat 0 可能後面被拔除, 這樣用 prev, cur 來找 max diff 會算不到 0 的位置
     * Ex: seats [3,5] -> 其實可以塞 0
     *
     * 2. 結尾的 edge case
     * 同樣 seat n-1 可能後面被拔除, 這樣用 prev, cur 來找 max diff 會算不到 n-1 的位置
     * 所以還要在計算 distance < capacity-1-seats.last()
     * 來看看是不是 n-1 是 max dist
     * */
    public int seat() {
        int seatNum = 0;
        if (seats.size() > 0) {
            Integer prev = null;
            int dist = seats.first();
            for (Integer seat : seats) {
                if(prev != null) {
                    int d = (seat-prev)/2;
                    if(d > dist) {
                        dist = d;
                        seatNum = prev + dist;
                    }
                }
                prev = seat;
            }
            if(dist < cap-1-seats.last()) {
                seatNum = cap - 1;
            }
        }
        seats.add(seatNum);
        return seatNum;
    }

    public void leave(int p) {
        seats.remove(p);
    }
}
