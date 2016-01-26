package multimatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Ethan
 */
public class ButtonListener implements ActionListener {
    private Logic logic;
    private JButton next;
    
    public ButtonListener(Logic l, JButton n) {
        this.logic = l;
        this.next = n;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            logic.checkAnswer();
        }
    }
    
}
