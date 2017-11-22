package leetcode_old;

public class ClimbingStairs {
    public static void main(String[] a) {
//        System.out.println(climbing(1));
//        System.out.println(climbing(2));
//        System.out.println(climbing(3));
//        System.out.println(climbing(4));
        System.out.println(2/(1/8.0));
    }

    public static int climbing(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbing(n - 1) + climbing(n - 2);
    }

    public static int climbing2(int n) {

        //悠浮科技
        //ucfunnel
        return 1;
    }
}
