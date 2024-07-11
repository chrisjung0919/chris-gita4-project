package crapsgame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class crapsgame extends JFrame {
    private JButton rollButton;
    private JTextArea resultTextArea;
    private Calculation game;

    public crapsgame() {
        setTitle("Craps Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        game = new Calculation();

        rollButton = new JButton("Roll Dice");
        rollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int sum = rollDice();
                game.play(sum);
                displayResult(sum);
            }
        });

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rollButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1 + rand.nextInt(6) + 1;
    }

    private void displayResult(int sum) {
        if (game.hasWon()) {
            resultTextArea.append("You rolled " + sum + ". You win!\n");
        } else {
            if (!game.isPointPhase()) {
                if (sum == 7 || sum == 11) {
                    resultTextArea.append("You rolled " + sum + ". You win!\n");
                } else if (sum == 2 || sum == 3 || sum == 12) {
                    resultTextArea.append("You rolled " + sum + ". You lose (Craps).\n");
                } else {
                    resultTextArea.append("You rolled " + sum + ". The point is " + sum + ". Keep rolling.\n");
                }
            } else {
                if (sum == game.getPoint()) {
                    resultTextArea.append("You rolled " + sum + ". You win by making your point!\n");
                } else if (sum == 7) {
                    resultTextArea.append("You rolled " + sum + ". You lose.\n");
                } else {
                    resultTextArea.append("You rolled " + sum + ". The point is " + game.getPoint() + ". Keep rolling.\n");
                }
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                crapsgame crapsGame = new crapsgame();
                crapsGame.setVisible(true);
            }
        });
    }
}