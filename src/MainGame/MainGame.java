
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
        
        frmMain.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        frmMain.setResizable(false);
        frmMain.setLayout(null);
        frmMain.setLocationRelativeTo(null);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BouncingBallGame game = new BouncingBallGame();
        game.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        game.setBackground(new Color(71, 71, 135));

        frmMain.addKeyListener(game);
        frmMain.addMouseMotionListener(game);        
        frmMain.addMouseListener(game);
        
        frmMain.add(game);
        frmMain.setVisible((true));
    }
}
