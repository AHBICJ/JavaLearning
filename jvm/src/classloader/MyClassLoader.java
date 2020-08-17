package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {
    private String name;

    public MyClassLoader(String name) {
        this.name = name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassDate(name);
        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassDate(String clsName) {
        byte[] data = null;
        InputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        clsName = clsName.replace('.', '/');
        try (out) {
            in = new FileInputStream(new File("classes/" + clsName + ".class"));
            int a = 0;
            while ((a = in.read()) != -1) {
                out.write(a);
                data = out.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
