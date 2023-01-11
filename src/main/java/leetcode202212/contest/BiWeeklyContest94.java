package leetcode202212.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BiWeeklyContest94 extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new BiWeeklyContest94();
//        LC.minimizeSet(12, 3, 2, 10);
//        LC.minimizeSet(16, 14, 12, 8);
//        LC.topStudents(
//                new String[]{"fkeofjpc", "qq", "iio"},
//                new String[]{"jdh", "khj", "eget", "rjstbhe", "yzyoatfyx", "wlinrrgcm"},
//                new String[]{"rjstbhe eget kctxcoub urrmkhlmi yniqafy fkeofjpc iio yzyoatfyx khj iio", "gpnhgabl qq qq fkeofjpc dflidshdb qq iio khj qq yzyoatfyx", "tizpzhlbyb eget z rjstbhe iio jdh jdh iptxh qq rjstbhe", "jtlghe wlinrrgcm jnkdbd k iio et rjstbhe iio qq jdh", "yp fkeofjpc lkhypcebox rjstbhe ewwykishv egzhne jdh y qq qq", "fu ql iio fkeofjpc jdh luspuy yzyoatfyx li qq v", "wlinrrgcm iio qq omnc sgkt tzgev iio iio qq qq", "d vhg qlj khj wlinrrgcm qq f jp zsmhkjokmb rjstbhe"},
//                new int[]{96537918, 589204657, 765963609, 613766496, 43871615, 189209587, 239084671, 908938263}, 3);
//        LC.topStudents(
//                new String[]{"m", "eveszfubew"},
//                new String[]{"iq", "etwuedg", "egpakyk", "da", "qkmhvgxg", "q", "zs", "ujmy", "mh"},
//                new String[]{
//                        "eveszfubew jebebqp iq eveszfubew eveszfubew iq daej eveszfubew q da",
//                        "ohfz zs ujmy egpakyk eveszfubew pffeq q qkmhvgxg kdgqq ipp",
//                        "cceierguau mh da eveszfubew m etwuedg ikeft egpakyk ltnibxljfi m",
//                        "km m iq rab inooo ujmy tlrdyu yqhn m xlkhebs",
//                        "q etwuedg m eveszfubew ixrfzwmb m jyltumdwt dacmewk odbllqdiq eveszfubew"},
//                new int[]{643903773, 468275834, 993893529, 509587004, 61125507}, 5);
//        LC.countAnagrams("ptx cccbhbq");

        LC.topStudents(
                new String[]{"smart", "brilliant", "studious"},
                new String[]{"not"},
                new String[]{
                        "this student is not studious",
                        "the student is smart"},
                new int[]{1, 2}, 2);
    }


    public int captureForts(int[] forts) {
        int res = 0;
        int n = forts.length;

        for (int i = 0; i < n; i++) {
            if (forts[i] == 0 && i > 0 && i < n - 1) {
                if (forts[i - 1] == 0) continue;
                int j = i, k = i;
                while (k < n && forts[k] == 0) k++;
                while (j >= 0 && forts[j] == 0) j--;
                if (j >= 0 && k < n && (forts[j] != 0 && forts[k] != 0 && forts[j] != forts[k])) {
                    res = Math.max(res, k - j - 1);
                }
            }
        }
        return res;
    }

    public int minimizeSet(int d1, int d2, int c1, int c2) {
        TreeSet<Integer> s1 = new TreeSet<>();
        TreeSet<Integer> s2 = new TreeSet<>();
        for (int i = 1; s1.size() < c1; i++) {
            if (i % d1 != 0) s1.add(i);
        }

        for (int i = 1; s2.size() < c2; i++) {
            if (i % d2 != 0 && !s1.contains(i)) s2.add(i);
        }
        return 0;
    }

    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
            if (x[0] != y[0]) return y[0] - x[0];
            return x[1] - y[1];
        });

        Set<String> pf = new HashSet<>();
        Set<String> nf = new HashSet<>();
        for (String p : positive_feedback) pf.add(p);
        for (String p : negative_feedback) nf.add(p);

        for (int i = 0; i < student_id.length; i++) {
            String r = report[i];
            String[] rr = r.split(" ");
            int score = 0;
            for (String rm : rr) {
                if (pf.contains(rm)) score += 3;
                else if (nf.contains(rm)) score -= 1;
            }
            pq.offer(new int[]{score, student_id[i]});
        }
        List<Integer> res = new ArrayList<>();
        while (k > 0) {
            k--;
            res.add(pq.poll()[1]);
        }
        return res;
    }

    int m = (int) 1e9 + 7;
    Map<Integer, Long> map = new HashMap<>();

    public int countAnagrams(String s) {
        String[] ss = s.split(" ");
        long[] sum = new long[ss.length];
        for (int i = 0; i < ss.length; i++) {
            sum[i] = count(ss[i]);
        }
        long all = 1;
        for (long sss : sum) all = (all * sss) % m;
        return (int) all % m;
    }

    long count(String s) {
        long all = fn(s.length());
        int[] c = new int[26];
        for (char cc : s.toCharArray()) c[cc - 'a']++;
        for (int cc : c) if (cc > 1) all /= fn(cc);
        return all;
    }

    long fn(int i) {
        if (i == 1) return 1;
        if (i == 2) return 2;
        if (map.containsKey(i)) return map.get(i);
        long v = i * fn(i - 1) % m;
        map.put(i, v);
        return v;
    }
}
