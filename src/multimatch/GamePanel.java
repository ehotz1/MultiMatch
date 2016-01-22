package multimatch;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
/**
 *
 * @author Ethan
 */
public class GamePanel extends JPanel {
    ArrayList<Block> list;
    int x;
    int y;
    
    public GamePanel() {
        super.setBackground(Color.WHITE);
        super.setSize(WIDTH, 500);
        
    }
    
    public void setList(ArrayList<Block> list) {
        this.list = list;
        x = 100;
        y = 100;
    }
    
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (list != null) {
            for (Block block : list) {
                block.draw(g);
            }
        }
        
        
    }
    
    public void drawBlocks(Graphics g) {
        for (Block block : list) {
            block.setXY(x, y);
            block.draw(g);
            x += 200;
        }
    }
}
