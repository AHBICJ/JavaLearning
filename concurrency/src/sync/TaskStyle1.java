package sync;


public class TaskStyle1 implements Runnable {
    static Normal number = new Normal();

    @Override
    public void run() {
        synchronized (this) {
            number.normal();
        }
    }
}


