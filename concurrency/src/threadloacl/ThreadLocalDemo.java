package threadloacl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {
    // 没有解决这个对象每次都重复创建的问题
    public String date(int seconds) {
        Date data = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(data);
    }

    public static void main(String[] args) {
        useTheadPool();
    }

    // 使用复制的方法 两个线程
    private static void useCopy(){
        new Thread(() -> {
            String date = new ThreadLocalDemo().date(30);
            System.out.println(date);
        }).start();
        new Thread(() -> {
            String date = new ThreadLocalDemo().date(3000);
            System.out.println(date);
        }).start();
    }

    // 使用循环 创建多个线程
    private static void useFor(){
        for (int i=0;i<30;i++){
            int finalI = i;
            new Thread(() -> {
                String date = new ThreadLocalDemo().date(finalI *30);
                System.out.println(date);
            }).start();
        }
    }

    // 使用线程池 不要太多的线程创建开销
    private static void useTheadPool(){
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i=0;i<1000;i++){
            int finalI = i;
            pool.execute(() -> {
                String date = new ThreadLocalDemo().date(finalI *30);
                System.out.println(date);
            });
        }
        pool.shutdown();
    }
}
