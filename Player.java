import java.awt.*;
public class Player extends GameObject{
    Handler handler;
    private Game game;
    public static int playerScore = 0;
    public Player(int width, int height, int speedX, int speedY, int posx, int posy, Color c, ID id, Handler handler, Game game) {
        super(width, height, speedX, speedY, posx, posy, c, id);
        this.handler = handler;
        this.game = game;
        
    }

    public void tick(){
        posx+= speedX;
        posy += speedY;

        

        posx = Game.clamp(posx, 0, Game.WIDTH-100);
        posy = Game.clamp(posy, 0, Game.HEIGHT-100);
        

       for(int i = 0; i < handler.objects.size(); i++){
           GameObject obj = handler.objects.get(i);
            //height, spike1, spike2
            if (obj.getID() == ID.Spike){
                if (getBounds().intersects(obj.getBounds())){
                    System.out.println("creepers");
                    if (Options.algorithmMode) {
                        playerScore = HUD.score;
                        game.gameState = Game.State.ComputerTurn;
                    }
                    else game.gameState = Game.State.End;
                }
            }
       }
    }

    public void render(Graphics g){
        g.setColor(c);
        g.fillRect(posx, posy, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(posx, posy, 64, 64);
    }
    
    
}
