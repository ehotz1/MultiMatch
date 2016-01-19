
package multimatch;

import java.util.ArrayList;


/**
 *
 * @author Ethan
 */
public class Logic {
    private ArrayList blocks = new ArrayList();
    
    public void start() {
        
    }
    
    
    public void RoundStart() {
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
        
        //Create blocks, display
        
    }
    
    private int getOperand() {
        return (int)(Math.random() * 10);
    }
    
    public void checkAnswer() {
        //if blocks all in spots. flag function? Flag goal = blocks.size(), add/subtract when placed into spots
    }
}
