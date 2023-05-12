import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel {
    Ball ball;
    Paddle paddle1;
    Paddle paddle2;
    static final int width = 1366;
    static final int height = 768;
    Score score;
    StartCount startCount;
    GamePanel(){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        paddle1 = new Paddle(0,height/2-75, this, 87, 83,Color.PINK);
        paddle2 = new Paddle(width-20, height/2-75, this, 101, 97, Color.green);

        score = new Score(this);
        ball = new Ball(this);
        startCount = new StartCount(this);

        addKeyListener(paddle1);
        addKeyListener(paddle2);
        setFocusable(true);
        requestFocusInWindow();
        ball.start();
        paddle1.start();
        paddle2.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        score.draw(g);
        ball.draw(g);
        paddle1.draw(g);
        paddle2.draw(g);
        if(StartCount.countStart > 0) startCount.draw(g);
    }
}
