/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author User
 */
public class CustomPanel extends JPanel {
    private int borderRadius = 20;
    private Color bgColor =  new Color(204, 204, 204);
    
    public CustomPanel() {
        //this.setBorder(new LineBorder(Color.BLACK, 1, true));
    }
    
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.bgColor = backgroundColor;
    } 
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), borderRadius, borderRadius);
    }
}
