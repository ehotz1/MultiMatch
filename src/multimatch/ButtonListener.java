package multimatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Ethan
 */
public class ButtonListener implements ActionListener {
    private UserInterface GUI;
    private JButton play;
    private JButton instructions;
    private JButton exit;
    private JButton menu;
    private JButton next;
    private JButton save;
    
    private Logic logic;
    
    
    
    public ButtonListener(UserInterface form, JButton play, JButton instructions, 
            JButton exit, JButton menu, Logic logic, JButton next, JButton save) {
        this.GUI = form;
        this.play = play;
        this.instructions = instructions;
        this.exit = exit;
        this.menu = menu;
        this.save = save;
        
        this.logic = logic;
        this.next = next;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == exit) {
            
            System.exit(0);
        }
        if (source == next) {
            logic.checkAnswer();
        }
        if (source == save) {
            System.exit(0);
        }
        GUI.changeScreen(source);
    }
    
    
}
