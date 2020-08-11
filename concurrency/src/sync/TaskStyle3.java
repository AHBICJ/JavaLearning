package sync;

class TaskStyle3 implements Runnable {
    static Normal number = new Normal();

    @Override
    public void run() {
        synchronized (TaskStyle3.class) {
            number.normal();
        }
    }
}

