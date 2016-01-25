package multimatch;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Ethan
 */
public class MouseListener extends MouseInputAdapter {
    private GamePanel panel;
    private boolean dragging = false;
    private Block currentBlock = null;
    
    
    public MouseListener(GamePanel p) {
        this.panel = p;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        //Detect block being clicked on
        for (Block block : panel.getList()) {
            if (block.contains(p)) {
                dragging = true;
                currentBlock = block;
                break;
            }
        }
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging && currentBlock != null) {
            currentBlock.setXY(e.getX() - 75, e.getY() - 75);
            checkBounds();
            panel.repaint();
        }
    }
    
    public boolean checkBounds() {
        Rectangle area = panel.getBounds();

        if (area.contains(currentBlock.x, currentBlock.y, currentBlock.getWidth(), currentBlock.getHeight())) {
            return true;
        }

        int new_x = currentBlock.x;
        int new_y = currentBlock.y;

        if ((currentBlock.x + currentBlock.getWidth()) > area.getWidth()) {
            new_x = (int) area.getWidth() - (int) (currentBlock.getWidth() - 1);
        }
        if (currentBlock.x < 0) {
            new_x = -1;
        }
        if ((currentBlock.y + currentBlock.getHeight()) > area.getHeight()) {
            new_y = (int) area.getHeight() - (int) (currentBlock.getHeight() - 1);
        }
        if (currentBlock.y < 0) {
            new_y = -1;
        }
        currentBlock.setLocation(new_x, new_y);
        return false;
    }
    

}
