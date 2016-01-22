package multimatch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Ethan
 */
public class Block {
    private String number;
    private int x;
    private int y;
    private Color color;
    private Color contrast;
    private Font font = new Font(Font.SERIF, Font.BOLD, 100);
    
    public Block(int num, int x, int y) {
        this.number = num+"";
        this.x = x;
        this.y = y;
        color = randomColor();
        contrast = getContrastColor(color);
    }
    
    private Color randomColor() {
        int red = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        return new Color(red, blue, green);
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y, 150, 150);
        g.setColor(contrast);
        g.setFont(font);
        g.drawString(number, x + 50, y + 100);
    }
    
    public Color getContrastColor(Color color) {
        double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        return y >= 128 ? Color.black : Color.white;
    }
    
    
}
