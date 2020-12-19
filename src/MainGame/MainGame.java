package MainGame;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

        JPanel backgroundPane = new JPanel();
        backgroundPane.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        backgroundPane.setLayout(null);
        
        JLabel auxLabel = new JLabel();
        auxLabel.setIcon(new ImageIcon("assets/images/Background.png"));
        auxLabel.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        BouncingBallGame game = new BouncingBallGame();
        game.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        game.setBackground(new Color(53, 59, 72));

        //game.add(auxLabel);
        backgroundPane.add(game);

        frmMain.addKeyListener(game);

        frmMain.add(backgroundPane);
        frmMain.setVisible((true));
    }
}
