import java.awt.*;
public class Ball extends Thread  {
    private GamePanel gamePanel;
    private final int ballDiameter = 25;
    private int x = GamePanel.width/2 - (ballDiameter/2);
    private int y = GamePanel.height/2;
    private int velocityX = 4;
    private int velocityY = 4;
    private Rectangle ballRectangle;
    static boolean winMessage;

    Ball (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        ballRectangle = new Rectangle(x, y, ballDiameter, ballDiameter);
    }
    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(x, y, ballDiameter, ballDiameter);
    }
    public void drawWinMessage(Graphics g){
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Consolas", Font.ITALIC, 30));
        if (Score.scorePaddle1 > Score.scorePaddle2){
            g.drawString("Paddle Pink Won!", GamePanel.width / 2 - 140, GamePanel.height / 2 - 50);
        } else {
            g.drawString("Paddle Green Won!", GamePanel.width / 2 - 140, GamePanel.height / 2 - 50);
        }
    }
    public void run(){

        while(true) {
            while (StartCount.countStart > 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                StartCount.countStart--;
                gamePanel.repaint();
            }

            if(y > GamePanel.height - ballDiameter || y < 0) velocityY *= -1;

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

                if(Score.scorePaddle1 == 11 || Score.scorePaddle2 == 11){
                    gamePanel.gameRunning = false;
                    gamePanel.button.setVisible(true);
                    winMessage = true;
                }

                x = GamePanel.width / 2 - (ballDiameter/2);
                y = GamePanel.height / 2;
            }

            ballRectangle.setLocation(x,y);
            if(gamePanel.gameRunning){
                x += velocityX;
                y += velocityY;
            }
            gamePanel.repaint();
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
