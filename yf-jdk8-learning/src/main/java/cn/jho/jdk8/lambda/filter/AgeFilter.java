package cn.jho.jdk8.lambda.filter;

import cn.jho.jdk8.lambda.model.Employee;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-11 15:09
 */
public class AgeFilter implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() > 30;
    }

}
