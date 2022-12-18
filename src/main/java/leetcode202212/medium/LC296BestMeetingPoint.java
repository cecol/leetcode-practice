package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC296BestMeetingPoint extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC296BestMeetingPoint();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Math/296.Best-Meeting-Point
     * 這題其實我一看到連暴力法都沒直覺想出來,
     * 最簡單就是針對每一點 x/y 去算對其他所有點 grid[i/j] 距離 然後找出最小
     * 但最快是用 數學概念
     * 兩點之間 最短距離 是中位數
     * 這邊有個解釋
     * https://leetcode.com/problems/best-meeting-point/solutions/74186/14ms-java-solution/comments/77244
     * As long as you have different numbers of people on your left and on your right,
     * moving a little to the side with more people decreases the sum of distances.
     * So to minimize it, you must have equally many people on your left and on your right.
     * 就是說 你 [左邊] 的人數多於 [右邊]
     * 往左邊靠過去 會減少左邊人數也會減少整體的 distance
     * 最終 [左邊人數跟右邊] 一樣
     * 這樣才會有 min distance
     * 這時候你剛好處在 中位數點
     *
     * 所以就是列出出有 x idx list, y idx list, 都是由大到小
     * sum += idx.get(r--) - idx.get(l++)
     * 因為都要往 [中位數] 走 所以 r 位置 走到中位數距離 跟 l 走到中位數距離 花費
     * 就是 l 到 r 中間距離:  idx.get(r) - idx.get(l)
     *
     * 這題因為是用  Manhattan Distance 所以可以這樣算距離, 不然一般 點對點距離應該是 要平分開根號
     * */
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        List<Integer> I = new ArrayList<>();
        List<Integer> J = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) I.add(i);
            }
        }
        // 保證 j 加入 List<Integer> J 是由小到大, 所以 loop 外層是 j
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) J.add(j);
            }
        }
        return minTotalDistance(I) + minTotalDistance(J);
    }

    int minTotalDistance(List<Integer> idx) {
        int l = 0, r = idx.size() - 1, sum = 0;
        while (l < r) {
            sum += idx.get(r--) - idx.get(l++);
        }
        return sum;
    }
}
