package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.*;

public class LC380InsertDeleteGetRandomO_1 extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC380InsertDeleteGetRandomO_1();
    }


    /**
     * https://leetcode.com/problems/insert-delete-getrandom-o1/discuss/85425/Java-Solution-(Beats-99.20)-Using-HashMap-and-ArrayList-with-Explanation
     * 沒什麼頭緒的一題
     * 基本解法
     * 1. List記載所有加入的elements
     * 2. Map記載每個elements 在 List的位置
     * -> insert 就是直觀的檢查Map, 沒有的話加入 List, Map
     * -> remove 就是直觀的檢查Map, 有的話先依據Map找到的位置把它移到List最後, 然後刪除最後O(1)
     * -> getRandom 用 Math.random 來取一個 List position
     * 這個解法蠻有意思
     * https://leetcode.com/problems/insert-delete-getrandom-o1/discuss/85401/Java-solution-using-a-HashMap-and-an-ArrayList-along-with-a-follow-up.-(131-ms)
     * 這邊有提到, phone interview 是加考如果可以 duplicated element
     * 1. duplicated element -> 要拿到的機率會比較高
     * 2. 還是 List, Map 去紀錄, 只是 Map變成 Map<Integer of key, Set(all duplicated elements position)>
     * 3. 所以 remove 就是 Map.get().iterator.next() 去刪除
     */
    Map<Integer, Integer> m = new HashMap<>();
    List<Integer> res = new ArrayList<>();

    public void RandomizedSet() {

    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (m.containsKey(val)) return false;
        res.add(val);
        m.put(val, res.size() - 1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!m.containsKey(val)) return false;
        int p = m.get(val);
        Collections.swap(res, p, res.size() - 1);
        m.put(res.get(p), p);
        res.remove(res.size() - 1);
        m.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return res.get((int) (Math.random() * res.size()));
    }
}
