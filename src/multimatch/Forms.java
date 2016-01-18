
package multimatch;


import java.awt.CardLayout;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ethan
 */
public class Forms {
    
    private JFrame mainFrame;
    
    private JPanel cardContainer;
    private JPanel mainScreen;
    private JPanel gameScreen;
    private JPanel instructionScreen;
    
    private JButton exit;
    private JButton play;
    private JButton instructions;
    
    private Logic game;
    
    //Fonts, layouts
        Font titleFont = new Font(Font.SERIF, Font.BOLD, 32);
        Font subtitleFont = new Font(Font.SERIF, Font.BOLD, 24);
        private CardLayout cardLayout = new CardLayout();
    
    public Forms() {
        
        createGUI();
        //Instantiate game logic
        game = new Logic();
        
        
    }
    
    private void createGUI() {
        mainFrame = new JFrame("MultiMatch");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Fullscreen
        mainFrame.setExtendedState(MAXIMIZED_BOTH);
        //mainFrame.setUndecorated(true);
        
        cardContainer = new JPanel(cardLayout);
        
        MainMenu();
        InstructionScreen();
        GameScreen();
        
        cardContainer.add(mainScreen);
        cardContainer.add(instructionScreen);
        cardContainer.add(gameScreen);
        mainFrame.add(cardContainer);
        
        mainFrame.pack();
        mainFrame.setVisible(true);
        
    }
    
    private void MainMenu() {
        mainScreen = new JPanel();
        mainScreen.setLayout(new BoxLayout(mainScreen, BoxLayout.Y_AXIS));
        
        JLabel titleText = new JLabel("MultiMatch");
        titleText.setFont(titleFont);
        JLabel subText = new JLabel("Please read instructions before playing");
        subText.setFont(subtitleFont);
        JLabel blank = new JLabel("");
        
        play = new JButton("Play");
        play.setEnabled(false);
        instructions = new JButton("Instructions");
        exit = new JButton("Exit");
        
        MenuListener handler = new MenuListener(play, instructions, exit);
        
        instructions.addActionListener(handler);
        play.addActionListener(handler);
        exit.addActionListener(handler);
        
        
        mainScreen.add(blank);
        mainScreen.add(titleText);
        mainScreen.add(subText);
        mainScreen.add(blank);
        mainScreen.add(play);
        mainScreen.add(instructions);
        mainScreen.add(blank);
        mainScreen.add(exit);
    }
    
    private void InstructionScreen() {
        instructionScreen = new JPanel();
    }
    
    private void GameScreen() {
        gameScreen = new JPanel();
        JPanel scorePanel = new JPanel();
        JPanel gamePanel = new JPanel();
    }

    
    
}
