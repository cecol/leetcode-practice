package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

public class LC2213LongestSubstringOfOneRepeatingCharacter extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2213LongestSubstringOfOneRepeatingCharacter();
    }

    /**
     * https://leetcode.com/problems/longest-substring-of-one-repeating-character/discuss/1871687/Java-or-Segment-Tree
     * 要再回來重看 segment tree 算法
     * Segment tree 解釋
     * https://cp-algorithms.com/data_structures/segment_tree.html#range-updates-lazy-propagation
     */
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length(), k = queryIndices.length;
        SegmentTree st = new SegmentTree(s);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            st.update(queryIndices[i], queryCharacters.charAt(i));
            res[i] = st.rangeQuery(0, n - 1).maxLen;
        }
        return res;
    }

    static class SegmentTree {
        int n;
        char[] A;
        Node[] st;

        public SegmentTree(String s) {
            this.A = s.toCharArray();
            this.n = A.length;
            this.st = new Node[4 * n];
            build(1, 0, n - 1);
        }

        Node rangeQuery(int i, int j) {
            return rangeQuery(1, 0, n - 1, i, j);
        }

        void update(int i, char val) {
            update(1, 0, n - 1, i, i, val);
        }

        static class Node {
            int maxLen;
            boolean mono;
            char cPref, cSuff;
            int lenPref, lenSuff;

            Node(char c, int len) {
                mono = true;
                cPref = cSuff = c;
                maxLen = lenPref = lenSuff = len;
            }

            Node(int maxLen, char cPref, int lenPref, char cSuff, int lenSuff) {
                this.mono = false;
                this.maxLen = maxLen;
                this.cPref = cPref;
                this.lenPref = lenPref;
                this.cSuff = cSuff;
                this.lenSuff = lenSuff;
            }

            static Node merge(Node l, Node r) {
                if (l == null) return r;
                if (r == null) return l;
                if (l.cSuff != r.cPref)
                    return new Node(Math.max(l.maxLen, r.maxLen), l.cPref, l.lenPref, r.cSuff, r.lenSuff);
                if (l.mono && r.mono) return new Node(l.cPref, l.maxLen + r.maxLen);
                if (l.mono)
                    return new Node(Math.max(l.maxLen + r.lenPref, r.maxLen), l.cPref, l.maxLen + r.lenPref, r.cSuff, r.lenSuff);
                if (r.mono)
                    return new Node(Math.max(l.lenSuff + r.maxLen, l.maxLen), l.cPref, l.lenPref, r.cSuff, l.lenSuff + r.maxLen);
                return new Node(Math.max(l.lenSuff + r.lenPref, Math.max(l.maxLen, r.maxLen)), l.cPref, l.lenPref, r.cSuff, r.lenSuff);
            }
        }

        void build(int p, int L, int R) {
            if (L == R) {
                st[p] = new Node(A[L], 1);
            } else {
                int m = (L + R) / 2, lp = p * 2, rp = 2 * p + 1;
                build(lp, L, m);
                build(rp, m + 1, R);
                st[p] = Node.merge(st[lp], st[rp]);
            }
        }

        void update(int p, int L, int R, int i, int j, char val) {
            if (i > j) return;
            if ((L >= i) && (R <= j)) {
                st[p] = new Node(A[i] = val, 1);
            } else {
                int m = (L + R) / 2, lp = p * 2, rp = 2 * p + 1;
                update(lp, L, m, i, Math.min(m, j), val);
                update(rp, m + 1, R, Math.max(i, m + 1), j, val);
                st[p] = Node.merge(st[lp], st[rp]);
            }
        }

        Node rangeQuery(int p, int L, int R, int i, int j) {
            if (i > j) return null;
            if ((L >= i) && (R <= j)) return st[p];
            int m = (L + R) / 2, lp = p * 2, rp = 2 * p + 1;
            return Node.merge(
                    rangeQuery(lp, L, m, i, Math.min(m, j)),
                    rangeQuery(rp, m + 1, R, Math.max(m + 1, i), j));
        }
    }
}
