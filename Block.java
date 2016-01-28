import java.awt.*;
import javax.swing.*;

public class Block extends JComponent{
    double x,y,w,h;
    boolean hit;
    public Block(double x_, double y_, double w_, double h_){
        x=x_;
        y=y_;
        w=w_;
        h=h_;
        hit = false;
    }

    public void paintBlock(Graphics g, JComponent view, int i, int j){
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
        if(hit == true) clear(g,block_x,block_y,blockWidth, blockHeight);
        else fill(g,block_x,block_y,blockWidth, blockHeight);

    }

    public void clear(Graphics g, double x1, double y1, double w1, double h1){
        System.out.println("clear method");
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect((int)x1,(int)y1,(int)w1,(int)h1);
    }

    public void fill(Graphics g, double x1, double y1, double w1, double h1){
        System.out.println("fill method");
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int)x1,(int)y1,(int)w1,(int)h1);
    }

    public boolean hit(){
        return hit;
    }

}
