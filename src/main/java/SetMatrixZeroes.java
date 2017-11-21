import java.util.HashSet;

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        if (matrix == null) return;
        HashSet<Integer> row = new HashSet<>(), col = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        for (int i : row) for (int j = 0; j < matrix[i].length; j++) matrix[i][j] = 0;
        for (int i : col) for (int j = 0; j < matrix.length; j++) matrix[j][i] = 0;
    }
}
