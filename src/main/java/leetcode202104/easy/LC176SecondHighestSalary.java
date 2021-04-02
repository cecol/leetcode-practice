package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC176SecondHighestSalary extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC176SecondHighestSalary();
    }

    /**
     * 這題考MySQL??
     * (select distinct Salary as SecondHighestSalary from Employee order by Salary desc limit 1 offset 1)
     * UNION
     * (SELECT null)
     * LIMIT 1;
     *
     * 以上是想出來的 union 是為了補 null
     * 更短的
     * select (
     *   select distinct Salary from Employee order by Salary Desc limit 1 offset 1
     * ) as SecondHighestSalary
     *
     * */
}
