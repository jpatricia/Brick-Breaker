import java.awt.*;
import javax.swing.*;

public class Block extends JComponent{
    double x,y,w,h;
    public Block(double x_, double y_, double w_, double h_){
        x=x_;
        y=y_;
        w=w_;
        h=h_;
    }

    public void paintBlock(Graphics g, JComponent view, int i){
        Graphics2D g2 = (Graphics2D) g;               // 2D drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

       //drawing the blocks

        int blockHeight =(int)((h/500)* view.getHeight());
        int blockWidth = (int) ((w/500)*view.getWidth());


        int block_x = (int) ((x/500)*view.getWidth());
        int block_y = (int) ((y/500)*view.getHeight());

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(block_x, block_y, blockWidth, blockHeight);
        g2.setStroke(g2.getStroke());

        System.out.println(i);
        if(y <=40 || (y>=80 && y<=100) || (y>=140 && y<=160)){
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(block_x,block_y,blockWidth, blockHeight);
        }

        else {
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(block_x,block_y,blockWidth, blockHeight);
        }

    }

}
