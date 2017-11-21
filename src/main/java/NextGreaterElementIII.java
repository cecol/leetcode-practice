import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NextGreaterElementIII {
    public static void main(String[] s) {
        System.out.println(nextGreaterElement(4121));
        System.out.println(nextGreaterElement(1999999999));
        System.out.println(nextGreaterElement(12443322));
    }

    public static int nextGreaterElement(int n) {
        char[] c = Integer.toString(n).toCharArray();
        int i = c.length - 1;
        for (; i > 0; i--) {
            if (c[i - 1] < c[i]) break;
        }
        if (i == 0) return -1;
        List<Character> l = new LinkedList<>();
        for (int j = c.length - 1; j >= i; j--) l.add(c[j]);
        Collections.sort(l);
        for (int j = 0; j < l.size(); j++) {
            if (l.get(j) > c[i - 1]) {
                char t = l.remove(j);
                l.add(j, c[i-1]);
                c[i - 1] = t;
                break;
            }
        }
        StringBuilder sb = new StringBuilder("");
        for (int j = 0; j <= i - 1; j++) sb.append(c[j]);
        for (char t : l) sb.append(t);
        Long res = Long.parseLong(sb.toString());
        return res > Integer.MAX_VALUE ? -1 : Integer.parseInt(sb.toString());
    }
}
