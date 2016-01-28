import javax.swing.*;
import java.awt.*;

public class Paddle extends JComponent {
    double x,y,w,h;
    // Constructor for SimpleDraw
    public Paddle(int x_, int y_, int w_, int h_) {
        x = x_;
        y = y_;
        w = w_;
        h = h_;
    }

    public void paintPaddle(Graphics g, JComponent view) {

        Graphics2D g2 = (Graphics2D) g;               // 2D drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        //drawing the paddle
        int height = view.getHeight()*2/100;        // height of paddle
        int width = view.getWidth()*16/100;         // width of paddle
        int tempx = (int)(view.getWidth()*x/100);         // x coordinates
        int tempy = (int)(view.getHeight()*y/100);        // y coordinates

        w = width;
        h = height;

        g2.setColor(Color.orange);
        g2.fillRoundRect(tempx-(width/2),tempy,width,height,15,15); // draw and fill paddle with orange
    }

    public double getPwidth(){
        return w/100;  //change it to fraction
    }

}