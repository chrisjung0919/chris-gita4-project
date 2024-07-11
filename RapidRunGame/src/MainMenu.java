import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private int selectedStage; // Store the selected stage
    private Color selectedColor = Color.GREEN; // Store the selected color, default is green

    public MainMenu() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        setBackground(Color.BLACK); // Set background color for the main menu

        // Create title label with customized font and color
        JLabel titleLabel = new JLabel("RAPID RUN");
        titleLabel.setFont(new Font("Impact", Font.BOLD, 80)); // Larger font size
        titleLabel.setForeground(Color.WHITE); // Custom title color
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create panel for the player square
        JPanel playerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw player square with selected color
                int PLAYER_SIZE = 100; // Increase player square size
                int x = (getWidth() - PLAYER_SIZE) / 2;
                int y = (getHeight() - PLAYER_SIZE) / 2;
                g.setColor(selectedColor); // Use selected color
                g.fillRect(x, y, PLAYER_SIZE, PLAYER_SIZE);

                // Draw smiling face on the player square
                int circleSize = PLAYER_SIZE / 6; // Increase circle size
                int offsetX = x; // X offset for circles
                int offsetY = y; // Y offset for circles

                // Draw first circle
                g.setColor(Color.BLACK);
                g.fillOval(offsetX + PLAYER_SIZE / 4, offsetY + PLAYER_SIZE / 4, circleSize, circleSize);

                // Draw second circle
                g.fillOval(offsetX + PLAYER_SIZE * 3 / 4 - circleSize, offsetY + PLAYER_SIZE / 4, circleSize, circleSize);

                // Draw smile (upside-down semicircle)
                g.fillArc(offsetX + PLAYER_SIZE / 4, offsetY + PLAYER_SIZE / 2, PLAYER_SIZE / 2, PLAYER_SIZE / 3, 0, -180);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 300); // Increase the preferred size of the player panel
            }
        };

        // Add components to panel
        add(titleLabel, BorderLayout.NORTH);
        add(playerPanel, BorderLayout.CENTER);
        add(createPlayButton(), BorderLayout.SOUTH);
        add(createCustomizationButton(), BorderLayout.EAST); // Add customization button
    }

    private JButton createPlayButton() {
        JButton playButton = new JButton("PLAY");
        playButton.setFont(new Font("Impact", Font.BOLD, 40));
        playButton.setForeground(Color.black);
        playButton.setBackground(Color.GREEN);
        playButton.setFocusPainted(false); // Remove focus border
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the selected stage and switch to stage selection screen
                selectedStage = 1; // For example, set stage 1 initially
                removeAll();
                add(new StageSelection());
                revalidate();
                repaint();
            }
        });
        return playButton;
    }

    // Create customization button
    private JButton createCustomizationButton() {
        JButton customizationButton = new JButton("Customization");
        customizationButton.setFont(new Font("Impact", Font.BOLD, 20));
        customizationButton.setForeground(Color.black);
        customizationButton.setBackground(Color.BLUE); // Customize button color
        customizationButton.setFocusPainted(false); // Remove focus border
        customizationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create customization screen with a listener for color selection
                removeAll();
                CustomizationScreen customizationScreen = new CustomizationScreen(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Return to main menu after selecting color
                        removeAll();
                        add(new MainMenu());
                        revalidate();
                        repaint();
                    }
                }, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Switch to stage selection screen after selecting color
                        removeAll();
                        add(new StageSelection());
                        revalidate();
                        repaint();
                    }
                });
                add(customizationScreen);
                revalidate();
                repaint();
            }
        });
        return customizationButton;
    }

    // Inner class for stage selection screen
    private class StageSelection extends JPanel {
        public StageSelection() {
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setLayout(new GridLayout(3, 1));

            // Create stage buttons with custom appearance
            JButton stage1Button = createStyledButton("STAGE 1", Color.YELLOW);
            JButton stage2Button = createStyledButton("STAGE 2", Color.ORANGE);
            JButton stage3Button = createStyledButton("STAGE 3", Color.RED);

            // Add action listeners to stage buttons
            stage1Button.addActionListener(e -> startGame(1));
            stage2Button.addActionListener(e -> startGame(2)); // Listener for Stage 2 button
            stage3Button.addActionListener(e -> startGame(3));

            // Add stage buttons to panel
            add(stage1Button);
            add(stage2Button);
            add(stage3Button);
        }

        private void startGame(int stage) {
            // Switch to RapidRunGame with the selected stage and player color
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(MainMenu.this);
            frame.getContentPane().removeAll();
            RapidRunGame gamePanel = new RapidRunGame(stage);
            gamePanel.setPlayerColor(selectedColor); // Pass the selected color
            frame.getContentPane().add(gamePanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            gamePanel.requestFocusInWindow(); // Request focus for the game panel
        }

        private JButton createStyledButton(String text, Color bgColor) {
            JButton button = new JButton(text);
            button.setFont(new Font("Impact", Font.BOLD, 30));
            button.setForeground(Color.BLACK);
            button.setBackground(bgColor);
            button.setFocusPainted(false); // Remove focus border
            return button;
        }
    }

 // Inner class for customization screen
    private class CustomizationScreen extends JPanel {
        public CustomizationScreen(ActionListener colorSelectionListener, ActionListener selectListener) {
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setLayout(new BorderLayout());

            JPanel colorPanel = new JPanel();
            colorPanel.setLayout(new GridLayout(3, 3));

            // Create buttons for color selection
            JButton yellowButton = createColorButton(Color.YELLOW, colorSelectionListener);
            JButton redButton = createColorButton(Color.RED, colorSelectionListener);
            JButton blueButton = createColorButton(Color.BLUE, colorSelectionListener);
            JButton whiteColor = createColorButton(new Color(150, 7, 245), colorSelectionListener);
            JButton orangeButton = createColorButton(new Color(2, 82, 10), colorSelectionListener); // New color button: ORANGE
            JButton purpleButton = createColorButton(new Color(2, 241, 245), colorSelectionListener); // New color button: PURPLE

            // Add color selection buttons to panel
            colorPanel.add(yellowButton);
            colorPanel.add(redButton);
            colorPanel.add(blueButton);
            colorPanel.add(whiteColor);
            colorPanel.add(orangeButton); // Add orange button to panel
            colorPanel.add(purpleButton); // Add purple button to panel

            // Add color selection panel to the center
            add(colorPanel, BorderLayout.CENTER);

            // Create select button
            JButton selectButton = new JButton("Select");
            selectButton.setFont(new Font("Impact", Font.BOLD, 30));
            selectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Call selectListener to switch to stage selection screen
                    selectListener.actionPerformed(e);
                }
            });
            // Add select button to the bottom
            add(selectButton, BorderLayout.SOUTH);
        }

        private JButton createColorButton(Color color, ActionListener colorSelectionListener) {
            JButton button = new JButton();
            button.setBackground(color);
            button.setPreferredSize(new Dimension(150, 150));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Set the selected color
                    selectedColor = color;
                    // Repaint the player square panel to reflect the new color
                    repaint();
                    // Debugging output
                    System.out.println("Selected color: " + selectedColor);
                }
            });
            return button;
        }
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rapid Run");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.getContentPane().add(new MainMenu());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
