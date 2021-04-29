package leetcode202104.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1274NumberOfShipsInARectangle extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1274NumberOfShipsInARectangle();
    }

    class Sea {
        public boolean hasShips(int[] topRight, int[] bottomLeft) {
            return true;
        }
    }

    /**
     * 完全沒有想法的一題
     * 看了解答才發覺我根本想太多, 沒有好好理解這一題
     * https://leetcode.com/problems/number-of-ships-in-a-rectangle/discuss/440768/Java-Simple-divide-and-conquer-solution-with-explanation
     * 1. 先檢查此區間有無 ship -> 沒有直接回0 -> 這是關鍵,
     * -> 因為後續會切 4個象限, 所以0就是直接省去該象限
     * 2. 當切到只有一點 -> topRight[0]==bottomLeft[0]&&topRight[1]==bottomLeft[1] 直接回 1
     * -> 其他的會因為 hasShips == false 排除了
     */
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if (!sea.hasShips(topRight, bottomLeft)) return 0;
        if (bottomLeft[0] > topRight[0] || bottomLeft[1] > topRight[1]) return 0;
        if (topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]) return 1;
        int mx = (topRight[0] + bottomLeft[0]) / 2;
        int my = (topRight[1] + bottomLeft[1]) / 2;
        return countShips(sea, new int[]{mx, my}, bottomLeft) +
                countShips(sea, topRight, new int[]{mx + 1, my + 1}) +
                countShips(sea, new int[]{mx, topRight[1]}, new int[]{bottomLeft[0], my + 1}) +
                countShips(sea, new int[]{topRight[0], my}, new int[]{mx + 1, bottomLeft[1]});
    }
}
