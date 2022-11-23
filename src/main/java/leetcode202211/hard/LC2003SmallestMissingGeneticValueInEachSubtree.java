package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC2003SmallestMissingGeneticValueInEachSubtree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2003SmallestMissingGeneticValueInEachSubtree();
    }

    /**
     * https://leetcode.com/problems/smallest-missing-genetic-value-in-each-subtree/discuss/1641677/Java-Solution/1217390
     * 這題幾個關鍵點
     * 1. 如果整棵樹沒有 nums[i] == 1, 那全部答案都是 1
     * 2. 如果有 nums[i] == 1, 那就是找出那個 subtree root genetic == 1, 從他開始
     * 3. 維持一個 TreeSet<Integer> missingValues 裏面放 1 - n+1, 透過 missingValues.first() 就會知道到底當下缺哪個 min num
     * 4. 所以只有 nums[subtree root] 到 root 過程中 Smallest Missing Genetic 都要看當前少了什麼
     * 不然其他 sub tree Smallest Missing Genetic 都是 1
     * <p>
     * 所以步驟是
     * 1. 先建立最後 result
     * - int[] res = new int[n];
     * - Arrays.fill(res, 1);
     * 預設先都給 1, 後續再依據 missingValues.first 更新
     * 2. 建立 parent to sons map,
     * 3. 找出哪個 i 是 nums[i] == 1, 然後把他的子孫都從 parent to sons map 拿掉,
     * 因為他們都注定 Smallest Missing Genetic 都是 1 (前面一開始填過)
     * 4. 開始回頭看 parent node, 每次過程  直到找到 root
     * - missingValues.remove(nums[parent]);
     * - removeDescendents if has sons
     * - res[parent] = missingValues.first();
     *
     */
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);

        Map<Integer, List<Integer>> sonsMap = new HashMap<>();
        for (int i = 1; i < n; i++) {
            int parent = parents[i];
            if (!sonsMap.containsKey(parent)) sonsMap.put(parent, new ArrayList<>());
            sonsMap.get(parent).add(i);
        }

        int ind = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                ind = i;
                break;
            }
        }
        if (ind == -1) return res;

        TreeSet<Integer> missingValues = new TreeSet<>();
        for (int i = 1; i <= n + 1; i++) missingValues.add(i);
        removeDescendents(missingValues, sonsMap, nums, ind);
        res[ind] = missingValues.first();

        int parent = parents[ind];
        while (parent != -1) {
            missingValues.remove(nums[parent]);
            for (Integer son : sonsMap.get(parent)) {
                if (son == ind) continue;
                removeDescendents(missingValues, sonsMap, nums, son);
            }
            res[parent] = missingValues.first();
            ind = parent;
            parent = parents[parent];
        }
        return res;
    }

    void removeDescendents(TreeSet<Integer> missingValues, Map<Integer, List<Integer>> sonsMap, int[] nums, int node) {
        missingValues.remove(nums[node]);
        if (sonsMap.containsKey(node)) {
            for (Integer son : sonsMap.get(node)) removeDescendents(missingValues, sonsMap, nums, son);
        }
    }
}
