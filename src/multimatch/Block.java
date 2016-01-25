package multimatch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Ethan
 */
public class Block extends Rectangle {
    private int number;
    private Color color;
    private Color contrast;
    private boolean snapped = false;
    private final Font font = new Font(Font.SERIF, Font.BOLD, 100);
    
    public Block(int num, int x, int y) {
        this.number = num;
        this.x = x;
        this.y = y;
        this.width = 150;
        this.height = 150;
        color = randomColor();
        contrast = getContrastColor(color);
    }
    
    private Color randomColor() {
        int red = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        return new Color(red, blue, green);
    }
    
    public Color getContrastColor(Color color) {
        double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        return y >= 128 ? Color.black : Color.white;
    }
    
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public void isSnapped() {
        this.snapped = true;
    }
    
    public void unSnap() {
        this.snapped = false;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y, width, height);
        g.setColor(contrast);
        g.setFont(font);
        g.drawString(number+"", x + 50, y + 100);
    }
    
    
    
    
}
