package Sorting;

import Setup.Implementation;
import Setup.State;

public class BogoSort extends Implementation {
    public BogoSort(int[] values) {
        super(values);
    }

    @Override
    public void sort() {
        setState(State.SORTING);
        new Thread(new MyThread()).start();
    }

    class MyThread implements Runnable{

        void bogoSort(int[] a)
        {
            while (isSorted(a) == false)
                shuffle(a);
        }

        void shuffle(int[] a)
        {
            for (int i = 0; i < a.length; i++)
                swap(a, i, (int)(Math.random() * i));
        }
        boolean isSorted(int[] a)
        {
            for (int i = 1; i < a.length; i++)
                if (a[i] < a[i - 1])
                    return false;
            return true;
        }

        @Override
        public void run() {
            int[] values = getValues();
            bogoSort(values);
            setState(State.DONE);
        }
    }
}
