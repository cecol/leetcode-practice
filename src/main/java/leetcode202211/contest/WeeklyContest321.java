package leetcode202211.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;
import util.list.ListNode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WeeklyContest321 extends BasicTemplate {
    public static void main(String[] args) throws IOException {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new WeeklyContest321();

        Stream<String> lines = Files.lines(Paths.get("./input.txt"), StandardCharsets.UTF_8);
        List<String> ln = lines.collect(Collectors.toList());
        LC.appendCharacters(ln.get(0), ln.get(1));

    }

    public int pivotInteger(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) sum += i;
        int p = 0;
        for (int i = 1; i <= n; i++) {
            p += i;
            if (sum - p + i == p) return i;
        }
        return -1;
    }

    public int appendCharacters(String s, String t) {
        int is = 0, it = 0;
        boolean[] dp = new boolean[t.length()];
        for (; is < s.length(); is++) {
            while (s.charAt(is) == t.charAt(it)) {
                if (it == 0) dp[0] = true;
                else dp[it] = dp[it - 1];
                it++;
            }
        }
        it = 0;
        for(;it<t.length();it++) if(!dp[it]) break;
        log.debug("{}", it);
        return t.length() - it;
    }

    public ListNode removeNodes(ListNode head) {
        Deque<ListNode> q = new LinkedList<>();
        ListNode cur = head;
        while (cur != null) {
            while (q.size() > 0 && q.peekLast().val < cur.val) q.pollLast();
            q.offerLast(cur);
            cur = cur.next;
        }
        ListNode h = q.pollFirst();
        cur = h;
        while (q.size() > 0) {
            cur.next = q.pollFirst();
            cur = cur.next;
        }
        return h;
    }

    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        TreeSet<Integer> ts = new TreeSet<>();
        int res = 1;
        ts.add(k);
        int l = 0;
        for (; l < n; l++) if (nums[l] == k) break;
        int r = l;
        boolean rp = true;
        while (r < n || l >= 0) {
            if ((rp || l < 0) && r < n - 1) {
                r++;
                ts.add(nums[r]);
                if (rp) rp = false;
            } else if ((!rp || r >= n) && l > 0) {
                l--;
                ts.add(nums[l]);
                if (!rp) rp = true;
            }

            List<Integer> list = new ArrayList<Integer>(ts);
            if (ts.size() / 2 == list.indexOf(k)) res++;
        }
        return res;
    }
}
