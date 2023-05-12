import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle extends Thread implements KeyListener{
    private int x;
    private int y;
    private int velocityY = 2;
    private final int width = 20;
    private final int height = 150;
    private boolean upPressed;
    private boolean downPressed;
    private GamePanel gamePanel;
    private final int keyCodeUp;
    private final int keyCodeDown;
    Color color;
    Rectangle paddleRectangle;

    Paddle (int x, int y, GamePanel gamePanel, int keyCodeUp, int keyCodeDown, Color color){
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
        this.keyCodeUp = keyCodeUp;
        this.keyCodeDown = keyCodeDown;
        this.color = color;
        paddleRectangle = new Rectangle(x,y,width,height);
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == keyCodeUp){
            upPressed = true;
        } else if (e.getKeyCode() == keyCodeDown) {
            downPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == keyCodeUp) {
            upPressed = false;
        } else if (e.getKeyCode() == keyCodeDown) {
            downPressed = false;
        }
    }
    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y,width,height);
    }

    public void run(){
        while(true){
            if (upPressed) {
                moveUp();
            }if (downPressed) {
                moveDown();
            }
            paddleRectangle.setLocation(x,y);
            gamePanel.repaint();
            try {
                Thread.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private void moveUp() {
        if (y > 0) {
            y -= velocityY;
        }
    }
    private void moveDown() {
        if (y + height < gamePanel.getHeight()) {
            y += velocityY;
        }
    }
}