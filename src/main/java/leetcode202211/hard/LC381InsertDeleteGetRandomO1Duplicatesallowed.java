package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Int;

import java.util.*;

public class LC381InsertDeleteGetRandomO1Duplicatesallowed extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC381InsertDeleteGetRandomO1Duplicatesallowed();
    }

    public void RandomizedCollection() {

    }

    /**
     * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/solution/
     * 明明是 LC380InsertDeleteGetRandomO_1 延伸題  完全沒印象
     * 1. 看到 getRandom 完全傻住, 以為有什麼特別演算法, 其實只是 Random.nextInt() 來取隨機 idx
     * 2. 關鍵還是 一個 list 記載所有元素, 一個 map 記載每個元素在 list 位置
     * -     為了實現 O(1) 操作 remove, insert, 關鍵在於 list 刪除要 O(1), 就是移到 list 尾巴再刪除
     * -     因為是 duplicate, 所以 map 記載 val offset 是一個 LinkedHashSet<Integer>
     * -     記載每個 duplicate 位置, 因為是 offset, 一定都是 unique, 所以下次要刪除時候可以很快 remove(removeIdx)
     * -     所以要拿刪除就是從 LinkedHashSet.iterator().next();
     * -     刪除時候直接拿 list.lastVal 覆寫到要刪除的 removeIdx, 不必 swap, 直接把藥刪除的值蓋掉
     * -     更新 list.lastVal 在 idxMap 的位置
     * -     一樣刪除 lastOffset: remove(list.size() - 1) , 因為之前位置剛好就是 list.size() - 1
     * -     加上新位置 add(removeIdx))
     *
     * */
    Map<Integer, LinkedHashSet<Integer>> idx = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    java.util.Random rand = new java.util.Random();
    public boolean insert(int val) {
        if (!idx.containsKey(val)) idx.put(val, new LinkedHashSet<>());
        idx.get(val).add(list.size());
        list.add(val);
        return idx.get(val).size() == 1;
    }

    public boolean remove(int val) {
        if (!idx.containsKey(val) || idx.get(val).size() == 0) return false;
        int removeIdx = idx.get(val).iterator().next();
        idx.get(val).remove(removeIdx);
        Integer lastVal = list.get(list.size() - 1);
        list.set(removeIdx, lastVal);
        idx.get(lastVal).add(removeIdx);
        idx.get(lastVal).remove(list.size() - 1);

        list.remove(list.size() - 1);
        return true;
    }

    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
