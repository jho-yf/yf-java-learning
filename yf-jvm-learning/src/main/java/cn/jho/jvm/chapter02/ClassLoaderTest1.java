package cn.jho.jvm.chapter02;

import sun.misc.Launcher;

import java.net.URL;
import java.security.Provider;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-02-27 23:17
 */
public class ClassLoaderTest1 {

    public static void main(String[] args) {
        // 获取BootstrapClassLoader能够加载的api路径
        System.out.println("***********BootstrapClassLoader能够加载的api路径***********");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }

        System.out.println("***********java包下任意一个类的ClassLoader***********");
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);

        System.out.println("***********扩展类加载器能够加载的路径***********");
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(";")) {
            System.out.println(path);
        }
    }

}
