package Frames;

import Setup.Sorter;
import Setup.State;

import javax.swing.*;
import java.awt.*;


public class SortingPanel extends JPanel {
    private Sorter sorter;
    private Thread sortingThread;

    SortingPanel() {
        setBackground(Color.BLACK);

        sortingThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                    SwingUtilities.invokeLater(() -> repaint());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        sortingThread.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int[] arr = getSorter().getValues();

        Color normalColor;

        if(getSorter().getState() == State.DONE){
            normalColor = new Color(12, 120, 30);
        } else {
            normalColor = new Color(12,120,160);
        }
        Color highlightedColor = new Color(245,10,60);

        drawBars(g2d,arr,width,height,normalColor,highlightedColor);
    }
    public void drawBars(Graphics2D g2d,int[] arr,int width, int height, Color normalColor, Color highlightedColor){
        int barWidth = width / arr.length;
        int barHeight;

        for (int i = 0; i < arr.length; i++) {
            g2d.setColor(normalColor);
            barHeight = (int) ((double) arr[i] / arr.length * height);
            int x = i * barWidth;
            int y = height - barHeight;

            if(getSorter().isActiveIndex(i) && getSorter().getState() != State.DONE){
                g2d.setColor(highlightedColor);
            }

            g2d.fillRect(x, y, barWidth, barHeight);
            g2d.setColor(Color.WHITE);
            g2d.drawRect(x, y, barWidth, barHeight);
        }
    }

    public Sorter getSorter() {
        return sorter;
    }

    public void setSorter(Sorter sorter) {
        if(this.sorter != sorter && sorter != null) this.sorter = sorter;
        repaint();
    }
}
