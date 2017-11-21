package util;

public class FindSecond {
    public static void main(String[] a) {
        String a1 = "a";
        String a2 = "a";

        System.out.println(a1.intern() == a2.intern());
    }

    public static int add(int[] n) {
        int max = n[0];
        int second = n[0];
        for (int i : n) if (max < i) max = i;
        for (int i : n) if (second < i && i != max) second = i;
        return second;
    }
}
