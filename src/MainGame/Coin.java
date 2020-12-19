package MainGame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author EdsonPaulo
 */
public class Coin {

    private BufferedImage coinImg;
    private int COIN_WIDTH = 70;
    private int COIN_HEIGHT = 70;

    private int positionX = Constants.WINDOW_WIDTH_HALF;
    private int positionY = 100;

    public Coin() {
        int coinImageNumber = (new Random().nextInt(5) + 1);
        try {
            coinImg = ImageIO.read(new File("assets/images/coins/" + coinImageNumber + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Racket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Coin(int posX, int posY) {
        this.positionX = posX;
        this.positionY = posY;
        int coinImageNumber = (new Random().nextInt(5) + 1);
        try {
            coinImg = ImageIO.read(new File("assets/images/coins/" + coinImageNumber + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Racket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void drawCoin(Graphics2D g2D) {
        g2D.drawImage(coinImg, positionX, positionY,
                positionX + COIN_WIDTH, positionY + COIN_HEIGHT,
                0, 0, coinImg.getWidth(), coinImg.getHeight(),
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

    public int getCOIN_WIDTH() {
        return COIN_WIDTH;
    }

    public void setCOIN_WIDTH(int COIN_WIDTH) {
        this.COIN_WIDTH = COIN_WIDTH;
    }

    public int getCOIN_HEIGHT() {
        return COIN_HEIGHT;
    }

    public void setCOIN_HEIGHT(int COIN_HEIGHT) {
        this.COIN_HEIGHT = COIN_HEIGHT;
    }

}
