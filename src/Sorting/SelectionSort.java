package Sorting;


import Setup.Implementation;
import Setup.State;

public class SelectionSort extends Implementation {

    public SelectionSort(int[] values) {
        super(values);
    }

    @Override
    public void sort() {
        setState(State.SORTING);
        new Thread(new MyThread()).start();
    }
    private class MyThread implements Runnable {
        private void selectionSort(int[] anArrayOfInt) {
            for (int i = 0; i < anArrayOfInt.length - 1; ++i) {
                for (int j = i + 1; j < anArrayOfInt.length; ++j) {
                    if (anArrayOfInt[j] < anArrayOfInt[i]) {
                        swap(anArrayOfInt, i, j);
                    }
                }
            }
        }

        @Override
        public void run() {
            int[] values = getValues();
            selectionSort(values);
            setState(State.DONE);
        }
    }
}
