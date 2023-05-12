package zzPingPong;
import java.awt.*;
public class Score{
    static int scorePaddle1 = 0;
    static int scorePaddle2 = 0;
    private GamePanel gamePanel;
    Score (GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));

        g.drawLine(GamePanel.width / 2, 0, GamePanel.width / 2, GamePanel.height);

        g.drawString("" + scorePaddle1, GamePanel.width/2 - 85, 50);
        g.drawString("" + scorePaddle2, GamePanel.width/2 + 47, 50);
    }
}
