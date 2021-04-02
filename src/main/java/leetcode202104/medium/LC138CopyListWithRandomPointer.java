package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC138CopyListWithRandomPointer extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC138CopyListWithRandomPointer();
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 蠻直觀的就用Map去紀錄 新舊node mapping來處理 random
     * 有免用 Map解法, 但有點複雜
     * 至於Map用法, 這個更簡潔
     * https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43488/Java-O(n)-solution
     * */
    public Node copyRandomList(Node head) {
        Map<Node, Node> m = new HashMap<>();
        Node tmp = head;
        Node n = new Node(0);
        Node cur = n;
        while (tmp != null) {
            Node nn = new Node(tmp.val);
            cur.next = nn;
            m.put(tmp, nn);
            cur = nn;
            tmp = tmp.next;
        }
        tmp = head;
        while (tmp != null) {
            Node nn = m.get(tmp);
            if(tmp.random != null && m.get(tmp.random) != null) nn.random = m.get(tmp.random);
            tmp = tmp.next;
        }
        return n.next;
    }
}
