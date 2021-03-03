package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class LC946ValidateStackSequences extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC946ValidateStackSequences();
        LC.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1});
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> q = new LinkedList<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            while (q.size() > 0 && q.getLast() == popped[j]) {
                q.pollLast();
                j++;
            }
            q.add(pushed[i]);
        }
        while (j < popped.length) {
            if (q.size() == 0) return false;
            else if (popped[j++] != q.pollLast()) return false;
        }
        return true;
    }
}
