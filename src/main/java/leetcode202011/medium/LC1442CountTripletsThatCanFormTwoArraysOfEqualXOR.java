package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR();
        LC.countTriplets(new int[]{2, 3, 1, 6, 7});
        LC.countTriplets2(new int[]{2, 3, 1, 6, 7});
    }

    /**
     * A ^ B == 0 => A == B
     * look for XOR[i .. k] == 0
     * XOR[i .. k] == 0
     * A[0] ^ A[1] ^ ... ^ A[i-1]
     * XOR
     * A[0] ^ A[1] ^ ... ^ A[i-1] ^ A[i] ^ A[i+1] ^ ... ^ A[k]
     * == 0
     * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/discuss/623754/Java-Use-the-Trick-of-XOR-operation!!!
     * O(N^2) 解法, 直接針對每個 arr[i], 往後看 j = i + 1 to arr.length
     * 一路 xor 下去, xor == 0 就是這區間都是合法的 (0 <= i < j <= k < arr.length)
     *
     * 只是這區間有多少個組合?
     * c += (j - i) 是怎麼想出來的?
     *
     * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/discuss/623754/Java-Use-the-Trick-of-XOR-operation!!!/1280823
     * 這樣想好了 i 到 j 區間共有 j-i+1 元素
     * 有多少辦法切成 兩個 non-empty sub-array?
     * [5,6,7,8] -> 切成兩個 non-empty sub-array ? 長度是 8-5+1 = 4
     * 因為 [5,6,7,8] 4個數中只有3個空缺(逗號)  就是 8-5 == j-i
     *
     * 我一直想錯說 [5,6,7] 的切法會包含 [5,6] 切法
     * 但事實上不是 [5,6,7] 切成兩份的結果  一定跟 [5,6] 切成兩份不一樣
     * [5,6] 切成兩份一定是 [5] & [6]
     * [5,6,7] 切成兩份任一份一定有2個元素  所以組合跟上面完全不樣!!
     * 我會一直誤解應該是因為看到範例
     * Input: arr = [2,3,1,6,7], triplets are (0,1,2)...
     * 看到 (0,1,2) 以為一定要三個元素, 但其實只是三個座標, 可以兩個元素 但產生3個座標 因為 (0 <= i < j <= k < arr.length)
     * j <= k 是允許的!!
     */
    public int countTriplets(int[] arr) {
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            int xor = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                xor ^= arr[j];
                if (xor == 0) {
                    c += (j - i);
                }
            }
        }
        return c;
    }

    /**
     * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/discuss/2764050/INTUITIVE-JAVA-SOLUTION-WITH-OPTIMIZATON
     * O(N) 解釋
     * 假設 (a,i), (b,i) and (c,i) are 3 subarrays ending at index i with zero xor.
     * If contribution to answer is : i-a + i-b + i- c = 3*i - (a + b + c).
     * 從 a 算到 i = i - a,
     * 從 b 算到 i = i - b,
     * 從 c 算到 i = i - c,
     * 總結是 3*i - (a+b+c)
     * 所以要
     * xorCount 記載當前該 XORi 出現幾次 -> xorCount.put(xorSum, count + 1);
     * indexSum 記載前面出現的 a,b,c index sum 就是 a+b+c -> indexSum.put(xorSum, i + sum + 1);
     * 為什麼是 i + sum + 1? + 1 哪來的?
     * */
    public int countTriplets2(int[] arr) {
        int c = 0;
        if (arr == null || arr.length < 2) return c;
        Map<Integer, Integer> xorCount = new HashMap<>();
        Map<Integer, Integer> indexSum = new HashMap<>();
        xorCount.put(0, 1);
        int xorSum = 0;
        for (int i = 0; i < arr.length; i++) {
            xorSum ^= arr[i];
            int count = xorCount.getOrDefault(xorSum, 0);
            int sum = indexSum.getOrDefault(xorSum, 0);
            c += count * i - sum;
            xorCount.put(xorSum, count + 1);
            indexSum.put(xorSum, i + sum + 1);
        }
        return c;
    }
}
