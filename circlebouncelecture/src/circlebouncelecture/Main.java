package circlebouncelecture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    JButton btnStart = new JButton("Start");
    JButton btnStop = new JButton("Stop");

    int intX = 50;
    int intY = 200;
    int intXAmount = 10;
    boolean OnorOff = false;
    Timer myTimer = new Timer(100, this);
    DefineCircle Circles[] = new DefineCircle[10];

    public Main() {
        super("Button Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(btnStart);
        add(btnStop);
        btnStart.addActionListener(this);
        btnStop.addActionListener(this);
        for (int i = 0; i < Circles.length; i++) {
            Circles[i] = new DefineCircle();
        }
        for (int j = 0; j < Circles.length; j++) {
            int xpos, ypos, xspeed, yspeed;

            xpos = (int) (Math.random() * 450);
            ypos = (int) (Math.random() * 450);
            xspeed = (int) (Math.random() * 2);
            if (xspeed == 0) {
                xspeed = 10;
            } else {
                xspeed = -10;
            }
            yspeed = (int) (Math.random() * 2);
            if (yspeed == 0) {
                yspeed = 10;
            } else {
                yspeed = -10;
            }
            Circles[j].setCircle(xpos, ypos, xspeed, yspeed, "Red");
        }
    }

    public static void main(String[] args) {
        final int FRAME_WIDTH = 500;
        final int FRAME_HEIGHT = 500;

        Main frame = new Main();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        Object objSource = event.getSource();
        if (objSource == btnStop) {
            stopTheTimer();
        } else if (objSource == btnStart) {
            startTheTimer();
        }
        if (OnorOff) {
            for (int m = 0; m < Circles.length; m++) {
                if (Circles[m].xDist > getWidth() - 50 || Circles[m].xDist < 0) {
                    Circles[m].velX *= -1;
                }
                if (Circles[m].yDist > getHeight() - 50 || Circles[m].yDist < 0) {
                    Circles[m].velY *= -1;
                }
                Circles[m].xDist += Circles[m].velX;
                Circles[m].yDist += Circles[m].velY;
            }
            repaint();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int n = 0; n < Circles.length; n++) {
            g.setColor(Color.red);
            g.fillOval(Circles[n].xDist, Circles[n].yDist, 50, 50);
        }
    }

    public void startTheTimer() {
        myTimer.start();
        OnorOff = true;
    }

    public void stopTheTimer() {
        myTimer.stop();
        OnorOff = false;
    }
}