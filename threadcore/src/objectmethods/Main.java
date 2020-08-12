package objectmethods;

public class Main {
    class Producer implements Runnable {
        private EventStorage storage;

        public Producer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    storage.put();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        private EventStorage storage;

        public Consumer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    storage.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.realMain();
    }

    private void realMain() {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        Thread pThread = new Thread(producer);
        Thread pConsumer = new Thread(consumer);
        pThread.start();
        pConsumer.start();
    }
}

