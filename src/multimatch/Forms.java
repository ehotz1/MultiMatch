
package multimatch;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
    private GamePanel gamePanel;
    
    private final JButton exit;
    private final JButton play;
    private final JButton instructions;
    private final JButton menu;
    private final JButton next;
    
    private Logic logic;
    private Score score;
    private MenuListener handler;
    private MouseListener listener;
    
    //Fonts, layouts
        Font titleFont = new Font(Font.SERIF, Font.BOLD, 50);
        Font subtitleFont = new Font(Font.SERIF, Font.BOLD, 24);
        float center = Component.CENTER_ALIGNMENT;
        CardLayout cardLayout = new CardLayout();
        
    
    public Forms() {
        
        
        //Instantiate game logic
        gamePanel = new GamePanel();
        logic = new Logic(gamePanel);
        score = new Score(1);
        
        play = new JButton("Play");
        instructions = new JButton("Instructions");
        exit = new JButton("Exit");
        menu = new JButton("Main Menu");
        next = new JButton("Next");
        
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
        
        JLabel subText = new JLabel("Please read instructions before playing");
        subText.setFont(subtitleFont);
        subText.setAlignmentX(center);
        
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
        gameScreen.setLayout(new BorderLayout());
        gameScreen.setBorder(new EmptyBorder(5,5,5,5));
        
        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.setPreferredSize(new Dimension(gameScreen.getWidth(), 50));
        
        JPanel nextPanel = new JPanel(new BorderLayout());
        nextPanel.setPreferredSize(new Dimension(gameScreen.getWidth(), 100));
        
        gameScreen.add(scorePanel, BorderLayout.NORTH);
        gameScreen.add(gamePanel, BorderLayout.CENTER);
        gameScreen.add(nextPanel, BorderLayout.SOUTH);
        
        JLabel scoreLabel = new JLabel("Score: " + score.getCurrentScore());
        JLabel errorLabel = new JLabel("Errors: " + score.getCurrentErrors());
        JLabel time = new JLabel("Time remaining: ");
        
        scorePanel.add(scoreLabel, BorderLayout.WEST);
        scorePanel.add(errorLabel, BorderLayout.EAST);
        scorePanel.add(time, BorderLayout.CENTER);
        
        
        next.setPreferredSize(new Dimension(300, 150));
        nextPanel.add(next, BorderLayout.CENTER);
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
            logic.startGame();
        }
    }
    

    
    
}
