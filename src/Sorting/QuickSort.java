package Sorting;

import Setup.Implementation;
import Setup.State;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends Implementation {

    public QuickSort(int[] values) {
        super(values);
    }
    @Override
    public void sort() {
        setState(State.SORTING);
        new Thread(new MyThread()).start();
    }
    private class MyThread implements Runnable {
        private int getRandom(int lower, int upper) {
            return ThreadLocalRandom.current().nextInt(lower, upper);
        }

        private int partition(int[] a, int start, int end, int pIndex) {
            int pivot = a[pIndex];
            swap(a, pIndex, end);
            int i = start - 1;
            for (int j = start; j <= end - 1; j++) {
                if (a[j] < pivot) {
                    i += 1;
                    swap(a, i, j);
                }
            }
            swap(a, i + 1, end);
            return i + 1;
        }
        private void qSort(int[] a, int l, int r) {
            if (l < r) {
                int pIndex = getRandom(l, r);
                int pivot = partition(a, l, r, pIndex);
                qSort(a, l, pivot - 1);
                qSort(a, pivot + 1, r);
            }
        }

        @Override
        public void run() {
            int[] values = getValues();
            qSort(values, 0, values.length - 1);
            setState(State.DONE);
        }
    }
}
