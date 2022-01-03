package cn.jho.jdk8.lambda.filter;

import cn.jho.jdk8.lambda.model.Employee;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-11 15:10
 */
public class SalaryFilter implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 5000;
    }

}
