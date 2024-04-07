package Sorting;

import Frames.Visualiser;

import java.util.Random;

public final class Sorting {
    static int length;

    public Sorting() {
    }
    public static int[] getValues(){

        if(Visualiser.length.equals("")) length = 270;
        else length = Integer.parseInt(Visualiser.length);

        if (length < 10 || length > 700) {
            length = 270;
        }
        return new Random().ints(1, length+1)
                .limit(length)
                .toArray();
    }
}
