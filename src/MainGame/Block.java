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
public class Block {

    private int blockWidth = 250;
    private int blockHeight = 100;

    private int blockPositionX = Constants.WINDOW_WIDTH_HALF;
    private int blockPositionY = Constants.WINDOW_HEIGHT_HALF;
    private int blockVelocity = 10;

    public Block() {

    }

    public void drawBlock(Graphics2D g2D, int width, int height) {
        this.blockWidth = width;
        this.blockHeight = height;
        g2D.setColor(Color.RED);
        g2D.fillRoundRect(blockPositionX, blockPositionY, blockWidth, blockHeight, 15, 15);
    }

    public int getBlockPositionX() {
        return blockPositionX;
    }

    public void setBlockPositionX(int blockPositionX) {
        this.blockPositionX = blockPositionX;
    }

    public int getBlockPositionY() {
        return blockPositionY;
    }

    public void setBlockPositionY(int racketPositiony) {
        this.blockPositionY = racketPositiony;
    }

    public int getBlockWidth() {
        return blockWidth;
    }

    public void setBlockWidth(int blockWidth) {
        this.blockWidth = blockWidth;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(int blockHeight) {
        this.blockHeight = blockHeight;
    }

    public int getBlockVelocity() {
        return blockVelocity;
    }

    public void setBlockVelocity(int blockVelocity) {
        this.blockVelocity = blockVelocity;
    }

}
