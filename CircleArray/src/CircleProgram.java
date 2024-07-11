import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CircleProgram extends JPanel {
    private ArrayList<Circle> circles;

    public CircleProgram() {
        circles = new ArrayList<>();
        JButton addButton = new JButton("Add Circle");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                circles.add(new Circle());
                repaint();
            }
        });

        JButton removeButton = new JButton("Remove Circle");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!circles.isEmpty()) {
                    circles.remove(circles.size() - 1);
                    repaint();
                }
            }
        });

        setLayout(new FlowLayout());
        add(addButton);
        add(removeButton);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Circle circle : circles) {
            circle.draw(g);
        }
    }

    private static class Circle {
        private int x;
        private int y;
        private int diameter;

        public Circle() {
            // Generate random position and size for the circle
            this.x = (int) (Math.random() * 400);
            this.y = (int) (Math.random() * 400);
            this.diameter = (int) (Math.random() * 50) + 20;
        }

        public void draw(Graphics g) {
            g.setColor(Color.RED);
            g.fillOval(x, y, diameter, diameter);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Circle Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CircleProgram());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
