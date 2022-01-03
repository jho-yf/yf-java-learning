package cn.jho.jdk8.lambda;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-15 19:52
 */
@FunctionalInterface
public interface MyPredicate<T> {

    /**
     * 函数式接口
     * @param t {@link T}
     * @return boolean
     */
    boolean test(T t);

}
