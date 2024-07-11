package checkerboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class checkerboard {
    private JFrame frame;
    private JPanel boardPanel;
    private JTextArea widthTextArea;
    private JTextArea heightTextArea;

    public checkerboard() {
        frame = new JFrame("Checkerboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        boardPanel = new JPanel();
        frame.add(boardPanel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new FlowLayout());

        widthTextArea = new JTextArea(1, 10);
        heightTextArea = new JTextArea(1, 10);
        JButton generateButton = new JButton("Generate Checkerboard");

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateCheckerboard();
            }
        });

        inputPanel.add(new JLabel("Width: "));
        inputPanel.add(widthTextArea);
        inputPanel.add(new JLabel("Height: "));
        inputPanel.add(heightTextArea);
        inputPanel.add(generateButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

    private void generateCheckerboard() {
        int width = Integer.parseInt(widthTextArea.getText());
        int height = Integer.parseInt(heightTextArea.getText());

        boardPanel.removeAll();
        boardPanel.setLayout(new GridLayout(height, width));

        Color color1 = Color.BLACK;
        Color color2 = Color.WHITE;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                JPanel square = new JPanel();
                if ((row + col) % 2 == 0) {
                    square.setBackground(color1);
                } else {
                    square.setBackground(color2);
                }
                boardPanel.add(square);
            }
        }

        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new checkerboard();
            }
        });
    }
}
