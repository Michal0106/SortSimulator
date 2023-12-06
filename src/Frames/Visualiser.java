package Frames;

import Sorting.*;
import Sorting.Sorting;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class Visualiser {
    public static String length;
    private JFrame frame;
    public static int sortingSpeed;

    private Visualiser() {
        JPanel panel;
        JButton q,w,r,s,t,u,e,x,lengthButton;
        JSlider jSlider;

        frame = new JFrame();
        frame.setSize(510, 390);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        q = new JButton("Bubble");
        w = new JButton("Merge");
        r = new JButton("Quick");
        s = new JButton("Insertion");
        t = new JButton("Selection");
        u = new JButton("Bogo");
        e = new JButton("Tim");
        x = new JButton("Cocktail");

        lengthButton = new JButton("Change size of array");
        jSlider = new JSlider(1,100,50);
        jSlider.setMajorTickSpacing(10);

        q.setBounds(50, 10, 100, 50);
        w.setBounds(50, 90, 100, 50);
        r.setBounds(350, 10, 100, 50);
        s.setBounds(350, 90, 100, 50);
        t.setBounds(50, 170, 100, 50);
        u.setBounds(350, 170, 100, 50);
        e.setBounds(200, 10, 100, 50);
        x.setBounds(200, 90, 100, 50);

        lengthButton.setBounds(180,230,150,50);
        jSlider.setBounds(100,290,300,50);

        jSlider.setPaintLabels(true);

        Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
        table.put (1, new JLabel("1 (fast)"));
        table.put (50, new JLabel("50"));
        table.put (100, new JLabel("100 (slow)"));

        jSlider.setLabelTable(table);


        q.addActionListener(v -> {
            SortingPanel p = new SortingPanel();
            BubbleSort bubbleSort = new BubbleSort(Sorting.getValues());
            p.setSorter(bubbleSort);
            frame = returnFrame(p);
            bubbleSort.sort();
        });

        w.addActionListener(v -> {
            SortingPanel p = new SortingPanel();
            MergeSort mergeSort = new MergeSort(Sorting.getValues());
            p.setSorter(mergeSort);
            frame = returnFrame(p);
            mergeSort.sort();
        });

        r.addActionListener(v -> {
            SortingPanel p = new SortingPanel();
            QuickSort quickSort = new QuickSort(Sorting.getValues());
            p.setSorter(quickSort);
            frame = returnFrame(p);
            quickSort.sort();
        });

        s.addActionListener(v -> {
            SortingPanel p = new SortingPanel();
            InsertionSort insertionSort = new InsertionSort(Sorting.getValues());
            p.setSorter(insertionSort);
            frame = returnFrame(p);
            insertionSort.sort();
        });

        t.addActionListener(v -> {
            SortingPanel p = new SortingPanel();
            SelectionSort selectionSort = new SelectionSort(Sorting.getValues());
            p.setSorter(selectionSort);
            frame = returnFrame(p);
            selectionSort.sort();
        });

        u.addActionListener(v -> {
            SortingPanel p = new SortingPanel();
            BogoSort bogoSort = new BogoSort(Sorting.getValues());
            p.setSorter(bogoSort);
            frame = returnFrame(p);
            bogoSort.sort();
        });

        e.addActionListener(v -> {
            SortingPanel p = new SortingPanel();
            TimSort timSort = new TimSort(Sorting.getValues());
            p.setSorter(timSort);
            frame = returnFrame(p);
            timSort.sort();
        });

        x.addActionListener(v -> {
            SortingPanel p = new SortingPanel();
            CocktailSort cocktailSort = new CocktailSort(Sorting.getValues());
            p.setSorter(cocktailSort);
            frame = returnFrame(p);
            cocktailSort.sort();
        });

        lengthButton.addActionListener(v ->
                length = JOptionPane.showInputDialog(frame, "Enter number of array elements[10 - 700].\nDefault elements are 270")
        );

        jSlider.addChangeListener(v -> {
            sortingSpeed = jSlider.getValue();
        });

        panel.add(q);
        panel.add(w);
        panel.add(r);
        panel.add(s);
        panel.add(t);
        panel.add(u);
        panel.add(e);
        panel.add(x);

        panel.add(lengthButton);
        panel.add(jSlider);

        panel.setBackground(new Color(33, 97, 140));

        frame.add(panel);
        frame.setVisible(true);
        length = JOptionPane.showInputDialog(frame, "Enter number of array elements[10 - 700].\nDefault elements are 270");
        sortingSpeed = jSlider.getValue();
        try {
            while (Integer.parseInt(length) > 700 || Integer.parseInt(length) < 0) {
                JOptionPane.showMessageDialog(frame, "Please choose the number in the given limit!");
                length = JOptionPane.showInputDialog(frame, "Enter number of array elements[10 - 700].\nDefault elements are 270");
            }
        } catch (NumberFormatException p) {
            p.getStackTrace();
        }
    }

    public static void main(String... s) {
        new Visualiser();
    }

    private JFrame returnFrame(JPanel panel) {
        JFrame frame = new JFrame("Sorting");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
}