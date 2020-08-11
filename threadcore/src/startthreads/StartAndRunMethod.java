package startthreads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable r1 = () -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("s1");
        };
        Runnable r2 = () -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("s2");
        };
        Runnable r3 = () -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("s3");
        };
        r1.run();
        new Thread(r2).start();
        new Thread(r3).run();
        Thread s2 = new Thread(r2);
        Class<?> threadClass = null;

        try {
            threadClass = Class.forName("java.lang.Thread");
            Method st0 = threadClass.getMethod("start0");
            st0.setAccessible(true);
            st0.invoke(s2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
