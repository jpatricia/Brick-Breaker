import javax.swing.*;
import java.awt.*;

/**
 * Created by Janice on 1/22/2016.
 */
class Splashwin extends JComponent {

    public Splashwin(){
        //create new Splash window
        JWindow jwin = new JWindow();
        //Time t = new Time();

        //create text for Splash window
        JLabel title = new JLabel("Welcome to Breakout Game!");
        JLabel text = new JLabel("Name: Janice Patricia");
        JLabel text1 = new JLabel("User ID: jpatrici");
        JLabel text2 = new JLabel("<html>Instructions:&nbsp The goal of this game is to break the blocks with the ball.<br>" +
                "&emsp &emsp &emsp &emsp &nbsp Move the paddle to the left or to the right with the left and right key<br>"+
                "&emsp &emsp &emsp &emsp &nbsp and point it to the right directions to make the ball hit the blocks.<br>"+
                "&emsp &emsp &emsp &emsp &nbsp Make sure not to drop the ball and have fun!</html>");

        //create panel for Splash window and set location&size of the labels
        JPanel panel = new JPanel(null);
        title.setLocation(125, 30);
        text.setLocation(27, 90);
        text1.setLocation(27, 110);
        text2.setLocation(27, 130);
        //   t.setLocation(50,130);
        title.setSize(380, 50);
        text.setSize(200, 25);
        text1.setSize(200, 25);
        text2.setSize(700, 95);
        //   t.setSize(100,20);

        //add labels to panel
        panel.add(title);
        panel.add(text);
        panel.add(text1);
        panel.add(text2);
        //panel.add(t);

        title.setFont(new Font("Arial", Font.BOLD, 23));
        text.setFont(new Font("Arial", Font.PLAIN, 16));
        text1.setFont(new Font("Arial", Font.PLAIN, 16));
        text2.setFont(new Font("Arial", Font.PLAIN, 16));
        title.setForeground(Color.WHITE);
        text.setForeground(Color.WHITE);
        text1.setForeground(Color.WHITE);
        text2.setForeground(Color.WHITE);

        //set the content pane - add
        jwin.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setBackground(Color.DARK_GRAY);
        jwin.setBounds(520, 320, 580, 300);
        jwin.setVisible(true);

        //set up the time for splash window to open
        try {
            Thread.sleep(16000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        jwin.setVisible(false);
        jwin.dispose();
    }
}
