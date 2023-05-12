import java.awt.*;
public class StartCount {
    private GamePanel gamePanel;
    static int countStart = 3;
    StartCount (GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void draw(Graphics g){
        g.setColor(new Color(0xFF0DAACC, true));
        g.setFont(new Font("Consolas", Font.ITALIC, 120));
        g.drawString("" + countStart, gamePanel.width / 2 - (120/2 - 24), gamePanel.height / 2);
    }
}
