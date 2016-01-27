import java.awt.*;
import javax.swing.*;

public class Block extends JComponent{

    public Block(){
    }

    public void paintBlock(Graphics g, JComponent view, int i, int j){
        Graphics2D g2 = (Graphics2D) g;               // 2D drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

       //drawing the blocks
        int yOffSet = 20;
        int xOffSet = view.getWidth()*5/100;
        int blockHeight = view.getHeight()/23;
        int blockWidth = view.getWidth()/11;

        if(i%10==0 || i/10 > 0){ // for row 2-5
            i = i%10;
        }

       int x = xOffSet + (i * blockWidth);
        int y = yOffSet +(j * blockHeight);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(x, y, blockWidth, blockHeight);
        g2.setStroke(g2.getStroke());
        g2.setColor(Color.darkGray);
        g2.fillRect(x, y, blockWidth, blockHeight);

    }

}
