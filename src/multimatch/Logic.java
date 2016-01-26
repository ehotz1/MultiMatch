
package multimatch;

import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Ethan
 */
public class Logic {
    private ArrayList<Block> blocks = new ArrayList();
    
    private GamePanel panel;
    
    public Logic(GamePanel panel) {
        
        this.panel = panel;
        
    }
    
    public void startGame() {
        roundStart();
    }
    
    
    public void roundStart() {
        newBlocks();
        panel.setNewList(blocks);
        panel.drawNewBlocks(panel.getGraphics());
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
        Collections.shuffle(blocks);
    }
    
    public ArrayList<Block> getBlockList() {
        return this.blocks;
    }
    
    private int getOperand() {
        return (int)(Math.random() * 10);
    }
    
    public void checkAnswer() {
        
    }

    public void checkSnaps() {
        int flag = 0;
        for (Block block : blocks) {
            if (block.isSnapped()) {
                flag++;
            }
        }
        if (flag == blocks.size()) {
            //next.setEnabled(true);
        }
        
    }
}
