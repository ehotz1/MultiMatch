package multimatch;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Ethan
 */
public class RoundInterface {
    private int roundNumber;
    private ButtonGroup group;
    private JPanel panel;
    private String name = null;
    private listener listener;
    
    public RoundInterface() {

        listener = new listener();
        group = new ButtonGroup();
        GUI();
    }
    
    private void GUI() {
        panel = new JPanel();
        panel.setSize(200,300);
        panel.setLayout(new GridLayout(5,1));
        
        panel.add(new JLabel("Please choose the number of rounds:"));
        
        JRadioButton one = new JRadioButton("1", false);
        one.addItemListener(listener);
        JRadioButton two = new JRadioButton("2", false);
        two.addItemListener(listener);
        JRadioButton three = new JRadioButton("3", false);
        three.addItemListener(listener);
        
        
        group.add(one);
        group.add(two);
        group.add(three);
        
        
        panel.add(one);
        panel.add(two);
        panel.add(three);
        
        panel.add(new JLabel("Please enter your name:"));
        
        name = JOptionPane.showInputDialog(null, panel);
    }
    
    public int returnRounds() {
        return this.roundNumber;
    }

    public String getName() {
        return this.name;
    }
    
    
    class listener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            JRadioButton source = (JRadioButton) e.getSource();
            roundNumber = Integer.parseInt(source.getText());
        }
        
    }
}

