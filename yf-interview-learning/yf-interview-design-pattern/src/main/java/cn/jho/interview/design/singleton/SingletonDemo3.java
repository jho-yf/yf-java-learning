package cn.jho.interview.design.singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-05 22:55
 */
public class SingletonDemo3 {

    public static final SingletonDemo3 INSTANCE;

    private String info;

    static {
        Properties properties = new Properties();
        try {
            properties.load(SingletonDemo3.class.getClassLoader().getResourceAsStream("single.properties"));
            INSTANCE = new SingletonDemo3(properties.getProperty("info"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SingletonDemo3(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "SingletonDemo3 [info=" + info + "]";
    }
}
