
package multimatch;

import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ethan
 */
public class Forms implements ActionListener {
    
    private JFrame mainFrame;
    private JPanel cardContainer;
    private JPanel mainScreen;
    private JPanel instructionScreen;
    private Logic game;
    
    //Fonts
        Font titleFont = new Font(Font.SERIF, Font.BOLD, 32);
        Font subtitleFont = new Font(Font.SERIF, Font.BOLD, 24);
    
    public Forms() {
        
        createGUI();
        //Instantiate game logic
        game = new Logic();
        
        
    }
    
    private void createGUI() {
        mainFrame = new JFrame("MultiMatch");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Fullscreen?
        mainFrame.setExtendedState(MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);
        
        //Main screen
        mainScreen = new JPanel(new GridLayout(0,1, 50, 50));
        
        JLabel titleText = new JLabel("MultiMatch");
        titleText.setFont(titleFont);
        JLabel subText = new JLabel("Please read instructions before playing");
        subText.setFont(subtitleFont);
        JLabel blank = new JLabel("");
        
        JButton play = new JButton("Play");
        play.setEnabled(false);
        JButton instructions = new JButton("Instructions");
        JButton exit = new JButton("Exit");
        
        mainScreen.add(blank);
        mainScreen.add(titleText);
        mainScreen.add(subText);
        mainScreen.add(blank);
        //Flowlayout for buttons?
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
