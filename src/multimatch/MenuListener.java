package multimatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Ethan
 */
public class MenuListener implements ActionListener {
    Forms form;
    JButton play;
    JButton instructions;
    JButton exit;
    JButton menu;
    
    
    public MenuListener(Forms form, JButton play, JButton instructions, JButton exit, JButton menu) {
        this.form = form;
        this.play = play;
        this.instructions = instructions;
        this.exit = exit;
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == exit) {
            
            System.exit(0);
        }
        form.changeScreen(source);
    }
    
    
}
