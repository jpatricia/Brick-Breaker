import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball extends JComponent{
    double x,y;
    public Ball(double x_, double y_){
        x = x_;
        y = y_;
    }
    public void paintBall(Graphics g, JComponent view) {

        Graphics2D g2 = (Graphics2D) g;               // 2D drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //drawing the ball
        int xOffset= (int)(view.getWidth()*x);
        int yOffset= (int)(view.getHeight()*y);
        int diameter= view.getWidth()*2/100;

        Ellipse2D shape = new Ellipse2D.Float();
        shape.setFrame(xOffset-(diameter-7),yOffset-diameter,diameter,diameter);
        g2.setColor(Color.RED);
        g2.draw(shape); //draw the ball(circle)
        g2.fill(shape); //fill ball with red

    }

}
