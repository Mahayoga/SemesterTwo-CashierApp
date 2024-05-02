/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Mahayoga
 */
public class CustomButton extends JButton {
    private int borderRadius = 20;
    private String textButton = "JButton";
    private int fontSize = 20;
    private int boldSize = 0;
    private Color textColor =  new Color(0, 0, 0);
    private Color bgColor =  new Color(255, 255, 255);
    
    public CustomButton() {
        this.setText("");
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(200, 80));
    }
    
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.bgColor = backgroundColor;
    }
    
    public void setTheText(String text) {
        this.textButton = text;
    }
    
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    } 
    
    public void setTextBold(int boldSize) {
        this.boldSize = boldSize;
    }
    
    public void setTextColor(Color color) {
        this.textColor = color;
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(bgColor);        
        g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), borderRadius, borderRadius);
        g2.setColor(textColor);
        
        FontMetrics metrics = g2.getFontMetrics(new Font("", boldSize, fontSize));
        int x = (this.getWidth() / 2) - (metrics.stringWidth(textButton) / 2);
        int y = (this.getHeight() / 2) + ((metrics.getHeight() / 2) / 2);
        g2.setFont(new Font("", boldSize, fontSize));
        g2.drawString(textButton, x, y);
    }
}
