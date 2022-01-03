package cn.jho.jdk8.lambda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-11-08 17:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    private String name;
    private String age;

}
