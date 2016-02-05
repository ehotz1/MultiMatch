package multimatch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 *
 * @author Ethan
 */
public class GamePanel extends JPanel {
    
    ArrayList<Block> blockList;
    ArrayList<SnapBox> boxList;
    
    private JLabel messageLabel;
    
    int blockX;
    int blockY;
    
    Timer messageTimer;
    
    
    public GamePanel() {
        boxList = new ArrayList();
        
        setGUI();
    }
    
    private void setGUI() {
        setLayout(new BorderLayout());
        
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
        messageLabel.setVisible(false);
        this.add(messageLabel, BorderLayout.CENTER);
        
        
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
        boxList.clear();
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 42));
        
        SnapBox op1 = new SnapBox(this.getWidth() / 10 - 50, boxHeight, this);
        op1.draw(g);
        g.drawString("x", op1.x + op1.width + 10, boxHeight + 100);
        SnapBox op2 = new SnapBox(op1.x + op1.width + 50, boxHeight, this);
        op2.draw(g);
        g.drawString("=", op2.x + op2.width + 10, boxHeight + 100);
        SnapBox prod1 = new SnapBox(op2.x + op2.width + 50, boxHeight, this);
        prod1.draw(g);
        
        boxList.add(op1);
        boxList.add(op2);
        boxList.add(prod1);
        
        if (blockList.size() == 4) {
            SnapBox prod2 = new SnapBox(prod1.x + prod1.width + 25, boxHeight, this);
            prod2.draw(g);
            boxList.add(prod2);
        }
    }
    
    public void setNewList(ArrayList<Block> list) {
        this.blockList = list;
        
    }
    
    
     
    public void drawNewBlocks(Graphics g) {
        blockX = 100;
        blockY = 100;
        for (Block block : blockList) {
            block.setXY(blockX, blockY);
            block.draw(g);
            blockX += 200;
        }
    }
    
    public void incorrect() {
        drawNewBlocks(getGraphics());
        for (Block block : blockList) {
            block.unSnap();
        }
        messageLabel.setForeground(Color.RED);
        quickMessage("INCORRECT");
    }
    
    public void correct() {
        messageLabel.setForeground(Color.GREEN);
        quickMessage("Correct!");
    }
    
    public void blockError() {
        messageLabel.setForeground(Color.RED);
        quickMessage("All blocks must be in slots");
    }
    
    public void quickMessage(String str) {
        messageLabel.setText(str);
        messageLabel.setVisible(true);
        messageTimer = new Timer();
        messageTimer.schedule(new hideMessage(), 2000);
    }
    
    private class hideMessage extends TimerTask {
            @Override
            public void run() {
                messageLabel.setVisible(false);
                messageTimer.cancel();
            }
        }
    
    
    
}
