package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class LC631DesignExcelSumFormula extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC631DesignExcelSumFormula();
    }

    /**
     * https://leetcode.com/problems/design-excel-sum-formula/discuss/104857/Java-implement-the-logic-in-Cell-class-easy-to-understand
     * 其實蠻直觀的,
     * 1. 建立一個 Cell class
     * -  裏面存 val && HashMap<Cell, Integer> formula
     * -  有 setVal, 要清除舊的 formula, set val
     * -  有 getVal, 如果是 formula, 得遞迴 formula.getVal 下去, 如果 formula is empty, 回傳 val 就好
     * -  有 setFormula build formula map,
     * 2. 然後就可以建立一個  Cell[][] table = new Cell[26][26];
     * 接下來就直接用 Cell 功能了
     */
    class Cell {
        int val;
        HashMap<Cell, Integer> formula = new HashMap<>();

        Cell(int v) {
            val = v;
        }

        Cell(String[] strs) {
            setFormula(strs);
        }

        void setFormula(String[] strs) {
            formula.clear();
            for (String s : strs) {
                if (s.indexOf(':') == -1) {
                    int[] p = getPos(s);
                    addCell(p[0], p[1]);
                } else {
                    String[] pos = s.split(":");
                    int[] start = getPos(pos[0]);
                    int[] end = getPos(pos[1]);
                    for (int r = start[0]; r <= end[0]; r++)
                        for (int c = start[1]; c <= end[1]; c++)
                            addCell(r, c);
                }
            }
        }

        int[] getPos(String s) {
            return new int[]{Integer.parseInt(s.substring(1)), s.charAt(0) - 'A'};
        }

        void addCell(int r, int c) {
            if (table[r][c] == null) table[r][c] = new Cell(0);
            Cell cell = table[r][c];
            formula.put(cell, formula.getOrDefault(cell, 0) + 1);
        }

        int getVal() {
            if (formula.size() == 0) return val;
            int sum = 0;
            for (Cell k : formula.keySet()) {
                sum += k.getVal() * formula.get(k);
            }
            return sum;
        }

        void setValue(int v) {
            formula.clear();
            val = v;
        }
    }

    Cell[][] table = new Cell[26][26];

    public void set(int r, char c, int val) {
        if (table[r][c - 'A'] == null) table[r][c - 'A'] = new Cell(val);
        else table[r][c - 'A'].setValue(val);
    }

    public int get(int r, char c) {
        if (table[r][c - 'A'] == null) return 0;
        else return table[r][c - 'A'].getVal();
    }

    public int sum(int row, char column, String[] numbers) {
        if (table[row][column - 'A'] == null) table[row][column - 'A'] = new Cell(numbers);
        else table[row][column - 'A'].setFormula(numbers);
        return table[row][column - 'A'].getVal();
    }
}
