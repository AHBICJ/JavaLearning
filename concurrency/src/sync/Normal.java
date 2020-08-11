package sync;

public class Normal {

    int i;

    static int times = 10000;
    public void normal() {
        for (int j = 0; j < times; j++) {
            i++;
        }
    }

}
