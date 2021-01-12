package test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassLoaderIs:test.MyClassLoader@5b80350b
 * hello classloader...
 */
public class MyClassLoader extends ClassLoader {
    private String path;

    public MyClassLoader(String classPath) {
        path = classPath;
    }


    @Override
    protected Class<?> findClass(String packageNamePath) throws ClassNotFoundException {
        Class clazz = null;
        byte[] classData = getData();

        if (classData != null) {
            clazz = defineClass(packageNamePath, classData, 0, classData.length);
        }
        return clazz;
    }

    private byte[] getData() {

        File file = new File(path);
        if (file.exists()) {
            FileInputStream in = null;
            ByteArrayOutputStream out = null;
            try {
                in = new FileInputStream(file);
                out = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = in.read(buffer)) != -1) {
                    out.write(buffer, 0, size);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            return out.toByteArray();
        } else {
            return null;
        }


    }


    public static void main(String[] args) throws Exception {
        String classPath = "E:\\untitled\\src\\main\\java\\test\\Hello1.class";
        String packageNamePath = "test.Hello1";
        MyClassLoader myClassLoader = new MyClassLoader(classPath);
        Class<?> clazz = myClassLoader.findClass(packageNamePath);
        System.out.println("ClassLoaderIs:" + clazz.getClassLoader());
        Method method = clazz.getDeclaredMethod("main", String[].class);
        Object object = clazz.newInstance();
        String[] arg = {""};
        method.invoke(object, (Object) arg);
    }
}
