package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC895MaximumFrequencyStack extends BasicTemplate {
    public static void main(String[] args) {
    }

    public void FreqStack() {

    }

    // 沒過
    // 關鍵資料結構
    // 1. 1 map 記載每個數字看過 頻次
    // 2. 1 map 記載該頻次 有哪些候選人的 STACK
    // 3. max 記載當前最大頻次
    // 4. pop 時候就是 MAX 去 頻次 stack 撈出第一個 Top
    // 5. max 頻次 stack is empty -> 代表該頻次沒了 -> max--
    Map<Integer, Integer> cm = new HashMap<>();
    Map<Integer, Stack<Integer>> m = new HashMap<>();
    int max;

    public void push(int val) {
        Integer f = cm.getOrDefault(val, 0) + 1;
        cm.put(val, f);
        max = Math.max(f, max);
        if (!m.containsKey(f)) m.put(f, new Stack<>());
        m.get(f).push(val);
    }

    public int pop() {
        Integer v = m.get(max).pop();
        cm.put(v, max - 1);
        if (m.get(max).isEmpty()) max--;
        return v;
    }
}
