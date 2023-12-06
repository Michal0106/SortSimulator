package Sorting;

import Setup.Implementation;
import Setup.State;

public class InsertionSort extends Implementation {
    public InsertionSort(int[] values) {
        super(values);
    }

    @Override
    public void sort() {
        setState(State.SORTING);
        new Thread(new MyThread()).start();
    }

    class MyThread implements Runnable{
        public void insertionSort(int arr[]){
            int n = arr.length;
            for (int i = 1; i < n; ++i) {
                int key = arr[i];
                int j = i - 1;
                while (j >= 0 && arr[j] > key) {
                    swap(arr,j,j+1);
                    j = j - 1;
                }
            }
        }
        @Override
        public void run() {
            int[] values = getValues();
            insertionSort(values);
            setState(State.DONE);
        }
    }
}
