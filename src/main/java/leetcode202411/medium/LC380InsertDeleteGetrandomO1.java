package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC380InsertDeleteGetrandomO1 extends BasicTemplate {
    public static void main(String[] args) {
    }

    public void RandomizedSet() {

    }

    // 沒過
    // 1. 用 map 記載看過的 val & val offset in list
    // 2. remove 得先把 VAL 移到 list end, remove 才會是 O(1)
    // 3. random * list.size 就是隨機拿取
    Map<Integer, Integer> idx = new HashMap<>();
    List<Integer> res = new ArrayList<>();

    public boolean insert(int val) {
        if (idx.containsKey(val)) return false;
        res.add(val);
        idx.put(val, res.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!idx.containsKey(val)) return false;
        int offset = idx.get(val);
        Collections.swap(res, offset, res.size() - 1);
        idx.put(res.get(offset), offset);
        res.remove(res.size() - 1);
        idx.remove(val);
        return true;
    }

    public int getRandom() {
        return res.get((int) (Math.random() * res.size()));
    }
}
