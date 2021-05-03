
import java.awt.*;

public class HUD {
    public static int HEALTH = 100;
    public static int score = 0;
    public void tick(){
        HEALTH = Game.clamp(HEALTH, 0, 100);
        if (HEALTH <= 0){
            System.exit(0);
        }
    }
    public void render(Graphics g){
        g.setColor(Color.blue);
        Font f = new Font("Arial", Font.BOLD, 100);
		g.setFont(f);

        g.drawString("SCORE: " + score, 100, 100);

    }
}
