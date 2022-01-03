package cn.jho.jdk8.lambda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-11 14:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    public Employee(Integer id) {
        this.id = id;
    }

    private Integer id;

    private String name;

    private Integer age;

    private Integer salary;

}
