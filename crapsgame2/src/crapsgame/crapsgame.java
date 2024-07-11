package crapsgame;



import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.text.DecimalFormat;

import java.util.Random;



public class crapsgame extends JFrame {

    private JButton rollButton;

    private JButton autoRollButton;

    private JTextArea resultTextArea;

    private JLabel numberOfGamesLabel;

    private JTextField numberOfGamesTextField;

    private JButton startGameButton;

    private Calculation game;

    private int numberOfGamesToPlay = 1;

    private int gamesPlayed = 0;

    private int wins = 0;

    private int losses = 0;



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



                // Check if the game is won and reset if necessary

                if (game.hasWon()) {

                    game.reset();

                }

            }

        });



        autoRollButton = new JButton("Auto-Roll");

        autoRollButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                autoRoll();

            }

        });



        resultTextArea = new JTextArea();

        resultTextArea.setEditable(false);



        numberOfGamesLabel = new JLabel("Number of Games:");

        numberOfGamesTextField = new JTextField(5);

        startGameButton = new JButton("Start Game");

        startGameButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    numberOfGamesToPlay = Integer.parseInt(numberOfGamesTextField.getText());

                } catch (NumberFormatException ex) {

                    // Handle invalid input

                    numberOfGamesToPlay = 0;

                }

                autoRoll();

            }

        });



        JScrollPane scrollPane = new JScrollPane(resultTextArea);



        JPanel buttonPanel = new JPanel();

        buttonPanel.add(rollButton);

        buttonPanel.add(autoRollButton);



        JPanel settingsPanel = new JPanel();

        settingsPanel.add(numberOfGamesLabel);

        settingsPanel.add(numberOfGamesTextField);

        settingsPanel.add(startGameButton);



        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(settingsPanel, BorderLayout.SOUTH);

    }



    public int rollDice() {

        Random rand = new Random();

        return rand.nextInt(6) + 1 + rand.nextInt(6) + 1;

    }



    private void autoRoll() {

        autoRollButton.setEnabled(false);

        resultTextArea.setText("");

        wins = 0; // Initialize wins

        losses = 0; // Initialize losses

        gamesPlayed = 0;



        while (gamesPlayed < numberOfGamesToPlay) {

            int sum = rollDice();

            game.play(sum);

            gamesPlayed++;



            // Update wins and losses based on the game outcome

            if (game.hasWon()) {

                wins++;

            } else {

                losses++;

            }

        }



        autoRollButton.setEnabled(true);

        double experimentalProbability = ((double) wins / numberOfGamesToPlay) * 100;

        DecimalFormat df = new DecimalFormat("0.00"); // Format to two decimal places

        resultTextArea.append("All games played. Experimental probability of winning Craps: " + df.format(experimentalProbability) + "%\n");

    }



    private void displayResult(int sum) {

        if (game.hasWon()) {

            resultTextArea.setText("You rolled " + sum + ". You win!\n");

        } else {

            if (!game.isPointPhase()) {

                if (sum == 7 || sum == 11) {

                    resultTextArea.setText("You rolled " + sum + ". You win!\n");

                } else if (sum == 2 || sum == 3 || sum == 12) {

                    resultTextArea.setText("You rolled " + sum + ". You lose (Craps).\n");

                } else {

                    resultTextArea.setText("You rolled " + sum + ". The point is " + sum + ". Keep rolling.\n");

                }

            } else {

                if (sum == game.getPoint()) {

                    resultTextArea.setText("You rolled " + sum + ". You win by making your point!\n");

                } else if (sum == 7) {

                    resultTextArea.setText("You rolled " + sum + ". You lose.\n");

                } else {

                    resultTextArea.setText("You rolled " + sum + ". The point is " + game.getPoint() + ". Keep rolling.\n");

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