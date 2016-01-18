
package multimatch;


import java.awt.CardLayout;
import java.awt.Component;
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
    private JButton menu;
    
    private Logic game;
    private MenuListener handler;
    
    //Fonts, layouts
        Font titleFont = new Font(Font.SERIF, Font.BOLD, 32);
        Font subtitleFont = new Font(Font.SERIF, Font.BOLD, 24);
        float center = Component.CENTER_ALIGNMENT;
        private CardLayout cardLayout = new CardLayout();
    
    public Forms() {
        
        
        //Instantiate game logic
        game = new Logic();
        
        play = new JButton("Play");
        instructions = new JButton("Instructions");
        exit = new JButton("Exit");
        menu = new JButton("Main Menu");
        
        handler = new MenuListener(this, play, instructions, exit, menu);
        
        createGUI();
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
        
        cardContainer.add(mainScreen, "main");
        cardContainer.add(instructionScreen, "inst");
        cardContainer.add(gameScreen, "game");
        mainFrame.add(cardContainer);
        
        mainFrame.pack();
        mainFrame.setVisible(true);
        
    }
    
    private void MainMenu() {
        mainScreen = new JPanel();
        mainScreen.setLayout(new BoxLayout(mainScreen, BoxLayout.Y_AXIS));
        
        
        JLabel titleText = new JLabel("MultiMatch");
        titleText.setFont(titleFont);
        titleText.setAlignmentX(center);
        
        JLabel subText = new JLabel("Please read instructions before playing", JLabel.CENTER);
        subText.setFont(subtitleFont);
        //subText.setAlignmentX(center);
        
        JLabel blank = new JLabel(" ");
        blank.setAlignmentX(center);
        
        
        //play.setEnabled(false);
        
        play.setAlignmentX(center);
        
        
        instructions.setAlignmentX(center);
        
        
        exit.setAlignmentX(center);
        
        
        
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
        instructionScreen.setLayout(new BoxLayout(instructionScreen, BoxLayout.Y_AXIS));
        
        JLabel text1 = new JLabel("The goal of the game is to click and drag the boxes so that the multiplication problem is correct, like so:",JLabel.CENTER);
        
        menu.addActionListener(handler);
        instructionScreen.add(text1);
        instructionScreen.add(menu);
    }
    
    private void GameScreen() {
        gameScreen = new JPanel();
        JPanel scorePanel = new JPanel();
        JPanel gamePanel = new JPanel();
    }
    
    public void changeScreen(Object source) {
        if (source == menu) {
            cardLayout.show(cardContainer, "main");
            
        }
        if (source == instructions) {
            cardLayout.show(cardContainer, "inst");
        }
        
        if (source == play) {
            cardLayout.show(cardContainer, "game");
            game.start();
        }
    }

    
    
}
