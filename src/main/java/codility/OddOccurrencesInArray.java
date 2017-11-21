package codility;

public class OddOccurrencesInArray {
    public static void main(String[] a) {
        System.out.println(solution(new int[]{9, 3, 9, 3, 9, 7, 9}));
    }

    public static int solution(int[] A) {
        if (A == null || A.length == 0) return 0;
        int v = A[0];
        for (int i = 1; i < A.length; i++) v ^= A[i];
        return v;
    }
}
