package Sorting;

import Setup.Implementation;
import Setup.State;
public class BubbleSort extends Implementation {

    public BubbleSort(int[] values) {
        super(values);
    }

    @Override
    public void sort() {
        setState(State.SORTING);
        new Thread(new MyThread()).start();
    }

    public class MyThread implements Runnable{

        private int[] values;

        public MyThread() {
        }
        public void bubbleSort(int[] arr) {
            int i, j, n = arr.length;
            boolean swapped;

            for (i = 0; i < n - 1; i++) {
                swapped = false;
                for (j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        swap(arr,j,j+1);
                        swapped = true;
                    }
                }
                if (!swapped) break;
            }
        }
        @Override
        public void run() {
            this.values = getValues();
            bubbleSort(values);
            setState(State.DONE);
        }
    }
}
