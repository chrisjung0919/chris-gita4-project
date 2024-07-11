import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SpaceInvadersGame extends JPanel implements ActionListener, KeyListener {
    private int shipX;
    private int invaderHits;
    private ArrayList<Invader> invaders;
    private boolean isMovingLeft;
    private boolean isMovingRight;
    private boolean isFiring;
    private ArrayList<Bullet> bullets;
    private Timer timer;

    public SpaceInvadersGame() {
        shipX = 375;
        invaders = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            invaders.add(new Invader());
        }
        isMovingLeft = false;
        isMovingRight = false;
        isFiring = false;
        bullets = new ArrayList<>();

        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(10, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (isMovingLeft && shipX > 0) {
            shipX -= 5;
        }
        if (isMovingRight && shipX < 750) {
            shipX += 5;
        }

        for (Invader invader : invaders) {
            invader.move();
            if (invader.getX() > 800) {
                invader.reset();
            }

            if (shipX < invader.getX() + 30 && shipX + 50 > invader.getX() && 500 < invader.getY() + 30 && 520 > invader.getY()) {
                if (!invader.isKilled()) {
                    invaderHits++;
                }
                if (invaderHits >= 3) {
                    invaderHits = 0;
                    invader.reset();
                } else {
                    invader.setY((int) (Math.random() * 200) + 50);
                }
            }

            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                bullet.move();
                if (bullet.getY() < 0) {
                    bullets.remove(i);
                    i--;
                }
                if (bullet.intersects(invader.getX(), invader.getY(), 30, 30)) {
                    bullets.remove(i);
                    i--;
                    if (!invader.isKilled()) {
                        invaderHits++;
                    }
                    if (invaderHits >= 3) {
                        invaderHits = 0;
                        invader.reset();
                    } else {
                        invader.setY((int) (Math.random() * 200) + 50);
                    }
                }
            }
        }

        repaint();
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            isMovingLeft = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            isMovingRight = true;
        }
        if (key == KeyEvent.VK_SPACE && !isFiring) {
            isFiring = true;
            bullets.add(new Bullet(shipX + 25, 500));
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            isMovingLeft = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            isMovingRight = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            isFiring = false;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(shipX, 500, 50, 20);
        g.fillRect(shipX+20, 470, 10, 50);

        g.setColor(Color.RED);
        for (Invader invader : invaders) {
            if (!invader.isKilled()) {
                g.fillRect(invader.getX(), invader.getY(), 30, 30);
            }
        }

        g.setColor(Color.YELLOW);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders");
        SpaceInvadersGame game = new SpaceInvadersGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private class Bullet {
        private int x;
        private int y;

        public Bullet(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move() {
            y -= 5;
        }

        public void draw(Graphics g) {
            g.fillRect(x, y, 3, 10);
        }

        public int getY() {
            return y;
        }

        public boolean intersects(int otherX, int otherY, int width, int height) {
            return x < otherX + width && x + 3 > otherX && y < otherY + height && y + 10 > otherY;
        }
    }

    private class Invader {
        private int x;
        private int y;
        private boolean killed;

        public Invader() {
            reset();
        }

        public void reset() {
            x = 0;
            y = (int) (Math.random() * 200) + 50;
            killed = false;
        }

        public void move() {
            if (!killed) {
                x += 2;
            }
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public boolean isKilled() {
            return killed;
        }
    }
}