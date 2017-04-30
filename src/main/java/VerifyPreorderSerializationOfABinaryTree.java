import java.util.Objects;

/**
 * lee code question address
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/#/description
 * key point:
 * 1: binary tree: number of nodes + 1 = number of leaves, and #=leaves, number=node
 * 2: check the number of time of #, if number get 3, it should be end of tree, no more nodes
 */

public class VerifyPreorderSerializationOfABinaryTree {
    public static void main(String[] args) {
        String c1 = "9,9,91,#,#,9,#,49,#,#,#";
        String c2 = "7,2,#,2,#,#,#,6,#";
        String c3 = "#";

        String[] ca = {c1, c2, c3};
        for (String c : ca) {
            boolean r = isValidSerialization(c);
            System.out.print(r);
            System.out.println(" " + c);
        }
    }

    public static boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        //validate this binary tree follow key point 1 or not
        int nullCount = 0;
        int nodeCounts = 0;
        for (String s : nodes) {
            if (Objects.equals(s, "#")) {
                nullCount += 1;
            } else {
                nodeCounts += 1;
            }
        }

        boolean reasonableNull = true;
        int nullAvailable = 0;
        for (int i = 0; i < nodes.length; i++) {
            //for c3
            if (nodes[0].equals("#") && nodes.length > 1) {
                reasonableNull = false;
                break;
            }
            if (nodes[i].equals("#")) {
                nullAvailable -= 1;
            } else {
                nullAvailable += 1;
            }
            if (nullAvailable == -1 && i != nodes.length - 1) {
                reasonableNull = false;
                break;
            }
        }

        boolean reasonableCount = nullCount == nodeCounts + 1;

        return reasonableCount && reasonableNull;
    }
}
