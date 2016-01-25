package multimatch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 *
 * @author Ethan
 */
public class GamePanel extends JPanel {
    private final JButton next;
    ArrayList<Block> blockList;
    ArrayList<SnapBox> boxList;
    int blockX;
    int blockY;
    
    
    public GamePanel() {
        boxList = new ArrayList();
        next = new JButton("Next");
        setLayout(new BorderLayout());
        nextButton();
    }
    
    private void nextButton() {
        JPanel nextPanel = new JPanel(new BorderLayout());
        nextPanel.setPreferredSize(new Dimension(this.getWidth(), 100));
        nextPanel.add(next, BorderLayout.CENTER);
        this.add(nextPanel, BorderLayout.SOUTH);
    }
    
    
    public void setList(ArrayList<Block> list) {
        this.blockList = list;
        blockX = 150;
        blockY = 100;
    }
    
    public ArrayList<Block> getBlocks() {
        return this.blockList;
    }
    
    public ArrayList<SnapBox> getBoxes() {
        return this.boxList;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (blockList != null) {
            for (Block block : blockList) {
                block.draw(g);
            }
        }
        staticElements(g);
    }
    
    private void staticElements(Graphics g) {
        int boxHeight = 300;
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 42));
        
        SnapBox op1 = new SnapBox(this.getWidth() / 10, boxHeight);
        op1.draw(g);
        g.drawString("x", op1.x + op1.width + 10, boxHeight + 100);
        SnapBox op2 = new SnapBox(op1.x + op1.width + 50, boxHeight);
        op2.draw(g);
        g.drawString("=", op2.x + op2.width + 10, boxHeight + 100);
        SnapBox prod1 = new SnapBox(op2.x + op2.width + 50, boxHeight);
        prod1.draw(g);
        
        boxList.add(op1);
        boxList.add(op2);
        boxList.add(prod1);
        
        if (blockList.size() == 4) {
            SnapBox prod2 = new SnapBox(prod1.x + prod1.width + 25, boxHeight);
            prod2.draw(g);
            boxList.add(prod2);
        }
        
    }
    
    
    public void drawNewBlocks(Graphics g) {
        for (Block block : blockList) {
            block.setXY(blockX, blockY);
            block.draw(g);
            blockX += 200;
        }
    }
    
    
    
}
