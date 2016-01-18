package multimatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Ethan
 */
public class MenuListener implements ActionListener {
    JButton play;
    JButton instructions;
    JButton exit;
    
    
    public MenuListener(JButton play, JButton instructions, JButton exit) {
        this.play = play;
        this.instructions = instructions;
        this.exit = exit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (e.getSource() == exit) {
            
            System.exit(0);
        }
    }
    
    public void test() {
        System.out.println("action listener");
    }
    
}
