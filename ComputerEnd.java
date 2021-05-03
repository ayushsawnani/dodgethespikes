import java.awt.*;



public class ComputerEnd{
	private Game game;
	private Handler handler;

	public ComputerEnd(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}

	public void tick(){
		if(game.gameState == Game.State.End){
			if (Game.highscore <= HUD.score){
				Game.highscore = HUD.score;
			}
		}
	}

	
	public void render(Graphics g){
		handler.removeAllObjects();
        g.setColor(Color.orange);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.black);
		Font f = new Font("Arial", Font.BOLD,50);
		g.setFont(f);
		
        if (Player.playerScore < ComputerPlayer.computerScore) g.drawString("You Lost...", 615, 200);
		else if (Player.playerScore > ComputerPlayer.computerScore) g.drawString("You Win!", 615, 200);
        else g.drawString("Tie!", 615, 200);
		g.setColor(Color.blue);
        g.drawString("Your score: " + Player.playerScore, 100, 400);
        g.drawString("Computer Score: " + ComputerPlayer.computerScore, 1315, 400);
		g.setColor(Color.black);
		g.drawString("Play Again", Game.WIDTH/2-300, Game.HEIGHT/2-125);
		g.drawRect(Game.WIDTH/2-375, Game.HEIGHT/2-200, 700, 128);

        g.drawString("Options", Game.WIDTH/2-100, Game.HEIGHT/2 + 75);
		g.drawRect(Game.WIDTH/2-375, Game.HEIGHT/2, 700, 128);

		g.drawString("Quit", Game.WIDTH/2-100, Game.HEIGHT/2 + 275);
		g.drawRect(Game.WIDTH/2-375, Game.HEIGHT/2 + 200, 700, 128);
	}
}
