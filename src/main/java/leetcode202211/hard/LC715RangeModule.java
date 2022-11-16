package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class LC715RangeModule extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC715RangeModule();
    }

    public void RangeModule() {

    }

    TreeMap<Integer, Integer> tm = new TreeMap<>();

    /**
     * 我知道可以用 TreeMap 來做 Key = range start, end = range end
     * 只是沒想到可以做得這麼乾淨
     * https://leetcode.com/problems/range-module/discuss/2271749/Java-TreeMap-with-tons-of-explanations
     *
     * addRange
     * 就是要把 left, right 區間都找出來 全部 merge
     * 所以就是一直找任何有 TreeMap.key <= right, 都是可能要 merge
     * 沒找到 或者 找到的 TreeMap.key > left, 就代表結束
     * 不然就是把該 TreeMap.key 刪掉, 然後擴大 left, right
     *
     * queryRange
     * 因為要找到全包覆, 所以是找 TreeMap.key <= left
     * 如果找到的 TreeMap.key.value >= right 就是了
     *
     * removeRange 最經典是
     * 先把 left, right 拿去 addRange
     * 整個區間合併
     *
     * 再回頭找出 區間合併
     * 然後回頭去砍掉 區間合併 結果
     * 如果 區間合併 結果 < left => 要補回去
     * 如果 區間合併 結果 > right => 要補回去
     * */
    public void addRange(int left, int right) {
        while (true) {
            Map.Entry<Integer, Integer> lastPossibleOverlap = tm.floorEntry(right);
            if (lastPossibleOverlap == null || lastPossibleOverlap.getValue() < left) break;
            left = Math.min(lastPossibleOverlap.getKey(), left);
            right = Math.max(lastPossibleOverlap.getValue(), right);
            tm.remove(lastPossibleOverlap.getKey());
        }
        tm.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> possibleOverlap = tm.floorEntry(left);
        return possibleOverlap != null && possibleOverlap.getValue() >= right;
    }

    public void removeRange(int left, int right) {
        addRange(left, right);
        Map.Entry<Integer, Integer> firstOverlap = tm.floorEntry(left);
        tm.remove(firstOverlap.getKey());

        if(firstOverlap.getKey() < left) tm.put(firstOverlap.getKey(), left);
        if(firstOverlap.getValue() > right) tm.put(right, firstOverlap.getValue());
    }
}
