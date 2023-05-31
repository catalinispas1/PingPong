import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    Ball ball;
    Paddle paddle1;
    Paddle paddle2;
    static final int width = 1366;
    static final int height = 768;
    Score score;
    StartCount startCount;
    JButton button;
    boolean gameRunning;
    GamePanel(){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
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

        button = new JButton();
        button.setFocusable(false);
        button.setText("Play!");
        button.setBounds((width - 200) / 2, (height - 50) / 2, 200, 50);
        button.setVisible(true);
        button.addActionListener(this);
        this.add(button);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        score.draw(g);
        ball.draw(g);
        paddle1.draw(g);
        paddle2.draw(g);
        if (StartCount.countStart > 0 && gameRunning) startCount.draw(g);
        if (Ball.winMessage) ball.drawWinMessage(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            gameRunning = true;
            Ball.winMessage = false;
            button.setVisible(false);
            Score.scorePaddle1 = 0;
            Score.scorePaddle2 = 0;
            StartCount.countStart = 3;
            button.setText("Play Again!");
        }
    }
}
