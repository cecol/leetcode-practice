package codility201712;

public class SquaredDistance {
    public static void main(String[] a) {

    }

    public static int solution(int A, int B, int C, int D) {
        int case1 = (int) (Math.pow(A - B, 2) + Math.pow(C - D, 2));
        int case2 = (int) (Math.pow(A - C, 2) + Math.pow(B - D, 2));
        int case3 = (int) (Math.pow(A - D, 2) + Math.pow(B - C, 2));
        int max = case1 > case2 ? case1 > case3 ? case1 : case3 : case2 > case3 ? case2 : case3;
        return max;
    }
}
