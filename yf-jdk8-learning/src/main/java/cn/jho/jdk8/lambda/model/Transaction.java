package cn.jho.jdk8.lambda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-11-01 23:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {

    private Trader trader;

    private int year;

    private int value;

}
