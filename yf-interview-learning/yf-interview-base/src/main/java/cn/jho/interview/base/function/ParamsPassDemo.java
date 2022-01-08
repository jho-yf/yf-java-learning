package cn.jho.interview.base.function;


import java.util.Arrays;

/**
 * 方法的参数传递机制
 *  形参是基本数据类型：按值传递
 *  实参是引用数据类型：按地址传递
 *      特殊类型：String、包装类等对象不可变性
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-06 22:30
 */
public class ParamsPassDemo {

    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer num = 2;
        int[] arr = {1, 2, 3, 4, 5};
        MyData data = new MyData();

        change(i, str, num, arr, data);
        System.out.println("i=" + i);
        System.out.println("str=" + str);
        System.out.println("num=" + num);
        System.out.println("arr=" + Arrays.toString(arr));
        System.out.println("data.a=" + data.a);
    }

    private static void change(int i, String str, Integer num, int[] arr, MyData data) {
        i += 1;
        str += "world";
        num += 1;
        arr[0] += 1;
        data.a += 1;
    }

}

class MyData {

    int a = 10;

}
