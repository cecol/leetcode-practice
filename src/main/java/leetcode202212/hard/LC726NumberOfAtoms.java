package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;

public class LC726NumberOfAtoms extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC726NumberOfAtoms();
    }

    /**
     * https://leetcode.com/problems/number-of-atoms/discuss/109345/Java-Solution-using-Stack-and-Map/264534
     * 這題一些細節處理真有夠複雜, 雖然邏輯很直觀可以直接處理, parser 題型都會長這樣
     * 1. Stack 紀錄當前暫存的 Atom count
     * 2. 最後輸出要是 Atom 排序好的 -> TreeMap.ketSet() iteration 就是排序好的
     * 3. maintain 當前正在計算的 TreeMap<String, Integer> map
     * - 遇到 '(' -> push into stack
     * - 遇到 ')' -> 先把後續數字讀完, 把上一層 map 讀出來: pre_map, pre_map 乘上剛剛數值 加入當前 map
     * - 遇到 atom + 後綴數字, 讀出 atom, 讀出數字, 加入當前 map
     * 4. 當前 map.keySet 走一遍加入結果到 StringBuilder
     * */
    public String countOfAtoms(String formula) {
        TreeMap<String, Integer> map = new TreeMap<>();
        Stack<TreeMap> sk = new Stack<>();
        int i = 0, len = formula.length();
        while (i < len) {
            if (formula.charAt(i) == '(') {
                sk.push(map);
                map = new TreeMap<>();
                i++;
            } else if (formula.charAt(i) == ')') {
                int val = 0;
                i++;
                while (i < len && Character.isDigit(formula.charAt(i))) val = val * 10 + formula.charAt(i++) - '0';
                val = val == 0 ? 1 : val;
                if (sk.size() > 0) {
                    TreeMap<String, Integer> temp = map;
                    map = sk.pop();
                    for (String atom : temp.keySet())
                        map.put(atom, temp.get(atom) * val + map.getOrDefault(atom, 0));
                }
            } else {
                int j = i + 1;
                while (j < len && Character.isLowerCase(formula.charAt(j))) j++;
                String atom = formula.substring(i, j);
                int val = 0;
                while (j < len && Character.isDigit(formula.charAt(j))) val = val * 10 + formula.charAt(j++) - '0';
                val = val == 0 ? 1 : val;
                map.put(atom, map.getOrDefault(atom, 0) + val);
                i = j;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String atom : map.keySet()) {
            sb.append(atom);
            sb.append(map.get(atom) == 1 ? "" : map.get(atom));
        }
        return sb.toString();
    }
}
