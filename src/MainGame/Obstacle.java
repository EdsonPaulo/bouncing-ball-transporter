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
public class Obstacle {

    private BufferedImage obstacleImg;
    private int OBSTACLE_WIDTH = 200;
    private int OBSTACLE_HEIGHT = 30;

    private int positionX = Constants.WINDOW_WIDTH_HALF;
    private int positionY = Constants.WINDOW_HEIGHT_HALF;
    private int speed = 5;

    public Obstacle() {
        try {
            obstacleImg = ImageIO.read(new File("assets/images/Obstacle.png"));
            updateObstacle();
        } catch (IOException ex) {
            Logger.getLogger(Racket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateObstacle() {
        //Invert block direction
        if (positionX >= (Constants.WINDOW_WIDTH - OBSTACLE_WIDTH)
                || positionX <= 0) {
            speed *= -1;
        }
        positionX += speed;
    }

    public void drawObstacle(Graphics2D g2D) {
        g2D.drawImage(
            obstacleImg, positionX, positionY,
            positionX + OBSTACLE_WIDTH, positionY + OBSTACLE_HEIGHT,
            0, 0, obstacleImg.getWidth(), obstacleImg.getHeight(),
            null
        );
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

    public void setPositionY(int racketPositiony) {
        this.positionY = racketPositiony;
    }

    public int getOBSTACLE_WIDTH() {
        return OBSTACLE_WIDTH;
    }

    public void setOBSTACLE_WIDTH(int OBSTACLE_WIDTH) {
        this.OBSTACLE_WIDTH = OBSTACLE_WIDTH;
    }

    public int getOBSTACLE_HEIGHT() {
        return OBSTACLE_HEIGHT;
    }

    public void setOBSTACLE_HEIGHT(int OBSTACLE_HEIGHT) {
        this.OBSTACLE_HEIGHT = OBSTACLE_HEIGHT;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
