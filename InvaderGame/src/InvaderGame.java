import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class InvaderGame extends JPanel implements ActionListener, KeyListener {
    private int playerX = 200;
    private int playerY = 200;
    private boolean facingRight = false;
    private boolean facingLeft = false;
    private boolean facingUp = false;
    private boolean facingDown = false;
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private ArrayList<Invader> invaders = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private boolean gameOver = false;

    public InvaderGame() {
        setPreferredSize(new Dimension(400, 400));
        addKeyListener(this);
        setFocusable(true);
        Timer timer = new Timer(10, this);
        timer.start();

        // Initialize invaders
        for (int i = 0; i < 5; i++) {
            int randomX = new Random().nextInt(370);
            int randomY = new Random().nextInt(100) - 200;
            int health = 3; // Each invader has 3 health
            invaders.add(new Invader(randomX, randomY, health));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // Draw the player
        g.setColor(Color.blue);
        g.fillRect(playerX, playerY, 30, 30);

        // Draw the invaders
        g.setColor(Color.red);
        for (Invader invader : invaders) {
            if (invader.getHealth() > 0) {
                g.fillOval(invader.getX(), invader.getY(), 30, 30);
            }
        }

        // Draw the bullets
        g.setColor(Color.green);
        for (Bullet bullet : bullets) {
            g.fillOval(bullet.getX(), bullet.getY(), 7, 7);
        }

        // Display "Game Over!" message when the game is over
        if (gameOver) {
            g.setColor(Color.black);
            g.drawString("Game Over!", 180, 200);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            // Move the player and invaders
            movePlayer();
            moveInvaders();

            // Move bullets and check for collisions
            moveBullets();
            checkCollisions();
        }

        // Repaint the screen
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                facingLeft = true;
                facingRight = false;
                facingUp = false;
                facingDown = false;
                movingLeft = true;
                movingRight = false;
                movingUp = false;
                movingDown = false;
            } else if (key == KeyEvent.VK_RIGHT) {
                facingRight = true;
                facingLeft = false;
                facingUp = false;
                facingDown = false;
                movingRight = true;
                movingLeft = false;
                movingUp = false;
                movingDown = false;
            } else if (key == KeyEvent.VK_UP) {
                facingUp = true;
                facingDown = false;
                facingLeft = false;
                facingRight = false;
                movingUp = true;
                movingLeft = false;
                movingRight = false;
                movingDown = false;
            } else if (key == KeyEvent.VK_DOWN) {
                facingDown = true;
                facingUp = false;
                facingLeft = false;
                facingRight = false;
                movingDown = true;
                movingLeft = false;
                movingUp = false;
                movingRight = false;
            } else if (key == KeyEvent.VK_SPACE) {
                // Shoot a bullet based on the facing direction
                if (facingRight) {
                    bullets.add(new Bullet(playerX + 12, playerY + 12, 3, 0));
                } else if (facingLeft) {
                    bullets.add(new Bullet(playerX + 12, playerY + 12, -3, 0));
                } else if (facingUp) {
                    bullets.add(new Bullet(playerX + 12, playerY + 12, 0, -3));
                } else if (facingDown) {
                    bullets.add(new Bullet(playerX + 12, playerY + 12, 0, 3));
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        // Clear the facing direction flags when the key is released
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            movingLeft = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            movingRight = false;
        } else if (key == KeyEvent.VK_UP) {
            movingUp = false;
        } else if (key == KeyEvent.VK_DOWN) {
            movingDown = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void movePlayer() {
        if (!gameOver) {
            int step = 5;
            if (movingLeft && playerX > 0) {
                playerX -= step;
            }
            if (movingRight && playerX < 370) {
                playerX += step;
            }
            if (movingUp && playerY > 0) {
                playerY -= step;
            }
            if (movingDown && playerY < 370) {
                playerY += step;
            }
        }
    }

    public void moveInvaders() {
        for (Invader invader : invaders) {
            if (invader.getY() > 400) {
                invader.setY(-30);
                invader.setX(new Random().nextInt(370));
            }
            invader.setY(invader.getY() + 1);
        }
    }

    public void moveBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            int xDirection = bullet.getXDirection();
            int yDirection = bullet.getYDirection();

            if (bullet.getY() < 0 || bullet.getY() > 400) {
                bullets.remove(i);
            } else {
                bullet.setX(bullet.getX() + xDirection);
                bullet.setY(bullet.getY() + yDirection);
            }
        }
    }

    public void checkCollisions() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < invaders.size(); j++) {
                Invader invader = invaders.get(j);
                if (invader.getHealth() > 0) {
                    if (bullet.getX() >= invader.getX() && bullet.getX() <= invader.getX() + 30
                            && bullet.getY() >= invader.getY() && bullet.getY() <= invader.getY() + 30) {
                        invader.decrementHealth(); // Decrease invader's health
                        if (invader.getHealth() == 0) {
                            invaders.remove(j);
                        }
                        bullets.remove(i);
                    }
                }
            }
        }
        if (invaders.isEmpty()) {
            gameOver = true;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders");
        InvaderGame game = new InvaderGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
