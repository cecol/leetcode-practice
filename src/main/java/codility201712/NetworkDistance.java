package codility201712;


public class NetworkDistance {
    public int[] solution(int[] T) {
        int[] dpDistance = new int[T.length];
        for (int i = 0; i < T.length; i++) dpDistance[i] = -1;
        for (int i = 0; i < T.length; i++) {
            if (dpDistance[i] == -1) depthGo(T, dpDistance, i);
        }
        int[] result = new int[T.length - 1];
        for (int i : dpDistance) if (i > 0) result[i - 1]++;
        return result;
    }

    public void depthGo(int[] T, int[] dpDistance, int p) {
        if (T[p] == p) {
            dpDistance[p] = 0;
        } else if (dpDistance[T[p]] != -1) {
            dpDistance[p] = dpDistance[T[p]] + 1;
        } else {
            depthGo(T, dpDistance, T[p]);
            dpDistance[p] = dpDistance[T[p]] + 1;
        }
    }
}
