import java.util.HashMap;
import java.util.Map;

public class BobEatBreadJava {
    public static void main(String[] a) {
        eat(5, 70, 3, 2, new int[]{4, 6, 2, 5, 3});
    }

    public static int eat(int n, int w, int a, int b, int[] c) {
        if (c == null || c.length == 0 || c.length != n) return w; //null pointer case
        HashMap<String, Integer[]> record = new HashMap<>();

        for (int i = 0; i < c.length; i++) {
            if (i == 0) {
                record.put("1", new Integer[]{w + c[i], 0});
                record.put("0", new Integer[]{w - a + b, 1});
            } else {
                HashMap<String, Integer[]> record2 = new HashMap<>();
                for (Map.Entry<String, Integer[]> e : record.entrySet()) {
                    record2.put(e.getKey() + "1", new Integer[]{e.getValue()[0] + c[i], 0});
                    int noEat = e.getValue()[0] - a + b * (e.getValue()[1] + 1);
                    int noEatWeight = noEat > 0 ? noEat : 0; //if Weight < 0, set it to 0
                    record2.put(e.getKey() + "0", new Integer[]{noEatWeight, e.getValue()[1] + 1});
                }
                record = record2;
            }
        }

        int min = -1;
        String result = "";
        for (Map.Entry<String, Integer[]> e : record.entrySet()) {
            if (min == -1) {
                min = e.getValue()[0];
                result = e.getKey();
            }
            if (min > e.getValue()[0]) {
                min = e.getValue()[0];
                result = e.getKey();
            }
        }
        for (int i = 0; i < result.length(); i++) {
            String d = (i + 1) + "th";
            if (i == 0) d = "1st";
            else if (i == 1) d = "2nd";
            else if (i == 2) d = "3rd";
            String decision = result.charAt(i) == '1' ? "eat" : "not eat";
            System.out.println(d + " day= " + decision);
        }
        System.out.println("The minimum weight is \"" + min + "\"kg.");
        return min;
    }
}
