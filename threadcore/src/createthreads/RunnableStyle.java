package createthreads;


// 用实现Runnable方式实现线程
public class RunnableStyle {
    public static void main(String[] args) {
        // 使用Thead 传递Runnable 的那个构造函数
        //  public Thread(Runnable target) {
        //      this(null, target, "Thread-" + nextThreadNum(), 0);
        //  }
        Thread thread = new Thread(()->{
            // @FunctionalInterface
            // public interface Runnable
            System.out.println("implement Runnable");
        });
        // Causes this thread to begin execution; the Java Virtual Machine
        // calls the {@code run} method of this thread.
        thread.start();

        //    public void run() {
        //        if (target != null) {
        //            target.run();
        //        }
        //    }


    }

}
