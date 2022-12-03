package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;
import java.util.TreeMap;

public class LC1146SnapshotArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1146SnapshotArray();
    }


    /**
     * https://leetcode.com/problems/snapshot-array/discuss/350562/JavaPython-Binary-Search
     * 最直觀是每次都儲存當下的 snap, 但會浪費很多時間
     * 我們只儲存有改變過的 snap, 沒有紀錄 default = 0
     * 所以每個 element 都是 TreeMap<Integer, Integer>
     * -  key: 當前 snap_id, value 當前值
     * -  每次 set 就是 set 當前 snap_id, value 當前值
     * -  snap() 就是 snap_id++
     * -  get 就是走到哪個值得 TreeMap, 然後用 snap_id 往後找有找到就是
     * -  沒找到是因為我們一開始有放 tm[i].put(0, 0); 當預設值
     */
    TreeMap<Integer, Integer>[] tm;
    int idx = 0;

    public void SnapshotArray(int length) {
        tm = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            tm[i] = new TreeMap<>();
            tm[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        tm[index].put(idx, val);
    }

    public int snap() {
        return idx++;
    }

    public int get(int index, int snap_id) {
        return tm[index].floorEntry(snap_id).getValue();
    }
}
