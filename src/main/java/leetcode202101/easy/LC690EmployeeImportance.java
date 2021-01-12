package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC690EmployeeImportance extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC690EmployeeImportance();
        var s = LC.getImportance(null, 1);
    }

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> m = new HashMap<>();
        for (Employee e : employees) m.put(e.id, e);
        return im(m.get(id), m);
    }

    private int im(Employee e, Map<Integer, Employee> m) {
        int res = e.importance;
        if (!e.subordinates.isEmpty()) {
            for (Integer s : e.subordinates) {
                res += im(m.get(s), m);
            }
        }
        return res;
    }
}
