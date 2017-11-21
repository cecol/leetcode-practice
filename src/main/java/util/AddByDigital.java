package util;

public class AddByDigital {
    public static void main(String[] a) {
        System.out.println(add(5551));
    }

    public static int add(int n) {
        if (n / 10 == 0) return n % 10;
        else {
            return add(n / 10) + n % 10;
        }
    }
}
