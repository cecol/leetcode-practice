package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC638ShoppingOffers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC638ShoppingOffers();
    }


    /**
     * https://leetcode.com/problems/shopping-offers/solutions/1562987/java-map-memorized/
     * 其實題目是很直觀的 dfs, 難得是如何有一個有效的 memorization 結構?
     * dfs 基本邏輯
     * 1. 都不用 special, 當前 price X needs 先得到低消
     * 2. 開始每個 special 下去dfs輪替, 看看用了當下 special, 是否有更便宜, 還要先檢查當前 special 能不能用
     * - 傳到下層的 List<Integer> needs 要是一個新的
     * - 每層計算的過程都是用  Map<List<Integer>, Integer> map = new HashMap<>(); 做 memorization
     * - 直接用 List<Integer> needs 當作 key 下去找 之前看過的 result
     *
     * memorization 這邊用 Map 簡單多了, 有人用 bitmap 實在不太熟悉
     * 再者 一開始被 List<Integer> needs 清到 0 卡住
     * 其實就是先拿低消算一個答案, 剩下才去輪替 special, 輪不到 special, 就是一開始的低消就是答案
     * 所以先算低消是保底, 不管有沒有合適 special, 所以每層 dfs 都至少有保底低消
     * */
    Map<List<Integer>, Integer> map = new HashMap<>();

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        dfs(price, special, needs);
        return map.get(needs);
    }

    int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (map.containsKey(needs)) return map.get(needs);
        int res = getAll(price, needs);
        for (List<Integer> sp : special) {
            if (canUse(sp, needs)) {
                res = Math.min(res,
                        sp.get(sp.size() - 1) +
                        dfs(price, special, updateNeeds(sp, needs)));
            }
        }
        map.put(needs, res);
        return res;
    }

    List<Integer> updateNeeds(List<Integer> sp, List<Integer> needs) {
        List<Integer> updated = new ArrayList<>();
        for (int i = 0; i < needs.size(); i++) {
            updated.add(needs.get(i) - sp.get(i));
        }
        return updated;
    }

    boolean canUse(List<Integer> sp, List<Integer> needs) {
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i) < sp.get(i)) return false;
        }
        return true;
    }

    int getAll(List<Integer> price, List<Integer> needs) {
        int sum = 0;
        for (int i = 0; i < price.size(); i++) sum += price.get(i) * needs.get(i);
        return sum;
    }
}
