package main.viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ThreadPanel extends JPanel implements MouseListener {
    private static final int NORMAL_SIZE = 50;
    private JLabel likeLabel;
    private boolean isAnimating = false;
    private ThreadRun threadRun;

    public ThreadPanel() {
        setLayout(new BorderLayout());

        ImageIcon likeIcon = new ImageIcon("src/main/assets/heart.png");
        likeIcon.setImage(likeIcon.getImage().getScaledInstance(NORMAL_SIZE, NORMAL_SIZE, Image.SCALE_DEFAULT));
        likeLabel = new JLabel(likeIcon);

        add(likeLabel, BorderLayout.CENTER);

        likeLabel.addMouseListener(this);

        threadRun = new ThreadRun(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int iconWidth = likeLabel.getIcon().getIconWidth();
        int iconHeight = likeLabel.getIcon().getIconHeight();

        int iconX = (likeLabel.getWidth() - iconWidth) / 2;
        int iconY = (likeLabel.getHeight() - iconHeight) / 2;

        int clickX = e.getX();
        int clickY = e.getY();

        if (clickX >= iconX && clickX <= iconX + iconWidth && clickY >= iconY && clickY <= iconY + iconHeight) {
            if (!isAnimating) {
                isAnimating = true;
                threadRun = new ThreadRun(this);
                threadRun.start();
            } else {
                isAnimating = false;
                threadRun.interrupt();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public JLabel getLikeLabel() {
        return likeLabel;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public void setAnimating(boolean isAnimating) {
        this.isAnimating = isAnimating;
    }
}
