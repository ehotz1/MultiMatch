package multimatch;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Ethan
 */
public class Block implements MouseListener, MouseMotionListener {
    private int number;
    private int x;
    private int y;
    private boolean dragging;
    
    public Block(int num, int x, int y) {
        this.number = num;
        this.x = x;
        this.y = y;
        dragging = false;
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    private Color randomColor() {
        int red = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        return new Color(red, blue, green);
    }
    
    public void paint(Graphics g) {
        g.fillRect(x, y, 200, 200);
    }
    
    //Implement dragging functions

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        dragging = true;
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging) {
            
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
