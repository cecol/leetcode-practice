package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC386LexicographicalNumbers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC386LexicographicalNumbers();
    }

    /**
     * https://leetcode.com/problems/lexicographical-numbers/solutions/86231/simple-java-dfs-solution/
     * 這題有個邏輯可以歸納出 1,10,100  .... 2,20,200...
     * 但沒這麼好寫出邏輯 直到看到這個 超美dfs
     * 其實可以看作成這樣
     * The idea is pretty simple.
     * If we look at the order we can find out we just keep adding digit from 0 to 9 to every digit and make it a tree.
     * Then we visit every node in pre-order.
     * -       1        2        3    ...
     * -      /\        /\       /\
     * -   10 ...19  20...29  30...39   ....
     * 用這種方式來看 dfs tree 展開, 就可以直接用 preorder 來做到
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) dfs(i, res, n);
        return res;
    }

    void dfs(int cur, List<Integer> res, int n) {
        if (cur > n) return;
        res.add(cur);
        for (int i = 0; i < 10; i++) {
            if (cur * 10 + i > n) return;
            dfs(cur * 10 + i, res, n);
        }
    }
}
