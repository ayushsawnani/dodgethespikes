import java.awt.*;

public class Spike extends GameObject{
    private Handler handler;
    private Game game;
    public Spike(int width, int height, int speedX, int speedY, int posx, int posy, Color c, ID id, Handler handler, Game game) {
        super(width, height, speedX, speedY, posx, posy, c, id);
        this.handler = handler;
        this.game = game;
    }

    public void tick(){
        posx+= speedX;
        posy += speedY;
        posy = Game.clamp(posy, 0, Game.HEIGHT-100);
        if (posx <= 0){
            speedX = -speedX;
        }
        if (posx + 64 >= Game.WIDTH){
            speedX = -speedX;
        }

        for(int i = 0; i < handler.objects.size(); i++){
            GameObject obj = handler.objects.get(i);
             //height, spike1, spike2
             if (obj.getID() == ID.ComputerPlayer){
                 if (getBounds().intersects(obj.getBounds())){
                     System.out.println("jeepers");
                     if (Options.algorithmMode) {
                         ComputerPlayer.computerScore = HUD.score;
                         game.gameState = Game.State.ComputerEnd;
                     }
                     else game.gameState = Game.State.End;
                 }
             }
        }

    }

    public void render(Graphics g){
        g.setColor(c);
        g.fillRect(posx, posy, width, height);
        g.setColor(Color.GREEN);
        g.drawRect(posx, posy, 64, 8);
    }

    public Rectangle getBounds() {
        return new Rectangle(posx, posy, 64, 8);
    }
    
    
}