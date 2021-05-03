import java.awt.event.*;

public class KeyInput extends KeyAdapter{

    private Handler handler;
    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject temp = handler.objects.get(i);

            if (temp.getID() == ID.Player){
                if (!(Options.computersturn)){

                    if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) temp.setSpeedY(-10);
                    if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) temp.setSpeedY(10);
                    if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) temp.setSpeedX(-10);
                    if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) temp.setSpeedX(10);
                }
            }
            
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject temp = handler.objects.get(i);

            if (temp.getID() == ID.Player){
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) temp.setSpeedY(0);
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) temp.setSpeedY(0);
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) temp.setSpeedX(0);
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) temp.setSpeedX(0);
            }
            
        }

    }

}
