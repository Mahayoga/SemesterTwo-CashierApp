/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Demo;

import Form.MenuUtama;
import javax.swing.JPanel;

/**
 *
 * @author Mahayoga
 */
public class DemoMenuUtama {
    static String role = "A";
    MenuUtama demo;
    
    public void main() {
        demo = new MenuUtama(role);
        demo.setVisible(true);
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public void batal(JPanel jp) {
        demo.supplierBatalBtn(jp);
    }
}
