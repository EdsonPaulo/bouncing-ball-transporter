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
    private final int RACKET_WIDTH = 220;
    private final int RACKET_HEIGHT = 70;

    private int racketPositionX = 100;
    private int racketPositionY = 750;
    private int racketBounceRate = 20;

    public Racket() {
        try {
            racketImg = ImageIO.read(new File("assets/images/Racket.png"));
        } catch (IOException ex) {
            Logger.getLogger(Racket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void drawRacket(Graphics2D g2D) {
        g2D.drawImage(
            racketImg, racketPositionX, racketPositionY,
            racketPositionX + RACKET_WIDTH, racketPositionY + RACKET_HEIGHT,
            0, 0, racketImg.getWidth(), racketImg.getHeight(),
            null
        );
    }


    public int getRacketPositionX() {
        return racketPositionX;
    }

    public void setRacketPositionX(int racketPositionX) {
        this.racketPositionX = racketPositionX;
    }

    public int getRacketPositionY() {
        return racketPositionY;
    }

    public void setRacketPositionY(int racketPositiony) {
        this.racketPositionY = racketPositiony;
    }
    
    public int getRACKET_WIDTH() {
        return RACKET_WIDTH;
    }

    public int getRACKET_HEIGHT() {
        return RACKET_HEIGHT;
    }
}
