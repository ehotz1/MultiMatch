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
    
    int blockX;
    int blockY;
    
    public GamePanel() {
        super.setBackground(Color.WHITE);
        
        
    }
    
    public void setList(ArrayList<Block> list) {
        this.list = list;
        blockX = 200;
        blockY = 100;
    }
    
    public ArrayList<Block> getList() {
        return this.list;
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
            block.setXY(blockX, blockY);
            block.draw(g);
            blockX += 200;
        }
    }
    
}
