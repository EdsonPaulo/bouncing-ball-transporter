package MainGame;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author EdsonPaulo
 */
public class MainGame {

    public static void main(String[] args) throws Exception {
        JFrame frmMain = new JFrame("Bouncing Ball Transporter");

        frmMain.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT + 30);
        frmMain.setResizable(false);
        frmMain.setLayout(null);
        frmMain.setLocationRelativeTo(null);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BouncingBallGame game = new BouncingBallGame();
        game.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        game.setBackground(new Color(53, 59, 72));

        frmMain.addKeyListener(game);

        frmMain.add(game);
        frmMain.setVisible((true));
    }
}
