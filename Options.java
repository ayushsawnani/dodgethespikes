import java.awt.*;

public class Options{
    public static boolean algorithmMode;
    public static boolean computersturn;
    public Options(){
        algorithmMode = false;
        computersturn = false;
    }
    public void tick() {

    }
    public void render(Graphics g) {
        Font ft = new Font("Sans_Serif", Font.BOLD, 50);
        g.setColor(Color.BLACK);
        g.setFont(ft);
        g.drawString("VS. Computer", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 50);
        if (!(algorithmMode)) g.setColor(Color.RED);
        else if (algorithmMode) g.setColor(Color.GREEN);

        g.fillRect(Game.WIDTH/2 - 200, Game.HEIGHT/2 - 100, 50, 50);

        g.setColor(Color.BLACK);
        g.drawRect(Game.WIDTH/2 - 100, Game.HEIGHT/2 + 200, 200, 100);
        g.drawString("BACK", Game.WIDTH/2-75, Game.HEIGHT/2 + 270);

    }
}
