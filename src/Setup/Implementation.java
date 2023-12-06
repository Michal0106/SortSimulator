package Setup;

import Frames.Visualiser;

import java.util.ArrayList;
import java.util.List;

public abstract class Implementation implements Sorter {
    private int[] values;
    private State state = State.WAITING;
    private List<Integer> activeIndexes;

    public Implementation(int[] values) {
        this.values = values;
        this.activeIndexes = new ArrayList<>();
    }

    @Override
    public int[] getValues() {
        return values;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        if(this.state != state){
            this.state = state;
        }
    }
    @Override
    public boolean isActiveIndex(int index) {
        return activeIndexes.contains(index);
    }
    public void setActiveIndexes(int index1, int index2){
        activeIndexes.clear();
        activeIndexes.add(index1);
        activeIndexes.add(index2);
    }
    public void swap(int[] arr, int index1, int index2){
        setActiveIndexes(index1,index2);

        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;

        try {
            Thread.sleep(Visualiser.sortingSpeed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
