
package MainGame;

import javax.swing.JFrame;

/**
 *
 * @author EdsonPaulo
 */
public class MainGame {
    
    public static void main(String[] args) {
        JFrame frmMain = new JFrame("Bouncing Ball Transporter");
        
        frmMain.setSize(1366, 840);
        frmMain.setResizable(false);
        frmMain.setLayout(null);
        frmMain.setLocationRelativeTo(null);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMain.setVisible((true));
    }
}
