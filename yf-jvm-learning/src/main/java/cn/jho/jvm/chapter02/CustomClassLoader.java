package cn.jho.jvm.chapter02;

import java.io.FileNotFoundException;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-03-01 7:37
 */
public class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null) {
                throw new FileNotFoundException();
            } else {
                return defineClass(name, result, 0, result.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    private byte[] getClassFromCustomPath(String name) {
        // 从自定义路径中加载指定类
        // 如果指定路径字节码文件被加密，在此处需要进行解密
        return null;
    }

    public static void main(String[] args) {
        CustomClassLoader classLoader = new CustomClassLoader();
        try {
            Class<?> clazz = Class.forName("One", true, classLoader);
            Object obj = clazz.newInstance();
            System.out.println(obj.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
