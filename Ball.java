package zzPingPong;

import java.awt.*;
public class Ball extends Thread  {
    private GamePanel gamePanel;
    private final int ballDiameter = 25;
    private int x = GamePanel.width/2 - (ballDiameter/2);
    private int y = GamePanel.height/2;
    private int velocityX = 4;
    private int velocityY = 4;
    private Rectangle ballRectangle;

    Ball (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        ballRectangle = new Rectangle(x, y, ballDiameter, ballDiameter);
    }
    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(x, y, ballDiameter, ballDiameter);
    }

    public void run(){
        while (StartCount.countStart > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            StartCount.countStart--;
            gamePanel.repaint();
        }

        while(true) {
            if(y > 768 - ballDiameter || y < 0) velocityY *= -1;

            if(gamePanel.paddle1.paddleRectangle.intersects(ballRectangle)){
                x = x + 8;
                velocityX *= -1;
            }
            if(gamePanel.paddle2.paddleRectangle.intersects(ballRectangle)){
                x = x - 8;
                velocityX *= -1;
            }

            if(x <= 0 - ballDiameter || x >= ballDiameter + GamePanel.width){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(x <= 0 - ballDiameter) Score.scorePaddle2 ++;
                else Score.scorePaddle1++;

                x = GamePanel.width / 2 - (ballDiameter/2);
                y = GamePanel.height / 2;
            }

            ballRectangle.setLocation(x,y);
            x += velocityX;
            y += velocityY;
            gamePanel.repaint();
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

