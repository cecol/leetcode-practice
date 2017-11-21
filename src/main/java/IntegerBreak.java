public class IntegerBreak {
    public static void main(String[] args) {
        System.out.println(integerBreak(2));
    }

    public static int integerBreak(int n) {
        int m = 0;
        if(n == 2) return 1;
        if(n == 3) return 2;
        if (n % 3 == 0) m = (int) java.lang.Math.pow((double) 3, (double) n / 3);
        if (n % 3 == 1) m = 4 * (int) java.lang.Math.pow((double) 3, (double) (n - 4) / 3);
        if (n % 3 == 2) m = 2 * (int) java.lang.Math.pow((double) 3, (double) (n - 2) / 3);
        return m;
    }
}
