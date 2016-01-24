import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

// top-level container class
class Breakout {

    // constructor for the game
    // instantiates all of the top-level classes (model, view)
    // and tells the model to start the game
    Breakout() {
        Model model = new Model();
        View view = new View();
        // Splash Window
        // Splashwin splash = new Splashwin();
        JFrame f = new JFrame("Breakout"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600); // window size

        //SimpleDraw canvas = new SimpleDraw();
        f.setContentPane(view); // add canvas to jframe
        view.setLayout(new FlowLayout(FlowLayout.LEADING));
        f.setLocationRelativeTo(null);
        f.setVisible(true); // show the window
    }


    // model keeps track of game state (objects in the game)
    // contains a Timer that ticks periodically to advance the game
    // AND calls an update() method in the View to tell it to redraw
    class Model extends JComponent{
        Block blocks[];
        Ball b;
        Paddle p;
        public Model(){
            b = new Ball();
            p = new Paddle();
        }

        public Block[] getBlocks(){
            return blocks;
        }

        public void paintBall(Graphics g, JComponent view){
            b.paintBall(g,view);
        }

        public void paintPaddle(Graphics g, JComponent view){
            p.paintPaddle(g,view);
        }

    }

    // game window
    // draws everything based on the game state
    // receives notification from the model when something changes, and
    // draws components based on the model.
    class View extends JComponent {

        //constructor of View
        public View() {

        }
        public void paintComponent(Graphics g) {

            Model model = new Model();
            Graphics2D g2 = (Graphics2D) g;               // 2D drawing
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            ArrayList<Block> BlockList = new ArrayList<Block>();
            for(int i=0;i<10;i++) {
                BlockList.add(i, new Block());
            }
            for(Block block:BlockList){
                int j=1;    // row 1-5
                for(int i=0;i<50;i++){
                    if(i%10==0) j++;
                    block.paintBlock(g,this,i,j);
                }
            }

            model.paintBall(g, this);

            model.paintPaddle(g,this);
        }
    }

    // entry point for the application
    public static void main(String[] args) {
        Breakout game = new Breakout();
    }
}
