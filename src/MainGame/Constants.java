package MainGame;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public final class Constants {

    //Window dimension
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 850;
    public static final int WINDOW_WIDTH_HALF = WINDOW_WIDTH / 2;
    public static final int WINDOW_HEIGHT_HALF = WINDOW_HEIGHT / 2;
    
    //SOUND FILES
    public static final String GOT_COIN_SOUND = "assets/sounds/got-coin.wav";      
    public static final String YOU_WIN = "assets/sounds/you-win.wav";    
    public static final String LOSE_LIFE_SOUND = "assets/sounds/lose-life.wav";
    public static final String GAME_OVER_SOUND = "assets/sounds/game-over.wav";

    /*
      get the halves size of a passed size
     */
    public static int getHalves(int fullSizeObject) {
        return (fullSizeObject / 2);
    }

    public static float getHalves(float fullSizeObject) {
        return (fullSizeObject / 2);
    }

    public static int getHalves(int fullSizeObject, int halves) {
        return (fullSizeObject / halves);
    }

    public static float getHalves(float fullSizeObject, int halves) {
        return (fullSizeObject / halves);
    }

    /*
     * Draw a String centered in the middle of a Rectangle.
     */
    public static void drawCenteredString(
            Graphics2D g, String text, Font font,
            int containerX, int containerY,
            int containerWidth, int containerHeight
    ) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = containerX + (containerWidth - metrics.stringWidth(text)) / 2;
        int y = containerY + ((containerHeight - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

}
