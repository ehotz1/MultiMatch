
package multimatch;

import java.util.ArrayList;


/**
 *
 * @author Ethan
 */
public class Logic {
    private ArrayList blocks = new ArrayList();
    
    
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
                blocks.add(new Block(array[i]));
            }
        } else {
            array[2] = product;
            for (int i = 0; i < 3; i++) {
                blocks.add(new Block(array[i]));
            }
        }
    }
    
    private int getOperand() {
        return (int)(Math.random() * 10);
    }
}
