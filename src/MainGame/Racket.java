package MainGame;

import java.awt.Color;
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
public class Racket {

    // RACKET PROPERTIES
    private BufferedImage racketImg;
    private final int RACKET_WIDTH = 250;
    private final int RACKET_HEIGHT = 40;

    private int positionX = Constants.WINDOW_WIDTH_HALF;
    private int positionY = Constants.WINDOW_HEIGHT - RACKET_HEIGHT * 2;
    private int speed = 25;

    public Racket() {
        try {
            racketImg = ImageIO.read(new File("assets/images/Racket.png"));
        } catch (IOException ex) {
            Logger.getLogger(Racket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void drawRacket(Graphics2D g2D) {
        g2D.drawImage(
                racketImg, positionX, positionY,
                positionX + RACKET_WIDTH, positionY + RACKET_HEIGHT,
                0, 0, racketImg.getWidth(), racketImg.getHeight(),
                null
        );
    }

    public void moveToTop() {
        positionY -= speed;
    }

    public void moveToDown() {
        positionY += speed;
    }

    public void moveToLeft() {
        positionX -= speed;
    }

    public void moveToRight() {
        positionX += speed;
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

    public int getRACKET_WIDTH() {
        return RACKET_WIDTH;
    }

    public int getRACKET_HEIGHT() {
        return RACKET_HEIGHT;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
