package MainGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author EdsonPaulo
 */
public class BouncingBallGame extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private final Ball ball;
    private final Racket racket;
    private final Block block;

    private int mouseX;
    private int mouseY;
    private int oldMouseY;
    private int oldMouseX;

    private int blockPositionFactor = 15;

    private int userScore = 0;

    private boolean isMousePressed;

    public BouncingBallGame() throws Exception {
        ball = new Ball();
        racket = new Racket();
        block = new Block();

        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                update();
                repaint();
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(BouncingBallGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void update() throws InterruptedException {
        listenerRacketBounce();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();

        //ACTIVATE ANTIALISING
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        racket.drawRacket(g2D);
        ball.drawBall(g2D);
        block.drawBlock(g2D, 200, 30);

        //Invert block direction
        if (block.getBlockPositionX() >= (Constants.WINDOW_WIDTH - block.getBlockWidth())
                || block.getBlockPositionX() <= 0) {
            blockPositionFactor *= -1;
        }

        block.setBlockPositionX(block.getBlockPositionX() + blockPositionFactor);

        g2D.setColor(Color.white);
        g2D.setFont(new Font("consolas", Font.BOLD, 32));
        g2D.drawString("SCORE: " + userScore, 1100, 40);
    }

    public void listenerRacketBounce() throws InterruptedException {
        int overhead = mouseY - oldMouseY;
        if ((ball.getBallPositionX() + Constants.getHalves(ball.getBALL_SIZE()) > mouseX - Constants.getHalves(racket.getRACKET_WIDTH()))
                && (ball.getBallPositionX() - Constants.getHalves(ball.getBALL_SIZE()) < mouseX + Constants.getHalves(racket.getRACKET_WIDTH()))) {

            if (Point2D.distance(
                    ball.getBallPositionX(), ball.getBallPositionY(),
                    ball.getBallPositionX(), mouseY)
                    <= (Constants.getHalves(ball.getBALL_SIZE()) + Math.abs(overhead))) {

                ball.makeBounceBottom(mouseY);
                ball.setBallHorizontalSpeed(
                        Constants.getHalves((ball.getBallPositionX() - mouseX), 20)
                );
                if (overhead < 0) {
                    ball.setBallPositionY(ball.getBallPositionY() + ((int) Math.abs(Constants.getHalves(overhead))));
                    ball.setBallVerticalSpeed(ball.getBallVerticalSpeed() + Constants.getHalves(overhead));
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            racket.setRacketPositionY(racket.getRacketPositionY() - 20);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            racket.setRacketPositionY(racket.getRacketPositionY() + 20);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            racket.setRacketPositionX(racket.getRacketPositionX() - 20);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            racket.setRacketPositionX(racket.getRacketPositionX() + 20);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if ((e.getX() >= racket.getRacketPositionX()
                && e.getX() <= (racket.getRacketPositionX() + racket.getRACKET_WIDTH()))
                && (e.getY() >= racket.getRacketPositionY()
                && e.getY() <= (racket.getRacketPositionY() + racket.getRACKET_HEIGHT()))) {
            isMousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isMousePressed) {
            oldMouseY = mouseY;
            oldMouseX = mouseX;

            racket.setRacketPositionX(e.getX());
            racket.setRacketPositionY(e.getY());
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }
}
