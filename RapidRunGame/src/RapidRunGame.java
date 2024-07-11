import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RapidRunGame extends JPanel implements KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 50;
    private static final int GROUND_Y = HEIGHT - PLAYER_SIZE - 50; // Ground position

    private int playerY = GROUND_Y;
    private boolean isJumping = false;
    private int jumpVelocity = 0;
    private int score = 0;
    private int hp = 5; // Health points
    private List<Heart> hearts; // List to store hearts
    private boolean doubleSpikes = false; // Default value is false for Stage 1

    private List<Spike> spikes;
    private List<Coin> coins;
    private Random random;

    private Background background; // Background object
    
    private Color playerColor = Color.GREEN; // Default player color

    public RapidRunGame(int stage) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        spikes = new ArrayList<>();
        coins = new ArrayList<>();
        hearts = new ArrayList<>();
        random = new Random();
        background = new Background(stage, this); // Pass the stage number to the Background constructor

        if (stage == 2) {
            doubleSpikes = true; // Set to true for Stage 2
        }
        
        // Initialize hearts
        for (int i = 0; i < hp; i++) {
            hearts.add(new Heart(i * 40 + 10, 10)); // Adjust heart position
        }

        // Initialize game based on stage
        initializeStage(stage);

        // Game loop
        Timer timer = new Timer(1000 / 60, e -> {
            update();
            repaint();
        });
        timer.start();
        
        this.playerColor = playerColor; // Store the player color
    }
    
    public void setPlayerColor(Color color) {
        playerColor = color;
    }

    private void initializeStage(int stage) {
        switch (stage) {
            case 1:
                initializeStage1();
                break;
            case 2:
                initializeStage2();
                break;
            case 3:
                initializeStage3();
                break;

            default:
                System.out.println("Invalid stage number.");
        }
    }

    private void initializeStage1() {
        // Timer for spawning spikes
        Timer spikeTimer = new Timer(1000, e -> {
            // Form single spikes in Stage 1
            spikes.add(new Spike(WIDTH, GROUND_Y - Spike.SIZE + 40));
        });
        spikeTimer.start();

        // Timer for spawning coins
        Timer coinTimer = new Timer(13000, e -> {
            coins.add(new Coin(WIDTH, GROUND_Y)); // Align coin with ground
        });
        coinTimer.start();
    }
    
    private void initializeStage2() {
        // Timer for spawning spikes
        Timer spikeTimer = new Timer(750, e -> { // Decrease the delay to make spikes appear more frequently
            // Form double spikes in Stage 2
            if (doubleSpikes) {
                // Add first spike of the pair with normal speed
                spikes.add(new Spike(WIDTH, GROUND_Y - Spike.SIZE + 40, 9));
                // Add second spike of the pair with increased speed
                spikes.add(new Spike(WIDTH + Spike.SIZE, GROUND_Y - Spike.SIZE + 40, 12));
            } else {
                spikes.add(new Spike(WIDTH, GROUND_Y - Spike.SIZE + 40)); // Form single spikes as usual
            }
        });
        spikeTimer.start();
        
        // Timer for spawning coins
        Timer coinTimer = new Timer(13000, e -> {
            coins.add(new Coin(WIDTH, GROUND_Y)); // Align coin with ground
        });
        coinTimer.start();
    }
    private void initializeStage3() {
        // Timer for spawning spikes
        Timer spikeTimer = new Timer(750, e -> { // Keep the delay similar to Stage 2
            // Form triple spikes occasionally in Stage 3
            if (doubleSpikes) {
                // Form double spikes most of the time
                spikes.add(new Spike(WIDTH, GROUND_Y - Spike.SIZE + 40, 12)); // Increased speed
                spikes.add(new Spike(WIDTH + Spike.SIZE, GROUND_Y - Spike.SIZE + 40, 15)); // Increased speed
            } else {
                // Occasionally spawn triple spikes
                spikes.add(new Spike(WIDTH, GROUND_Y - Spike.SIZE + 40, 12)); // Increased speed
                spikes.add(new Spike(WIDTH + Spike.SIZE, GROUND_Y - Spike.SIZE + 40, 15)); // Increased speed
                spikes.add(new Spike(WIDTH + 2 * Spike.SIZE, GROUND_Y - Spike.SIZE + 40, 18)); // Even higher speed
            }
        });
        spikeTimer.start();
        
        // Timer for spawning coins
        Timer coinTimer = new Timer(13000, e -> { // Keep the coin spawn time similar to Stage 2
            coins.add(new Coin(WIDTH, GROUND_Y)); // Align coin with ground
        });
        coinTimer.start();
    }


    private void update() {
        // Update player position based on jump
        if (isJumping) {
            playerY -= jumpVelocity;
            jumpVelocity -= 1.07;

            if (playerY >= GROUND_Y) { // Player has landed
                playerY = GROUND_Y;
                isJumping = false;
            }
        }

        // Update spike positions
        for (Spike spike : spikes) {
            spike.move();
            if (spike.getX() < -Spike.SIZE) {
                // Spike moved off-screen, remove it
                spikes.remove(spike);
                break;
            }
        }

        // Update coin positions
        for (Coin coin : coins) {
            coin.move();
            if (coin.getX() < -Coin.SIZE) {
                // Coin moved off-screen, remove it
                coins.remove(coin);
                break;
            }
        }

        // Check for collisions
        checkCollisions();
    }

    private void checkCollisions() {
        Rectangle playerRect = new Rectangle(WIDTH / 2 - PLAYER_SIZE / 2, playerY, PLAYER_SIZE, PLAYER_SIZE);

        // Check collisions with spikes
        Iterator<Spike> spikeIterator = spikes.iterator();
        while (spikeIterator.hasNext()) {
            Spike spike = spikeIterator.next();
            Rectangle spikeRect = new Rectangle(spike.getX(), spike.getY(), Spike.SIZE, Spike.SIZE);
            if (playerRect.intersects(spikeRect)) {
                // Collision with spike detected, reduce HP
                hp--;
                hearts.remove(hearts.size() - 1); // Remove a heart from the display
                if (hp <= 0) {
                    System.out.println("Game Over!");
                    System.exit(0); // Game over logic
                }
                spikeIterator.remove(); // Remove the spike
            }
        }

        // Check collisions with coins
        Iterator<Coin> coinIterator = coins.iterator();
        while (coinIterator.hasNext()) {
            Coin coin = coinIterator.next();
            Rectangle coinRect = coin.getBounds();
            if (playerRect.intersects(coinRect)) {
                // Collision with coin detected, increment score and remove coin
                score++;
                System.out.println("Score: " + score);
                coinIterator.remove();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw the background
        background.draw(g, WIDTH, HEIGHT);

        // Draw player square (green without outline)
        g.setColor(playerColor);
        g.fillRect(WIDTH / 2 - PLAYER_SIZE / 2, playerY, PLAYER_SIZE, PLAYER_SIZE);

        // Draw smiling face on the player square
        int circleSize = PLAYER_SIZE / 5; // Size of circles relative to player square
        int offsetX = WIDTH / 2 - PLAYER_SIZE / 2; // X offset for circles
        int offsetY = playerY; // Y offset for circles

        // Draw first circle
        g.setColor(Color.BLACK);
        g.fillOval(offsetX + PLAYER_SIZE / 4, offsetY + PLAYER_SIZE / 4, circleSize, circleSize);

        // Draw second circle
        g.fillOval(offsetX + PLAYER_SIZE * 3 / 4 - circleSize, offsetY + PLAYER_SIZE / 4, circleSize, circleSize);

        // Draw smile (upside-down semicircle)
        g.fillArc(offsetX + PLAYER_SIZE / 4, offsetY + PLAYER_SIZE / 2, PLAYER_SIZE / 2, PLAYER_SIZE / 3, 0, -180);

        // Draw spikes (black triangles)
        for (Spike spike : spikes) {
            spike.draw(g);
        }

        // Draw coins (yellow circles)
        for (Coin coin : coins) {
            coin.draw(g);
        }

        // Draw hearts for HP
        for (Heart heart : hearts) {
            heart.draw(g);
        }

        // Display score in top right corner
        Font font = new Font("Impact", Font.BOLD, 30); // Customize font size and style
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, WIDTH - 130, 30);
        
        Font winFont = new Font("Arial", Font.BOLD, 50);
        if (score == 3) {
            g.setFont(winFont);
            g.setColor(Color.WHITE); // Change color if needed
            String winMessage = "You Win!";
            int messageWidth = g.getFontMetrics().stringWidth(winMessage);
            int x = (WIDTH - messageWidth) / 2;
            int y = HEIGHT / 2;
            g.drawString(winMessage, x, y);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE && !isJumping) {
            isJumping = true;
            jumpVelocity = 17; // Initial jump velocity
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        // Initial stage is set to 1
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rapid Run");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.getContentPane().add(new RapidRunGame(1)); // Start with stage 1
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static class Spike {
        public static final int SIZE = 30;
        private int x, y;
        private int speed; // Adjust spike speed as needed

        // Constructor with speed parameter
        public Spike(int x, int y, int speed) {
            this.x = x;
            this.y = y + PLAYER_SIZE - SIZE; // Align spike with player's bottom edge
            this.speed = speed;
        }

        // Constructor without speed parameter
        public Spike(int x, int y) {
            this.x = x;
            this.y = y + PLAYER_SIZE - SIZE; // Align spike with player's bottom edge
            this.speed = 9; // Default speed
        }

        public void move() {
            x -= speed;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void draw(Graphics g) {
            int[] xPoints = { x, x + SIZE / 2, x + SIZE };
            int[] yPoints = { y + SIZE, y, y + SIZE };
            g.setColor(Color.BLACK);
            g.fillPolygon(xPoints, yPoints, 3);
        }
    }

    private static class Coin {
        public static final int SIZE = 40; // Increase coin size
        private int x, y;
        private int speed = 5; // Adjust coin speed as needed

        public Coin(int x, int y) {
            this.x = x;
            this.y = y - SIZE / 2; // Align coin with ground
        }

        public void move() {
            x -= speed;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, SIZE, SIZE);
        }

        public void draw(Graphics g) {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, SIZE, SIZE);
        }
    }

    private static class Heart {
        private static final int SIZE = 30;
        private int x, y;

        public Heart(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void draw(Graphics g) {
            g.setColor(Color.RED);
            int[] xPoints = { x - 7, x + SIZE / 2 - 7, x + SIZE - 7 };
            int[] yPoints = { y + SIZE - SIZE / 3 - 15, y + SIZE - 15, y + SIZE - SIZE / 3 - 15 }; // Upside down triangle
            g.fillPolygon(xPoints, yPoints, 3);
            g.fillArc(x - SIZE / 4, y, SIZE / 2, SIZE / 3, 0, 180);
            g.fillArc(x + SIZE / 4, y, SIZE / 2, SIZE / 3, 0, 180);
        }
    }

    private static class Background {
        private int stage;
        private Color color1;
        private Color color2;
        private Color currentColor;
        private JPanel panelToRepaint;

        public Background(int stage, JPanel panelToRepaint) {
            this.stage = stage;
            this.panelToRepaint = panelToRepaint;

            if (stage == 1) {
                color1 = Color.CYAN;
                color2 = Color.BLUE;
            } else if (stage == 2) {
                color1 = Color.ORANGE;
                color2 = Color.RED;
            } else if (stage == 3) {
                color1 = new Color(75, 0, 130); // Purple color
                color2 = new Color(255, 255, 255); // White color
                currentColor = color1;

                // Timer for background blinking
                Timer blinkTimer = new Timer(200, e -> {
                    if (currentColor.equals(color1)) {
                        currentColor = color2;
                    } else {
                        currentColor = color1;
                    }
                    // Repaint the panel to update the background color
                    panelToRepaint.repaint();
                });
                blinkTimer.start();
            }
        }

        public void draw(Graphics g, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradient;
            if (stage == 1 || stage == 2) {
                gradient = new GradientPaint(0, 0, color1, 0, height, color2);
            } else { // Stage 3
                gradient = new GradientPaint(0, 0, currentColor, 0, height, currentColor);
            }
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);
        }
    }
}