package sync;

class TaskStyle2 implements Runnable {
    static Normal number = new Normal();

    @Override
    public synchronized void run() {
        number.normal();
    }
}


