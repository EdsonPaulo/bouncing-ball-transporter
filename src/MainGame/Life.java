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
public class Life {

    private BufferedImage lifeImg;
    private int LIFE_WIDTH = 30;
    private int LIFE_HEIGHT = 30;

    private int life = 4;

    public Life() {
        try {
            lifeImg = ImageIO.read(new File("assets/images/Life.png/"));
        } catch (IOException ex) {
            Logger.getLogger(Racket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void drawLife(Graphics2D g2D) {
        for (int i = 0; i <= life; i++) {
            int posX = 40 + i * 40;
            int posY = 30;
            g2D.drawImage(lifeImg, posX, posY, posX + LIFE_WIDTH, posY + LIFE_HEIGHT,
                    0, 0, lifeImg.getWidth(),
                    lifeImg.getHeight(),
                    null
            );
        }
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
