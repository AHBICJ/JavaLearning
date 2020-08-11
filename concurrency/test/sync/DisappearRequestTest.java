package sync;

import org.junit.Test;

import static org.junit.Assert.*;

public class DisappearRequestTest {

    @Test
    public void test1() throws InterruptedException {
        TaskStyle1 task = new TaskStyle1();
        Thread t1 = new Thread(task);
        Thread t2  = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertEquals(Normal.times * 2, TaskStyle1.number.i);
    }

    @Test
    public void test2() throws InterruptedException {
        TaskStyle2 task = new TaskStyle2();
        Thread t1 = new Thread(task);
        Thread t2  = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertEquals(Normal.times * 2, TaskStyle2.number.i);
    }

    @Test
    public void test3() throws InterruptedException {
        TaskStyle3 task1 = new TaskStyle3();
        TaskStyle3 task2 = new TaskStyle3();
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertEquals(Normal.times * 2, TaskStyle3.number.i);
    }

    @Test
    public void tes(){
        System.out.println("ABCD".replaceAll("abc","abcc"));
    }
}