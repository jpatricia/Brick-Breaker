import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball extends JComponent{
    double x,y,d;
    public Ball(double x_, double y_, double d_){
        x = x_;
        y = y_;
        d = d_;
    }
    public void paintBall(Graphics g, JComponent view) {

        Graphics2D g2 = (Graphics2D) g;               // 2D drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //drawing the ball
        int xOffset= (int)(view.getWidth()*x/100);
        int yOffset= (int)(view.getHeight()*y/100);
        int diameter= view.getWidth()*2/100;

        d = diameter;

      //  Ellipse2D shape = new Ellipse2D.Float();
      //  shape.setFrame(xOffset-(diameter-7),yOffset-diameter,diameter,diameter);
        g2.setColor(Color.RED);
        g2.fillRoundRect(xOffset-diameter,yOffset-diameter,diameter,diameter,30,30); // draw and fill paddle with orange

    }

    public double getdiam(){
        return d/100;
    }

}
