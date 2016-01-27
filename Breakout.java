import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.Observable;
import java.util.Observer;

// top-level container class
class Breakout {

    // constructor for the game
    // instantiates all of the top-level classes (model, view)
    // and tells the model to start the game
    Breakout() {
        Model model = new Model();
        View view = new View(model);

        // update the view
        model.addObserver(view);
        model.notifyObservers();

        // Splash Window
        // Splashwin splash = new Splashwin();

        //setting up the game window
        JFrame f = new JFrame("Breakout"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600); // window size
        f.setContentPane(view); // add canvas to jframe
        view.setLayout(new FlowLayout(FlowLayout.LEADING));
        f.setLocationRelativeTo(null);
        f.setVisible(true); // show the window

        f.addKeyListener(model);

    }

    // model keeps track of game state (objects in the game)
    // contains a Timer that ticks periodically to advance the game
    // AND calls an update() method in the View to tell it to redraw
    class Model extends Observable implements KeyListener{

        //create new ball, paddle and timer
        Ball ball;
        Paddle paddle ;
        Timer timer;



        //initialize the left and right key
        boolean  leftKey = false, rightKey = false;

        //constructor of model
        public Model(){
            ball = new Ball(0.50,0.94);
            paddle = new Paddle(50, 95);
            timer = new Timer(25,timerListener);
            timer.start();
        }

        ActionListener timerListener = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                ball.x +=0.001;
                ball.y -=0.001;
                setChanged();
                notifyObservers();
            }
        };

        public void paintBall(Graphics g, JComponent view){
            ball.paintBall(g,view);
        }

        public void paintPaddle(Graphics g, JComponent view){
            paddle.paintPaddle(g,view);
        }

        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT) {
                moveLeft();
                leftKey = true;
                setChanged();
                notifyObservers();
            }
            if(key == KeyEvent.VK_RIGHT) {
                moveRight();
                rightKey = true;
                setChanged();
                notifyObservers();
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT){
                leftKey = false;
            }
            if(key == KeyEvent.VK_RIGHT) {
                rightKey = false;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        // left key move coordinate
        public void moveLeft(){
            paddle.x--;
            System.out.println("paddle.x left "+paddle.x);
        }
        //right key move coordinate
        public void moveRight(){
            paddle.x++;
            System.out.println("paddle.x right "+paddle.x);
        }

    }

    // game window
    // draws everything based on the game state
    // receives notification from the model when something changes, and
    // draws components based on the model.
    class View extends JComponent implements Observer{
        private Model model;

        //constructor of View
        public View(Model model_) {
            model =model_;
        }

        //paint on the view
        public void paintComponent(Graphics g) {

            // 2D drawing
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            //draw the blocks
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

            //draw the ball and paddle
            model.paintBall(g, this);
            model.paintPaddle(g,this);

            //actionListener for the ball
//            model.b.addActionListener(new ActionListener(){
//               public void actionPerformed(ActionEvent e){
//                   System.out.println("ball action listener");
//               }
//            });
        }

        @Override //update the view by repainting the canvas
        public void update(Observable arg0, Object arg1){
            repaint();
        }
    }

    // entry point for the application
    public static void main(String[] args) {
        Breakout game = new Breakout();
    }
}
