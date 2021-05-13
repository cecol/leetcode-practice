package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC1352ProductOfTheLastKNumbers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1352ProductOfTheLastKNumbers();
    }
    /**
     * 很直觀的就是sub array sum的翻版
     * 只是product 要處理 0的問題, 所以要記載0最後出現的位置, 如果range cover到 就是回傳0
     * */
    List<Integer> l = new ArrayList<>();
    int lastZ = -1;
    public void ProductOfNumbers() {

    }

    public void add(int num) {
        if (l.size() == 0) l.add(num);
        else {
            if (l.get(l.size() - 1) == 0) l.add(num);
            else l.add(num * l.get(l.size() - 1));
        }
        if (num == 0) lastZ = l.size() - 1;
    }

    public int getProduct(int k) {
        int idx = l.size() - k;
        if (lastZ >= idx) return 0;
        else {
            if (idx == 0) return l.get(l.size() - 1);
            return l.get(l.size() - 1) / (l.get(idx - 1) == 0 ? 1 : l.get(idx - 1));
        }
    }
}
