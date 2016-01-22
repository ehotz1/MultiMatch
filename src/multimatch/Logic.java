
package multimatch;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


/**
 *
 * @author Ethan
 */
public class Logic implements MouseListener, MouseMotionListener {
    private ArrayList<Block> blocks = new ArrayList();
    private Forms forms;
    private GamePanel panel;
    
    public Logic(Forms forms, GamePanel panel) {
        this.forms = forms;
        this.panel = panel;
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    public void startGame() {
        roundStart();
    }
    
    
    public void roundStart() {
        newBlocks();
        panel.setList(blocks);
        panel.drawBlocks(panel.getGraphics());
        //Create blocks, display
        
    }
    
    public void newBlocks() {
        int[] array = new int[4];
        blocks.clear();
        array[0] = getOperand();
        array[1] = getOperand();
        int product = array[0] * array[1];
        if (product > 9) {
            array[2] = product/10;
            array[3] = product%10;
            for (int i = 0; i < 4; i++) {
                blocks.add(new Block(array[i], 0, 0));
            }
        } else {
            array[2] = product;
            for (int i = 0; i < 3; i++) {
                blocks.add(new Block(array[i], 0 ,0));
            }
        }
    }
    
    public ArrayList<Block> getBlockList() {
        return this.blocks;
    }
    
    private int getOperand() {
        return (int)(Math.random() * 10);
    }
    
    public void checkAnswer() {
        //if blocks all in spots. flag function? Flag goal = blocks.size(), add/subtract when placed into spots
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
