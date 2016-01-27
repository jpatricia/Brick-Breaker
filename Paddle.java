import javax.swing.*;
import java.awt.*;

public class Paddle extends JComponent {
    double x,y;
    // Constructor for SimpleDraw
    public Paddle(double x_, double y_) {
        x = x_;
        y = y_;
    }

    public void paintPaddle(Graphics g, JComponent view) {

        Graphics2D g2 = (Graphics2D) g;               // 2D drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        System.out.println("x "+x);
        System.out.println("y "+y);
        //drawing the paddle
        int height = view.getHeight()*2/100;        // height of paddle
        int width = view.getWidth()*10/100;         // width of paddle
        int tempx = (int)(view.getWidth()*x/100);         // x coordinates
        int tempy = (int)(view.getHeight()*y/100);        // y coordinates

        System.out.println("height "+height);
        System.out.println("width "+width);
        System.out.println("tempx "+tempx);
        System.out.println("(w/2) "+(width/2));

        g2.setColor(Color.orange);
        g2.fillRoundRect(tempx-(width/2),tempy,width,height,15,15); // draw and fill paddle with orange
    }
}