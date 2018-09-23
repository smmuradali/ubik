/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiksimdist;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.*;

/**
 *
 * @author SA2305
 */
public class MyDisplay extends JFrame{

    public MyDisplay() throws HeadlessException {
    }

    public MyDisplay(GraphicsConfiguration gc) {
        super(gc);
    }

    public MyDisplay(String title) throws HeadlessException {
        super(title);
    }

    public MyDisplay(String title, GraphicsConfiguration gc) {
        super(title, gc);
    }

    

    

      
}
