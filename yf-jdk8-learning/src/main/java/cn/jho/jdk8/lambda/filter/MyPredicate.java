package cn.jho.jdk8.lambda.filter;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-11 15:04
 */
public interface MyPredicate<T> {

    /**
     * 策略接口方法
     * @param t {@link T}
     * @return 是否符合
     */
    boolean test(T t);

}
