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
    int fps,speed;
    // constructor for the game
    // instantiates all of the top-level classes (model, view)
    // and tells the model to start the game
    Breakout(int fps_, int bspeed_) {
        //initialization
        fps = fps_;
        speed = bspeed_;
        Model model = new Model(fps,speed);
        View view = new View(model);

        // update the view
        model.addObserver(view);
        model.notifyObservers();

        // Splash Window
  //      Splashwin splash = new Splashwin();

        //setting up the game window
        JFrame f = new JFrame("Breakout"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600); // window size
        f.setContentPane(view); // add canvas to jframe
        view.setLayout(new FlowLayout(FlowLayout.LEADING));
        f.setLocationRelativeTo(null);
        f.setVisible(true); // show the window

      //  JOptionPane.showMessageDialog(f,"Click to start game ","Start Game",JOptionPane.PLAIN_MESSAGE);

        f.addKeyListener(model);
        f.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("clicked");
                if(model.pause) model.start();
                else model.pause();
            }
        });

    }

    double xmove = -1;
    double ymove = -1;

    // model keeps track of game state (objects in the game)
    // contains a Timer that ticks periodically to advance the game
    // AND calls an update() method in the View to tell it to redraw
    class Model extends Observable implements KeyListener{
        int fps,speed,score;
        //create new ball, paddle and timer
        Ball ball;
        Paddle paddle;
        Timer timer;
        Timer balltimer;
        ArrayList<Block> BlockList = new ArrayList<Block>();


        //initialize the left and right key
        boolean  leftKey = false, rightKey = false, pause = true;

        //constructor of model
        public Model(int fps_, int speed_){
            ball = new Ball(50,94,23);
            paddle = new Paddle(50, 95, 78, 10);

            fps = fps_;
            speed = speed_;

            int j=0;    // row 1-5
            int k=0;
            for(int i=0;i<50;i++) {
                if(i%10==0) {
                    j++;
                    k=0;
                }
                k++;
                BlockList.add(i, new Block((40+1)*k,30*j,40,30));
            }

            timer = new Timer((1/fps)*1000,timerListener);
            balltimer = new Timer((1/speed)*1000,timerballListen);

        }

        public void start(){
            pause = false;
            timer.start();
            balltimer.start();
        }

        public void pause(){
            pause = true;
            balltimer.stop();
            timer.stop();
        }


        ActionListener timerListener = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers();
            }};

        ActionListener timerballListen = new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                ball.x-=xmove;
                ball.y+=ymove;
                if((ball.y/100) <= 0.00) {
                    System.out.println("top-wall");
                    ymove = -ymove;
                }
                if((ball.x/100) <= 0.00 || ball.x/100 >=1.00 ){
                    System.out.println("left-right-wall");
                    xmove = -xmove;
                }

                double pright = (paddle.x/100) + (paddle.getPwidth()/2);
                double ptop = paddle.y/100;
                double bbot = (ball.y/100);
                double xleft=(bbot*100) - (ptop*100);

                if((xleft<=1.00 && xleft>0.00) && (ball.x+(ball.getdiam()*100)) >= paddle.x && (ball.x/100) <= pright){
                    System.out.println("hit paddle");
                    ymove =-Math.abs(ymove);
                }

                if((ball.y/100) >= 1.15){
                    balltimer.stop();
                }

                for(int i=0;i<BlockList.size();i++){
                    //this for loop will go through the blocks abd see
                    //if the ball intersects with a block or not
                   // System.out.println(i);

                    double topLeftX = BlockList.get(i).x;
                    double topLeftY = BlockList.get(i).y;
                    double botrightX = BlockList.get(i).x + BlockList.get(i).h ;
                    double botrightY =BlockList.get(i).y + BlockList.get(i).w;
                    boolean gethit = intersectCheck(topLeftX*2.1/10,topLeftY*2.1/10,botrightX*2.1/10,botrightY*2.1/10);
                    if(gethit){ //block intersect with ball
                        ymove = -ymove;
                        BlockList.remove(i);
                        score+=10;
                    }
                }
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

            System.out.println(paddle.x);
            if(key == KeyEvent.VK_LEFT && paddle.x >=10) {
                moveLeft();
                leftKey = true;
            }
            if(key == KeyEvent.VK_RIGHT && paddle.x <= 90) {
                moveRight();
                rightKey = true;
            }
            if(key == KeyEvent.VK_SPACE) {
                if(!pause){
                    pause = true;
                    pause();
                }else{
                    pause = false;
                    start();
                }
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
            paddle.x-=3;
            System.out.println("paddle.x left "+paddle.x);
        }
        //right key move coordinate
        public void moveRight(){
            paddle.x+=3;
            System.out.println("paddle.x right "+paddle.x);
        }

        //this function checks if the ball intersects with block or not
        public boolean intersectCheck(double x1, double y1, double x2,double y2){
            //x1 and y1 are the coordinates of the top left
            //x2 and y2 are the coordinates of the bottom right
            boolean hit = false;

            double x3 = ball.x;
            double y3 = ball.y;
            double x4 = ball.x + ball.getdiam();
            double y4 = ball.y + ball.getdiam();

            if(!(x2<x3 || x4<x1 || y2<y3 || y4<y1)){
                System.out.println("if stmt");
                hit = true;
            }

            return hit;
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
            model=model_;
        }

        //paint on the view
        public void paintComponent(Graphics g) {

            // 2D drawing
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            //top left corner, score of the game
            g2.drawString("Score: "+model.score,20,20);

            //top right corner, start button

            g2.setColor(Color.BLACK);
            g2.drawString("Start/Pause",4+this.getWidth()*8/10,20);
            g2.setFont(new Font("Arial", Font.PLAIN, 16));

            //draw the blocks
            for (Block block : model.BlockList) {
                int j = 1;    // row 1-5
                for (int i = 0; i < 50; i++) {
                    if (i % 10 == 0) j++;
                    block.paintBlock(g, this, i);
                }
            }

            //draw the ball and paddle
            model.paintBall(g, this);
            model.paintPaddle(g, this);
        }

        @Override //update the view by repainting the canvas
        public void update(Observable arg0, Object arg1){
            repaint();
        }
    }

    // entry point for the application
    public static void main(String[] args) {
        int fps=30,bspeed=10;

        if(args.length >0){
            try {
                fps = Integer.parseInt(args[0]);
                bspeed = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        Breakout game = new Breakout(fps,bspeed);
    }
}
