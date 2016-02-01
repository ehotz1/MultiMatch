package multimatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Ethan
 */
public class ButtonListener implements ActionListener {
    UserInterface GUI;
    JButton play;
    JButton instructions;
    JButton exit;
    JButton menu;
    
    private Logic logic;
    private JButton next;
    
    
    public ButtonListener(UserInterface form, JButton play, JButton instructions, JButton exit, JButton menu, Logic logic, JButton next) {
        this.GUI = form;
        this.play = play;
        this.instructions = instructions;
        this.exit = exit;
        this.menu = menu;
        
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
        GUI.changeScreen(source);
    }
    
    
}
