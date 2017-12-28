package codility201712;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FindMaxWindowFromArrayInt {
    public int solution(int[] movies, int K, int L) {
        if (K + L > movies.length) return -1;
        int max = -1;
        for (int i = 0; i + K <= movies.length; i++) {
            int[] subK = Arrays.copyOfRange(movies, i, i + K);
            int KSum = IntStream.of(subK).sum();
            int lL = maxOfSubArray(Arrays.copyOfRange(movies, 0, i), L);
            int rL = maxOfSubArray(Arrays.copyOfRange(movies, i + K, movies.length), L);
            if (lL != -1 && rL != -1) {
                int totalSum = lL > rL ? KSum + lL : KSum + rL;
                if (totalSum > max) max = totalSum;
            } else if (lL != -1) {
                int totalSum = KSum + lL;
                if (totalSum > max) max = totalSum;
            } else if (rL != -1) {
                int totalSum = KSum + rL;
                if (totalSum > max) max = totalSum;
            }
        }
        return max;
    }

    public int maxOfSubArray(int[] sub, int L) {
        if (L > sub.length) return -1;
        int max = -1;
        for (int i = 0; i + L <= sub.length; i++) {
            int[] subL = Arrays.copyOfRange(sub, i, i + L);
            int sum = IntStream.of(subL).sum();
            if (sum > max) max = sum;
        }
        return max;
    }
}
