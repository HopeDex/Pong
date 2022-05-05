import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

public class Game extends JPanel implements KeyListener, ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private boolean play = false;
    private int score1 = 0;
    private int score2 = 0;

    private Timer timer;
    private int delay = 2;

    private int player1 = 310;
    private int player2 = 320;
    private int maxScore = 25;

    private int ballposX = 300;
    private int ballposY = 300;
    private int ballXdir = -1;
    private int ballYdir = -2;

    public Game() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 980, 600);
        // borders
        // g.setColor(Color.yellow);
        // g.fillRect(0, 0, 3, 592);
        // g.fillRect(0, 0, 692, 3);
        // g.fillRect(691, 0, 3, 592);

        // scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score2, 950, 30);
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score1, 20, 30);

        // the paddle
        g.setColor(Color.green);
        g.fillRect(10, player1, 8, 100);

        // the paddle
        g.setColor(Color.green);
        g.fillRect(970, player2, 8, 100);

        // the ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);

        if (ballposX > 992) {
            score1 = scoree(score1);
        }
        if (ballposX < 0) {
            score2 = scoree(score2);
        }
        // if (maxBalls == 3) {
        // play = false;
        // g.setColor(Color.red);
        // g.setFont(new Font("serif", Font.BOLD, 25));
        // g.drawString(" Game Over", 300, 300);
        // score1 = 0;
        // score2 = 0;
        // }
        // g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(10, player1, 8, 100))) {
                ballXdir = -ballXdir;
            }
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(970, player2, 8, 100))) {
                ballXdir = -ballXdir;
            }
            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposY < 00) {
                ballYdir = -ballYdir;
            }

            if (ballposY > 565) {
                ballYdir = -ballYdir;
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (player1 >= 450) {
                        player1 = 450;
                    } else {
                        moveUp();
                    }

                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (player1 < 10) {
                        player1 = 10;
                    } else {
                        moveDown();
                    }
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    if (player2 >= 450) {
                        player2 = 450;
                    } else {
                        moveUp2();
                    }
                }
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    if (player2 < 10) {
                        player2 = 10;
                    } else {
                        moveDown2();
                    }
                }
            }
        });
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!play) {
                        play = true;
                        ballposX = 120;
                        ballposY = 350;
                        ballXdir = -1;
                        ballYdir = -2;
                        player1 = 310;
                        score1 = 0;
                        repaint();

                    }
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    public void moveUp() {
        play = true;
        player1 += 20;

    }

    public void moveDown() {
        play = true;
        player1 -= 20;
    }

    public void moveDown2() {
        play = true;
        player2 -= 20;
    }

    public void moveUp2() {
        play = true;
        player2 += 20;
    }

    public int scoree(int s) {
        ballXdir = -ballXdir;
        ballYdir = -ballYdir;
        ballposX = 340;
        ballposY = 350;
        s += 5;
        return s;

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}