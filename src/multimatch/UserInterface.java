
package multimatch;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Ethan
 */
public class UserInterface {
    
    private JFrame mainFrame;
    
    private JPanel cardContainer;
    private JPanel mainScreen;
    private JPanel gameScreen;
    private JPanel instructionScreen;
    private JPanel scoreScreen;
    private GamePanel gamePanel;
    
    private final JButton exit;
    private final JButton play;
    private final JButton instructions;
    private final JButton menu;
    private final JButton next;
    private final JButton save;
    
    private JLabel scoreText;
    private JLabel scoreLabel;
    private JLabel errorLabel;
    private JLabel timeLabel;
    private JLabel roundCountdown;
    private JLabel scoreTotals;
    
    private Logic logic;
    private ButtonListener buttonListener;
    private MouseListener mouseListener;
    private Timer roundTimer;
    private int timeBetweenRounds;
    private int tick;
    
    //Fonts, layouts
        Font titleFont = new Font(Font.SERIF, Font.BOLD, 100);
        Font subtitleFont = new Font(Font.SERIF, Font.BOLD, 36);
        Font instructionFont = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
        float center = Component.CENTER_ALIGNMENT;
        CardLayout cardLayout = new CardLayout();
        
    
    public UserInterface() {
        mainFrame = new JFrame("MultiMatch");
        RoundInterface roundDialog = new RoundInterface();
        if (roundDialog.returnRounds() == 0) {
            JOptionPane.showMessageDialog(null, "No rounds chosen, game will now close.");
            System.exit(0);
        }
        gamePanel = new GamePanel();
        logic = new Logic(gamePanel, this, roundDialog.returnRounds());
        timeBetweenRounds = 10;
        
        play = new JButton("Play");
        instructions = new JButton("Instructions");
        exit = new JButton("Exit");
        menu = new JButton("Main Menu");
        next = new JButton("Next");
        save = new JButton("Close Game");
        
        buttonListener = new ButtonListener(this, play, instructions, exit, menu, logic, next, save);
        mouseListener = new MouseListener(gamePanel);
        
        createGUI();
    }
    
    private void createGUI() {
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setMinimumSize(new Dimension(640, 480));
        mainFrame.setExtendedState(MAXIMIZED_BOTH);
        //mainFrame.setUndecorated(true);
        
        cardContainer = new JPanel(cardLayout);
        
        MainMenu();
        InstructionScreen();
        GameScreen();
        scoreScreen();
        
        cardContainer.add(mainScreen, "main");
        cardContainer.add(instructionScreen, "inst");
        cardContainer.add(gameScreen, "game");
        cardContainer.add(scoreScreen, "score");
        mainFrame.add(cardContainer);
        
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    private void MainMenu() {
        mainScreen = new JPanel();
        mainScreen.setLayout(new BoxLayout(mainScreen, BoxLayout.Y_AXIS));
        Component space = Box.createVerticalStrut(50);
        Dimension buttonSize = new Dimension(150,75);
        
        JLabel titleText = new JLabel("MultiMatch");
        titleText.setFont(titleFont);
        titleText.setAlignmentX(center);
        
        JLabel subText = new JLabel("Please read instructions before playing");
        subText.setFont(subtitleFont);
        subText.setAlignmentX(center);
        
        play.setEnabled(false);
        play.setAlignmentX(center);
        play.setMinimumSize(buttonSize);
        instructions.setAlignmentX(center);
        instructions.setMinimumSize(buttonSize);
        exit.setAlignmentX(center);
        exit.setMinimumSize(buttonSize);
        
        instructions.addActionListener(buttonListener);
        play.addActionListener(buttonListener);
        exit.addActionListener(buttonListener);
        
        mainScreen.add(titleText);
        mainScreen.add(subText);
        mainScreen.add(space);
        mainScreen.add(play);
        mainScreen.add(Box.createVerticalStrut(10));
        mainScreen.add(instructions);
        mainScreen.add(space);
        mainScreen.add(exit);
    }
    
    private void InstructionScreen() {
        instructionScreen = new JPanel();
        instructionScreen.setLayout(new BoxLayout(instructionScreen, BoxLayout.Y_AXIS));
        JLabel broke = new JLabel("Image not found", JLabel.CENTER);
        
        JLabel text1 = new JLabel("<html><center>The goal of the game is to click and drag the boxes "
                + "so that the multiplication problem is correct, like so:</center></html>",JLabel.CENTER);
        text1.setFont(instructionFont);
        JLabel text2 = new JLabel("<html><center>Once all blocks are in place with a green border, "
                + "click the next button to check your answer.</center></html>", JLabel.CENTER);
        text2.setFont(instructionFont);
        JLabel text3 = new JLabel("<html><center>If correct, you will be presented with a new problem. "
                + "Try to solve as many as you can!</center></html>", JLabel.CENTER);
        text3.setFont(instructionFont);
        
        menu.addActionListener(buttonListener);
        menu.setAlignmentX(center);
        instructionScreen.add(text1);
        try {
            BufferedImage presort = ImageIO.read(UserInterface.class.getResource("../images/presort.png"));
            ImageIcon preIcon = new ImageIcon(presort);
            JLabel preLabel = new JLabel(preIcon,JLabel.CENTER);
            preLabel.setHorizontalAlignment(JLabel.CENTER);
            instructionScreen.add(preLabel);
        } catch (IOException e) {
            instructionScreen.add(broke);
        }
        instructionScreen.add(text2);
        try {
            BufferedImage sorted = ImageIO.read(UserInterface.class.getResource("../images/sorted.png"));
            ImageIcon sortIcon = new ImageIcon(sorted);
            JLabel sortLabel = new JLabel("",JLabel.CENTER);
            sortLabel.setIcon(sortIcon);
            instructionScreen.add(sortLabel);
        } catch (IOException e) {
            instructionScreen.add(broke);
        }
        instructionScreen.add(text3);
        
        instructionScreen.add(menu);
        
        
    }
    
    private void GameScreen() {
        gameScreen = new JPanel();
        gameScreen.setLayout(new BorderLayout());
        gameScreen.setBorder(new EmptyBorder(5,5,5,5));
        
        Font scoreFont = new Font(Font.SERIF, Font.PLAIN, 24);
        
        JPanel scorePanel = new JPanel(new GridLayout(1,3));
        scorePanel.setPreferredSize(new Dimension(gamePanel.getWidth(), 50));
        
        scoreLabel = new JLabel("Score: ", SwingConstants.CENTER);
        scoreLabel.setFont(scoreFont);
        errorLabel = new JLabel("Errors: ", SwingConstants.CENTER);
        errorLabel.setFont(scoreFont);
        timeLabel = new JLabel("Time remaining: ", SwingConstants.CENTER);
        timeLabel.setFont(scoreFont);
        
        scorePanel.add(scoreLabel);
        scorePanel.add(timeLabel);
        scorePanel.add(errorLabel);
        
        JPanel nextPanel = new JPanel(new BorderLayout());
        nextPanel.setPreferredSize(new Dimension(gamePanel.getWidth(), 100));
        nextPanel.add(next, BorderLayout.CENTER);
        next.addActionListener(buttonListener);
        next.setFont(subtitleFont);
        
        gameScreen.add(scorePanel, BorderLayout.NORTH);
        gameScreen.add(gamePanel, BorderLayout.CENTER);
        gameScreen.add(nextPanel, BorderLayout.SOUTH);
    }
    
    public void scoreScreen() {
        scoreScreen = new JPanel();
        scoreScreen.setLayout(new BoxLayout(scoreScreen, BoxLayout.Y_AXIS));
        
        JLabel text1 = new JLabel("Round complete!");
        text1.setFont(titleFont);
        text1.setAlignmentX(center);
        scoreText = new JLabel();
        scoreText.setFont(subtitleFont);
        scoreText.setHorizontalAlignment(JLabel.CENTER);
        scoreText.setVisible(false);
        roundCountdown = new JLabel();
        roundCountdown.setFont(subtitleFont);
        roundCountdown.setAlignmentX(center);
        roundCountdown.setVisible(false);
        scoreTotals = new JLabel();
        scoreTotals.setFont(subtitleFont);
        scoreTotals.setAlignmentX(center);
        scoreTotals.setVisible(false);
        
        save.addActionListener(buttonListener);
        
        scoreScreen.add(text1);
        scoreScreen.add(scoreText);
        scoreScreen.add(roundCountdown);
        scoreScreen.add(scoreTotals);
        scoreScreen.add(save);
    }
    
    
    public void showScores() {
        cardLayout.show(cardContainer, "score");
        scoreText.setText("Your score was " + logic.getScore().getCurrentScore() + " and you committed " + logic.getScore().getCurrentErrors() + " errors.");
        if (logic.getScore().getCurrentRound() < logic.getScore().getTotalRounds()) {
            roundCountdown.setVisible(true);
            save.setVisible(false);
            tick = timeBetweenRounds;
            roundTimer = new Timer();
            roundTimer.scheduleAtFixedRate(new countdown(), 1000, 1000);
        } else {
            roundCountdown.setVisible(false);
            save.setVisible(true);
            scoreTotals.setText("<html><center>Game complete. You achieved an average score of " + logic.getScore().calculateAverageScore()
                    + "<br> and committed an average of " + logic.getScore().calculateAverageErrors() + " errors. <br> Thanks for playing!</center></html>");
            
            scoreTotals.setVisible(true);
            logic.gameEnd();
        }
        
    }
    
    
    public void changeScreen(Object source) {
        if (source == menu) {
            cardLayout.show(cardContainer, "main");
            play.setEnabled(true);
        }
        if (source == instructions) {
            cardLayout.show(cardContainer, "inst");
        }
        
        if (source == play) {
            cardLayout.show(cardContainer, "game");
            logic.newRound();
        }
    }
    
    public void setTime(String time) {
        timeLabel.setText("Time remaining: " + time);
    }
    
    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
    
    public void setErrors(int errors) {
        errorLabel.setText("Errors: " + errors);
    }

    public void nextRound() {
        logic.newRound();
        scoreLabel.setText("Score: 0");
        errorLabel.setText("Errors: 0");
        cardLayout.show(cardContainer, "game");
    }
    
    class countdown extends TimerTask {

        @Override
        public void run() {
            tick--;
            roundCountdown.setText("Time until next round: " + tick + " seconds");
            if (tick == 0) {
                nextRound();
                
            }
        }
        
    }
    
}
