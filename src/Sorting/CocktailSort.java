package Sorting;

import Setup.Implementation;
import Setup.State;

public class CocktailSort extends Implementation {
    public CocktailSort(int[] values) {
        super(values);
    }

    @Override
    public void sort() {
        setState(State.SORTING);
        new Thread(new MyThread()).start();
    }

    class MyThread implements Runnable{

        void cocktailSort(int arr[])
        {
            boolean swapped = true;
            int start = 0;
            int end = arr.length;

            while (swapped == true)
            {
                swapped = false;
                for (int i = start; i < end - 1; ++i)
                {
                    if (arr[i] > arr[i + 1]) {
                        swap(arr,i,i+1);
                        swapped = true;
                    }
                }

                if (swapped == false)
                    break;

                swapped = false;
                end = end - 1;

                for (int i = end - 1; i >= start; i--)
                {
                    if (arr[i] > arr[i + 1])
                    {
                        swap(arr,i,i+1);
                        swapped = true;
                    }
                }
                start = start + 1;
            }
        }

        @Override
        public void run() {
            int[] values = getValues();
            cocktailSort(values);
            setState(State.DONE);
        }
    }
}
