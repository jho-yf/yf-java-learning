package cn.jho.jdk8.lambda;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-15 20:22
 */
public interface Operation2<T, R> {

    /**
     * 操作
     * @param t1 {@link T}
     * @param t2 {@link T}
     * @return {@link R}
     */
    R operate(T t1, T t2);

}
