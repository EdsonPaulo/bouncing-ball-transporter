package MainGame;

public final class Constants {

    //Window dimension
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 850;
    public static final int WINDOW_WIDTH_HALF = WINDOW_WIDTH / 2;
    public static final int WINDOW_HEIGHT_HALF = WINDOW_HEIGHT / 2;

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

}
