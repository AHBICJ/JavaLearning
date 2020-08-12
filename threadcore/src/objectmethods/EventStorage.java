package objectmethods;

import java.util.Date;
import java.util.LinkedList;

public class EventStorage {
    private int maxSize;
    private LinkedList<Date> storage;
    private int consumed = 0;
    private int generated = 0;

    public EventStorage() {
        this.maxSize = 10;
        this.storage = new LinkedList<Date>();
    }

    public synchronized void put() throws InterruptedException {
        while (storage.size() == maxSize) {
            wait();
        }
        storage.add(new Date());
        generated++;
        System.out.println("new added, " + generated +
                " generated in total, current size: " + storage.size());
        notify();
    }

    public synchronized void take() throws InterruptedException {
        while (storage.isEmpty()) {
            wait();
        }
        consumed++;
        System.out.println(storage.poll() + " has been taken, " + consumed +
                " consumed in total, current size: " + storage.size());
        notify();
    }
}
