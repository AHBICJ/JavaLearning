package threadpool;

import java.util.concurrent.RecursiveAction;

public class ForkJoinQuickSortTask extends RecursiveAction {

    final long[] array;
    final int lo;
    final int hi;

    public ForkJoinQuickSortTask(long[] array) {
        this.array = array;
        this.lo = 0;
        this.hi = array.length - 1;
    }

    public ForkJoinQuickSortTask(long[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected void compute() {
        if (lo < hi) {
            int pivot = partition(array, lo, hi);
            ForkJoinQuickSortTask left = new ForkJoinQuickSortTask(array, lo, pivot - 1);
            ForkJoinQuickSortTask right = new ForkJoinQuickSortTask(array, pivot + 1, hi);
            left.fork();
            right.fork();
            left.join();
            right.join();
        }
    }

    private int partition(long[] array, int lo, int hi) {
        long x = array[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (array[j] <= x) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, hi);
        return i + 1;
    }

    private void swap(long[] array, int i, int j) {
        if (i != j) {
            long temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}
