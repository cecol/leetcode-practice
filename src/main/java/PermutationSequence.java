import java.util.LinkedList;
import java.util.List;

public class PermutationSequence {
    public static void main(String[] a) {
        System.out.println(getPermutation(3, 5));
    }

    public static String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder("");
        List<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        while (numbers.size() > 0) {
            int c = 1;
            for (int i = 1; i < numbers.size(); i++) c *= i;
            sb.append(numbers.remove((k - 1) / c));
            k = k % c == 0 ? c : k % c;
        }
        return sb.toString();
    }
}
