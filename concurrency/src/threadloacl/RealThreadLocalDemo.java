package threadloacl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RealThreadLocalDemo {

    // 模拟封装成自己的工具类
    static class ThreadSafeFormatter {
        public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal
                = ThreadLocal.withInitial(() -> {
            System.out.println("Init Once");
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        });
    }

    public static String date(int seconds) {
        Date data = new Date(1000 * seconds);
        return ThreadSafeFormatter.dateFormatThreadLocal.get().format(data);
    }

    public static void main(String[] args) {
        useTheadPool();
    }

    private static void useTheadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            pool.execute(() -> {
                String date = date(finalI);
                System.out.println(date);
            });
        }
        pool.shutdown();
    }

}
