package codility;

public class CyclicRotation {
    public static void main(String[] a) {
        for (int i : solution(new int[]{3, 8, 9, 7, 6}, 3)) System.out.print(i + " ");
    }

    public static int[] solution(int[] A, int K) {
        if (A == null || A.length == 0 || A.length == 1 || K <= 0) return A;
        for (; K > 0; K--) {
            int v = A[0];
            for (int i = A.length - 1; i > 0; i--) {
                A[((i + 1) % A.length)] = A[i];
            }
            A[1] = v;
        }
        return A;
    }
}
