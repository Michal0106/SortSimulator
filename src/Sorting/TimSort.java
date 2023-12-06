package Sorting;

import Frames.Visualiser;
import Setup.Implementation;
import Setup.State;

public class TimSort extends Implementation {
    public TimSort(int[] values) {
        super(values);
    }

    @Override
    public void sort() {
        setState(State.SORTING);
        new Thread(new MyThread()).start();
    }

    @Override
    public void setActiveIndexes(int index1, int index2) {
        super.setActiveIndexes(index1, index2);

        try {
            Thread.sleep(Visualiser.sortingSpeed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    class MyThread implements Runnable{
        static int MIN_MERGE = 32;

        public int minRunLength(int n)
        {
            assert n >= 0;

            int r = 0;
            while (n >= MIN_MERGE) {
                r |= (n & 1);
                n >>= 1;
            }
            return n + r;
        }
        public void insertionSort(int[] arr, int left,
                                         int right)
        {
            for (int i = left + 1; i <= right; i++) {
                int temp = arr[i];
                int j = i - 1;
                while (j >= left && arr[j] > temp) {
                    swap(arr,j,j+1);
                    j--;
                }
            }
        }
        public void merge(int[] arr, int l, int m, int r)
        {
            int len1 = m - l + 1, len2 = r - m;
            int[] left = new int[len1];
            int[] right = new int[len2];
            for (int x = 0; x < len1; x++) {
                left[x] = arr[l + x];
            }
            for (int x = 0; x < len2; x++) {
                right[x] = arr[m + 1 + x];
            }

            int i = 0;
            int j = 0;
            int k = l;

            while (i < len1 && j < len2) {
                if (left[i] <= right[j]) {
                    arr[k] = left[i];
                    setActiveIndexes(k,i);
                    i++;
                }
                else {
                    arr[k] = right[j];
                    setActiveIndexes(k,j);
                    j++;
                }
                k++;
            }

            while (i < len1) {
                arr[k] = left[i];
                k++;
                i++;
            }

            while (j < len2) {
                arr[k] = right[j];
                k++;
                j++;
            }
        }

        public void timSort(int[] arr, int n)
        {
            int minRun = minRunLength(MIN_MERGE);

            for (int i = 0; i < n; i += minRun) {
                insertionSort(
                        arr, i,
                        Math.min((i + MIN_MERGE - 1), (n - 1)));
            }


            for (int size = minRun; size < n; size = 2 * size) {

                for (int left = 0; left < n; left += 2 * size) {

                    int mid = left + size - 1;
                    int right = Math.min((left + 2 * size - 1), (n - 1));

                    if (mid < right)
                        merge(arr, left, mid, right);
                }
            }
        }

        @Override
        public void run() {
            int[] values = getValues();
            timSort(values, values.length);
            setState(State.DONE);
        }
    }
}
