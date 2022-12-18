package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class LC382LinkedListRandomNode extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC382LinkedListRandomNode();
    }


    /**
     * https://blog.csdn.net/anshuai_aw1/article/details/88750673
     * 這篇重點考  蓄水池采样算法（Reservoir Sampling）
     * 無腦應該是把 ListNode 轉成 Array 拿去 random idx 取出要的
     * 但如果 ListNode 很長, mem 存不下去呢?
     * <p>
     * 所以不能 存在 mem
     * 只能 [邊取邊算] 機率
     * 證明:
     * 取 1st data -> 1/1
     * 取 2nd data -> 1/2
     * - 取第二个数据，发现数据流结束了。
     * - 因此我们只要保证以相同的概率返回第一个或者第二个数据就可以满足题目要求
     * - 因此我们生成一个 [0 to 1] 的随机数R， if R < 0.5 ，我们就返回第一个数据，如果R大于0.5，返回第二个数据。
     * 取 3rd data -> 1/3
     * - 我们陆续收到了数据1、2。和前面的例子一样，我们只能保存一个数据，所以必须淘汰1和2中的一个。
     * 应该如何淘汰呢？不妨和上面例子一样，我们按照二分之一的概率淘汰一个，例如我们淘汰了2。继续读取流中的数据3，发现数据流结束了，
     * 我们知道在长度为3的数据流中，如果返回数据3的概率为1/3，那么才有可能保证选择的正确性。
     * 也就是说，目前我们手里有1、3两个数据，我们通过一次随机选择，以1/3的概率留下数据3，[以2/3的概率留下数据1]。那么数据1被最终留下的概率是多少呢？
     * 数据1被留下概率：（1/2）* (2/3) = 1/3
     * 数据2被留下概率：（1/2）*(2/3) = 1/3
     * 数据3被留下概率：1/3
     *
     * 大致上是這樣
     * 所以在 getRandom()
     * 每次 num = rand.nextInt(count) + 1; 如果剛好 num == 1 or num == count
     * 我們就可以更新 choose = cur;
     * 就是指剛好取到最新的那一個 1/N 機率
     * 如果不是, 本來的 choose 也遺留在 本來的 1/N 機率
     */
    class Solution {

        ListNode head = null;
        Random rand = new Random();

        public Solution(ListNode head) {
            this.head = head;
        }

        public int getRandom() {
            ListNode cur = head, choose = cur;
            int count = 1;
            while (cur != null) {
                int num = rand.nextInt(count) + 1;
                if (num == 1) choose = cur;
                count++;
                cur = cur.next;
            }
            return choose.val;
        }
    }

}
