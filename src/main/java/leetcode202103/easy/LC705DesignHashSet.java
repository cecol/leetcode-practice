package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LC705DesignHashSet extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC705DesignHashSet();
    }

    /**
     * 特別解這一題是因為想說好好了解一下 HashSet實作, 看一下其他人的答案
     * 有用bits -> 來記錄該元素有無set過, 雖然真的很省空間應各很快, 但實作實在...
     * bits - https://leetcode.com/problems/design-hashset/discuss/548792/Java-solution-faster-than-99
     * 有用boolean[] set -> 無腦, 可以超快, 因為題目的test case is limit -> 但就是創造 sparse array
     * 1D boolean - https://leetcode.com/problems/design-hashset/discuss/273257/Java-solution-(99.7)
     * 1D boolean - https://leetcode.com/problems/design-hashset/discuss/769083/JAVA-or-97.7-fast-or-Using-boolean-Array
     * 也有boolean[][] set -> 創造 sparse array, 但因為拆成2D -> 至少可以fragment, 1D要一個超大continuous
     * 2D boolean https://leetcode.com/problems/design-hashset/discuss/143434/Beats-100-Real-Java-Solution-(Not-boolean-array))
     * <p>
     * https://leetcode.com/problems/design-hashset/discuss/355141/Java-Solution
     * 這個code很長 但目前是看起來比較標準的set實作
     * 有 load factor for re-hashing
     * cap -> set size
     */

    List<Integer>[] container = null;
    int cap = 1000;
    double loadFactor = 0.75;
    int count = 0;

    public void MyHashSet() {
        container = new LinkedList[cap];
    }

    private int getH(int key) {
        return key % cap;
    }

    private void maybeRehash() {
        if (loadFactor * cap == count) {
            count = 0;
            cap *= 2;
            List<Integer>[] oldC = container;
            container = new LinkedList[cap];
            for (List<Integer> list : oldC) {
                if (list != null) {
                    for (int oldK : list) this.add(oldK);
                }
            }
        }
    }

    public void add(int key) {
        if (contains(key)) return;
        maybeRehash();
        int hash = getH(key);
        if (container[hash] == null) container[hash] = new LinkedList<>();
        container[hash].add(key);
        count++;
    }

    public void remove(int key) {
        int hash = getH(key);
        List<Integer> list = container[hash];
        if (list != null) {
            Iterator<Integer> itr = list.iterator();
            while (itr.hasNext()) if (itr.next() == key) {
                itr.remove();
                count--;
            }
        }
    }

    public boolean contains(int key) {
        int hash = getH(key);
        List<Integer> list = container[hash];
        if (list != null) {
            Iterator<Integer> itr = list.iterator();
            while (itr.hasNext()) {
                if (itr.next() == key) return true;
            }
        }
        return false;
    }
}
