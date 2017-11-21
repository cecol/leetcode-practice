import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class NextGreaterElementII {
    public static void main(String[] s) {
        int[] n1 = {1, 2, 1};
        for (int i : nextGreaterElements(n1)) System.out.print(i + " ");
        System.out.println();
        int[] n2 = {5, 4, 3, 2, 1};
        for (int i : nextGreaterElements(n2)) System.out.print(i + " ");
    }

    public static int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int j = (i + 1) % nums.length;
            for (; j != i && nums[i] >= nums[j]; j = (j + 1) % nums.length);
            if (j != i) res[i] = nums[j];
            else res[i] = -1;
        }
        return res;
    }
}
