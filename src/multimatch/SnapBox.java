package multimatch;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Ethan
 */
public class SnapBox extends Rectangle {
    
    public SnapBox(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 200;
        this.height = 200;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
    
    
    
    
}
