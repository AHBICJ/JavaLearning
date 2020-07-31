package threadpool;

import org.junit.Test;

import static org.junit.Assert.*;

public class EveryTaskOneThreadTest {

    @Test
    public void realMain() {
        EveryTaskOneThread main = new EveryTaskOneThread();
        main.realMain();
    }

}