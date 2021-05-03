import java.awt.*;

public class ComputerPlayer extends Player{

    private Game game;
    private Handler handler;
    public static int computerScore = 0;

    public ComputerPlayer(int width, int height, int speedX, int speedY, int posx, int posy, Color c, ID id, Handler handler, Game game){
        super(width, height, speedX, speedY, posx, posy, c, id, handler, game);
        this.game = game;
        this.handler = handler;
    }

    public void tick(){
       for(int i = 0; i < handler.objects.size(); i++){
           GameObject obj = handler.objects.get(i);
            //height, spike1, spike2
            algorithm(obj);
       }
    }

    public void render(Graphics g){
        g.setColor(c);
        g.fillRect(posx, posy, width, height);
        g.setColor(Color.BLUE);
        g.drawRect(posx, posy, 64, 64);
    }

    public Rectangle getBounds() {
        return new Rectangle(posx-20, posy-20, 84, 84);
    }

    private void algorithm(GameObject obj) {
        if (Options.computersturn){
            if (obj.getID() == ID.Spike){
                //System.out.println(handler.objects.size());
                System.out.println(getBounds().intersects(obj.getBounds()));
                if (getBounds().intersects(obj.getBounds())){
                    System.out.println("creepers");
                    if (Options.algorithmMode) {
                        computerScore = HUD.score;
                        game.gameState = Game.State.ComputerEnd;
                    }
                    else game.gameState = Game.State.End;
                }
                else {
                    int closest = posy;
                    for(GameObject s : handler.objects){
                        if (s.getID() == ID.Spike){
                            if (closest > s.getY()){
                                closest = s.getY();
                            }
                        }
                    }
                    if (closest == posy){
                        closest = posy/2;
                    }
                    if (height < Math.abs(closest - obj.getY())) {
                        int dist = Math.abs(closest - obj.getY());
                        //System.out.println("Pathway: " + dist);
                        if ( closest - obj.getY() < 0){
                            posy = closest + dist/2;
                        } else if (closest - obj.getY() > 0){
                            posy = obj.getY() + dist/2;
                        }
                        posy = Game.clamp(posy, 0, Game.HEIGHT-100);
                    }
                }
            }
        }
    }
}
