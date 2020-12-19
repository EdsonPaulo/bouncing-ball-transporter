package MainGame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author EdsonPaulo
 */
public class Ball {

    // BALL PROPERTIES
    private BufferedImage ballImg;
    private final int BALL_SIZE = 50;
    private int speed = 5;
    private int positionX = 100;
    private int positionY = 100;
    private int directionX = 1;
    private int directionY = 1;

    public Ball() {
        try {
            ballImg = ImageIO.read(new File("assets/images/Ball.png"));
        } catch (IOException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void drawBall(Graphics2D g2D) {
        g2D.drawImage(ballImg, positionX, positionY,
                positionX + BALL_SIZE, positionY + BALL_SIZE,
                0, 0, ballImg.getWidth(), ballImg.getHeight(),
                null);
        keepOnScreenBounds();
        moveBall();
    }

    /// HANDLING BALL ON VERTICAL DIRECTION (Y)
    private void moveBall() {
        positionY += speed * directionY;
        positionX += speed * directionX;
    }

    // keep ball in the screen bounds
    private void keepOnScreenBounds() {
        if (positionY - Constants.getHalves(BALL_SIZE) < 0
                || positionY + Constants.getHalves(BALL_SIZE) >= Constants.WINDOW_HEIGHT) {
            directionY *= -1;
        }

        if (positionX - Constants.getHalves(BALL_SIZE) < 0
                || positionX + Constants.getHalves(BALL_SIZE) >= Constants.WINDOW_WIDTH) {
            directionX *= -1;
        }
    }

    public void invertDirectionX() {
        this.directionX *= -1;
    }

    public void invertDirectionY() {
        this.directionY *= -1;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBALL_SIZE() {
        return BALL_SIZE;
    }

}
