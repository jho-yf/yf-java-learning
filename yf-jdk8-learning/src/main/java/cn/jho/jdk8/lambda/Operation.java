package cn.jho.jdk8.lambda;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-15 19:55
 */
@FunctionalInterface
public interface Operation {

    /**
     * 运算
     *
     * @param num {@link Integer}
     * @return {@link Integer}
     */
    Integer operate(Integer num);

}
