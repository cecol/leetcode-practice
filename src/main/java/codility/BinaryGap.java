package codility;

public class BinaryGap {
    public static void main(String[] a) {
        System.out.println(solution(529));
    }

    public static int solution(int N) {
        String binaryString = Integer.toBinaryString(N);
        System.out.println(binaryString);
        int max = 0;
        for (int i = 0, c = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                if (c > max) max = c;
                c = 0;
            } else {
                c++;
            }
        }
        return max;
    }
}
