package main.viewPackage;

import javax.swing.*;
import java.awt.*;

public class ThreadRun extends Thread {
    private static final int NORMAL_SIZE = 50;
    private static final int LARGE_SIZE = 250;
    private static final int ANIMATION_DURATION = 1000;
    private static final int FRAME_RATE = 30;

    private ThreadPanel threadPanel;
    private int newSize = NORMAL_SIZE;

    public ThreadRun(ThreadPanel threadPanel) {
        this.threadPanel = threadPanel;
    }

    @Override
    public void run() {
        long startTime;
        boolean enlarge = true;

        while (!isInterrupted()) {
            startTime = System.currentTimeMillis();

            while (!isInterrupted() && threadPanel.isAnimating()) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                float progress = (float) elapsedTime / ANIMATION_DURATION;

                if (enlarge && progress <= 1.0f) {
                    newSize = (int) (NORMAL_SIZE + (LARGE_SIZE - NORMAL_SIZE) * progress);
                } else if (!enlarge && progress <= 1.0f) {
                    newSize = (int) (LARGE_SIZE - (LARGE_SIZE - NORMAL_SIZE) * progress);
                } else {
                    startTime = System.currentTimeMillis();
                    enlarge = !enlarge;
                    continue;
                }

                updateHeartSize(newSize);

                try {
                    Thread.sleep(1000 / FRAME_RATE);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public void updateHeartSize(int size) {
        ImageIcon resizedIcon = new ImageIcon("src/main/assets/heart.png");
        resizedIcon.setImage(resizedIcon.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
        threadPanel.getLikeLabel().setIcon(resizedIcon);
        threadPanel.revalidate();
        threadPanel.repaint();
    }
}
