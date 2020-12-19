package MainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author EdsonPaulo
 */
public class BouncingBallGame extends JPanel implements Runnable, KeyListener {

    private final BufferedImage backgroundImg;
    private final BufferedImage lifeImg;
    private final Ball ball;
    private final Racket racket;
    private final Obstacle obstacle;
    private final ArrayList<Coin> coinsList;
    private Coin coin;
    private final Life life;
    private final Random rand;

    private boolean paused = true;

    private int score = 0;

    public BouncingBallGame() throws Exception {
        backgroundImg = ImageIO.read(new File("assets/images/Background.png"));
        lifeImg = ImageIO.read(new File("assets/images/Life.png"));

        rand = new Random(100000);
        ball = new Ball();
        racket = new Racket();
        obstacle = new Obstacle();
        life = new Life();
        coinsList = new ArrayList<>();

        createCoins(6);

        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                update();
                Thread.sleep(8);
            } catch (InterruptedException ex) {
                Logger.getLogger(BouncingBallGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void update() throws InterruptedException {
        if (!paused) {
            checkCollisions();
            checkBallOnFloor();
            obstacle.updateObstacle();
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();

        //ACTIVATE ANTIALISING
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        for (Coin coin : coinsList) {
            coin.drawCoin(g2D);
        }
        ball.drawBall(g2D);
        racket.drawRacket(g2D);
        obstacle.drawObstacle(g2D);
        life.drawLife(g2D);

        if (paused) {
            drawPausedStatus(g2D);
        }

        g2D.setColor(Color.white);
        g2D.setFont(new Font("consolas", Font.BOLD, 32));
        g2D.drawString("Pontuação: " + score, Constants.WINDOW_WIDTH - 300, 60);
    }

    // listener to score points
    private void incrementScore() {
        score += 100;
        playSound(Constants.GOT_COIN_SOUND);
        // ball.setSpeed(ball.getSpeed() + 1);
        racket.setSpeed(racket.getSpeed() + 2);
        if (coinsList.isEmpty()) {
            youWin();
        }
        createCoins(rand.nextInt(2));
    }

    // listener for the ball fell to the floor
    private void checkBallOnFloor() {
        if (ball.getPositionY() >= Constants.WINDOW_HEIGHT - ball.getBALL_SIZE()) {
            if (life.getLife() > 0) {
                playSound(Constants.LOSE_LIFE_SOUND);
                ball.setPositionY(50);
                life.setLife(life.getLife() - 1);
                paused = true;
            } else {
                gameOver();
            }
        }
    }

    private void restartGame() {
        createCoins(6);
        ball.setSpeed(5);
        ball.setPositionY(50);
        racket.setSpeed(25);
        score = 0;
        life.setLife(4);
    }

    private void gameOver() {
        playSound(Constants.GAME_OVER_SOUND);
        paused = true;
        int result = JOptionPane.showConfirmDialog(this, "Você quer jogar de novo?",
                "GAME OVER", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void youWin() {
        playSound(Constants.YOU_WIN);
        paused = true;
        int result = JOptionPane.showConfirmDialog(this, "Você ganhou com " + score + "pontos. \n\n Você quer jogar de novo?",
                "Parabéns", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    // listener & handlers to colisions
    private void checkCollisions() {
        collisionBallAndRacket();
        collisionBallAndObstacle();
        collisionBallAndCoin();
    }

    //Colision between the ball racket
    private void collisionBallAndRacket() {
        Rectangle c = new Rectangle(
                racket.getPositionX(),
                racket.getPositionY(),
                racket.getRACKET_WIDTH(),
                racket.getRACKET_HEIGHT());
        Rectangle b = new Rectangle(
                ball.getPositionX(),
                ball.getPositionY(),
                ball.getBALL_SIZE(),
                ball.getBALL_SIZE());
        if (b.intersects(c)) {
            ball.invertDirectionY();
        }
    }

    //Colision between the ball and coin
    private void collisionBallAndCoin() {
        for (int i = 0; i < coinsList.size(); i++) {
            Coin coin = coinsList.get(i);
            Rectangle c = new Rectangle(
                    coin.getPositionX(),
                    coin.getPositionY(),
                    coin.getCOIN_WIDTH(),
                    coin.getCOIN_HEIGHT());
            Rectangle b = new Rectangle(
                    ball.getPositionX(),
                    ball.getPositionY(),
                    ball.getBALL_SIZE(),
                    ball.getBALL_SIZE());
            if (b.intersects(c)) {
                coinsList.remove(i);
                incrementScore();
            }
        }
    }

    //Colision between the ball obstacle
    private void collisionBallAndObstacle() {
        Rectangle o = new Rectangle(
                obstacle.getPositionX(),
                obstacle.getPositionY(),
                obstacle.getOBSTACLE_WIDTH(),
                obstacle.getOBSTACLE_HEIGHT());
        Rectangle b = new Rectangle(
                ball.getPositionX(),
                ball.getPositionY(),
                ball.getBALL_SIZE(),
                ball.getBALL_SIZE());
        if (b.intersects(o)) {
            ball.invertDirectionY();
        }
    }

    //generate instances of new coins and draw on random position
    private void createCoins(int nCoins) {
        while (nCoins > 0) {
            int coinPosX = rand.nextInt(Constants.WINDOW_WIDTH + 1);
            int coinPosY = rand.nextInt(Constants.WINDOW_HEIGHT_HALF - 55);
            coin = new Coin(coinPosX, coinPosY);
            coinsList.add(coin);
            nCoins--;
        }
    }

    //draw game over screen
    private void drawGameOver(Graphics2D g2D) {
        g2D.setBackground(new Color(44, 62, 80));
        g2D.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        g2D.setColor(Color.white);

        Constants.drawCenteredString(
                g2D, "GAME OVER", new Font("consolas", Font.BOLD, 48), 0, 0,
                Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        Constants.drawCenteredString(
                g2D, "Pressione 'Espaço' para jogar novamente", new Font("consolas", Font.BOLD, 28), 0, 0,
                Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT + 150);
    }

    //draw paused message
    private void drawPausedStatus(Graphics2D g2D) {
        g2D.setColor(Color.white);
        Constants.drawCenteredString(
                g2D, "Pressione 'Espaço' para continuar", new Font("consolas", Font.BOLD, 28), 0, 0,
                Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    private void playSound(String fileName) {
        try {
            InputStream is = new FileInputStream(fileName);
            AudioStream as = new AudioStream(is);
            AudioPlayer.player.start(as);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (racket.getPositionY() > Constants.WINDOW_HEIGHT - 150) {
                racket.moveToTop();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (racket.getPositionY() < Constants.WINDOW_HEIGHT - racket.getRACKET_HEIGHT()) {
                racket.moveToDown();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (racket.getPositionX() > 0) {
                racket.moveToLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (racket.getPositionX() + racket.getRACKET_WIDTH() < Constants.WINDOW_WIDTH) {
                racket.moveToRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            paused = !paused;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
