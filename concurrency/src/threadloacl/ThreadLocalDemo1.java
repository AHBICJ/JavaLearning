package threadloacl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo1 {
    // 使用全局的静态变量，减少这个对象的重复生成和销毁，想得很美但会出现问题
    // 因为 SimpleDateFormat线程不安全，
    // dataFormat里面用到了calendar这个成员变量的setTime
    // 并发访问会存在数据错误问题
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static String date(int seconds) {
        Date data = new Date(1000 * seconds);
        return dateFormat.format(data);
    }

    // 加个锁试试
    public static String safeDate(int seconds){
        Date data = new Date(1000 * seconds);
        String s;
        synchronized (ThreadLocalDemo1.class){
            s = dateFormat.format(data);
        }
        return s;
    }

    public static void main(String[] args) {
        useTheadPool();
    }

    private static void useTheadPool(){
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i=0;i<1000;i++){
            int finalI = i;
            pool.execute(() -> {
                String date = safeDate(finalI);
                System.out.println(date);
            });
        }
        pool.shutdown();
    }
}
