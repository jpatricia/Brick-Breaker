import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Created by Janice on 1/22/2016.
 */
public class Paddle extends JComponent {
    // Constructor for SimpleDraw
    public Paddle() {
    }

    public void paintPaddle(Graphics g, JComponent view) {

        Graphics2D g2 = (Graphics2D) g;               // 2D drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        //drawing the paddle
        int height = view.getHeight()*3/100;
        int width = view.getWidth()*10/100;
        int xOffset = view.getWidth()/2;
        int yOffset = view.getHeight()*95/100;
        g2.setColor(Color.orange);
        g2.drawRoundRect(xOffset-(width/2),yOffset,width,height,15,15); // draw the paddle(rounded rectangle)
        g2.fillRoundRect(xOffset-(width/2),yOffset,width,height,15,15); // fill paddle with orange
    }
}