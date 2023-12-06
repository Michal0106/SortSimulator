package Setup;

public interface Sorter {

    int[] getValues();

    State getState();

    void setState(State state);

    void sort();

    boolean isActiveIndex(int index);
}
