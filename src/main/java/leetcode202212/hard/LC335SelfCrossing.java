package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC335SelfCrossing extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC335SelfCrossing();
    }

    /**
     * https://leetcode.com/problems/self-crossing/solutions/79131/java-oms-with-explanation/
     * 不覺得下次遇到還可以歸納出這些 case..
     * case 1 - 第 4th 跨過 1st, 也應用於 5th cross 2nd, 6th cross 3nd
     * - distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]
     * case 2 - 5th meet 1st, 也應用於 6th meet 2nd..
     * case 3 - 6th cross 1st 也應用於 7th cross 2nd
     *
     * -              i-2
     * - case 1 : i-1┌─┐
     * -             └─┼─>i
     * -              i-3
     * <p>
     * -                 i-2
     * -  case 2 : i-1 ┌────┐
     * -               └─══>┘i-3
     * -               i  i-4      (i overlapped i-4)
     * <p>
     * - case 3 :    i-4
     * -            ┌──┐
     * -            │i<┼─┐
     * -         i-3│ i-5│i-1
     * -            └────┘
     * -              i-2
     */
    public boolean isSelfCrossing(int[] distance) {
        int l = distance.length;
        if (l <= 3) return false;
        for (int i = 3; i < l; i++) {
            if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) return true;
            if (i >= 4) {
                if (distance[i - 1] == distance[i - 3] &&
                        distance[i] + distance[i - 4] >= distance[i - 2]) return true;
            }
            if (i >= 5) {
                if (distance[i - 2] >= distance[i - 4]  &&
                        distance[i] >= distance[i - 2] - distance[i - 4] &&
                        distance[i - 1] >= distance[i - 3] - distance[i - 5] &&
                        distance[i - 1] <= distance[i - 3]) return true;
            }
        }
        return false;
    }
}
