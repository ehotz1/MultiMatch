package multimatch;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Ethan
 */
public class SnapBox extends Rectangle {
    private GamePanel panel;
    
    
    public SnapBox(int x, int y, GamePanel gp) {
        this.x = x;
        this.y = y;
        this.width = 200;
        this.height = 200;
        
        this.panel = gp;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
    
    public Block getContainingBlock() {
        for (Block block : panel.getBlocks()) {
            if (this.contains(block)) {
                return block;
            }
        }
        
        return null;
    }
    
    
    
    
}
