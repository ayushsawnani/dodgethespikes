import java.awt.*;
import java.awt.event.*;
import java.util.Random;



public class Startscreen extends MouseAdapter{
	private Game game;
	private Handler handler;
	private Random r;

	public Startscreen(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
		r = new Random();
	}


	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		boolean isOverPlay = mouseIsOver(mx, my, Game.WIDTH/2-375, Game.HEIGHT/2-200, 700, 128);
		boolean isOverOptions = mouseIsOver(mx, my, Game.WIDTH/2-375, Game.HEIGHT/2, 700, 128);


		boolean isOverBack = mouseIsOver(mx, my, Game.WIDTH/2 - 100, Game.HEIGHT/2 + 200, 200, 100);
		boolean isOverAlgo = mouseIsOver(mx, my, Game.WIDTH/2 - 200, Game.HEIGHT/2 - 100, 50, 50);


		boolean isOverPlayAgain = mouseIsOver(mx, my, Game.WIDTH/2-375, Game.HEIGHT/2-200, 700, 128);
		boolean isOverOptionsEnd = mouseIsOver(mx, my, Game.WIDTH/2-375, Game.HEIGHT/2, 700, 128);
		boolean isOverQuit = mouseIsOver(mx, my, Game.WIDTH/2-375, Game.HEIGHT/2 + 200, 700, 128);

		boolean isOverComputersTurn = mouseIsOver(mx, my, Game.WIDTH/2-375, Game.HEIGHT/2-200, 700, 128);
		boolean isOverComputerQuit = mouseIsOver(mx, my, Game.WIDTH/2-375, Game.HEIGHT/2 + 200, 700, 128);

		boolean isOverComputerPlayAgain = mouseIsOver(mx, my, Game.WIDTH/2-375, Game.HEIGHT/2-200, 700, 128);
		boolean isOverComputerOptionsEnd = mouseIsOver(mx, my, Game.WIDTH/2-375, Game.HEIGHT/2, 700, 128);
		boolean isOverComputerEndQuit = mouseIsOver(mx, my, Game.WIDTH/2-375, Game.HEIGHT/2 + 200, 700, 128);
		if (game.gameState == Game.State.Start){
			if(isOverPlay){
				game.gameState = Game.State.Game;
				HUD.score = 0;
				Player p = new Player(64, 64, 0, 0, Game.WIDTH/2-64, Game.HEIGHT/2-64, Color.black, ID.Player, handler, game);
				handler.addObject(p);
				Spike s = new Spike(64, 8, -20, 0, Game.WIDTH-76, r.nextInt(Game.HEIGHT), Color.black, ID.Spike, handler, game);
				handler.addObject(s);
				
			} else if (isOverOptions){
				game.gameState = Game.State.Options;
			}
		}

		if (game.gameState == Game.State.End){
			

			if(isOverPlayAgain){
				HUD.score = 0;
				System.out.println(HUD.score);
				Game.spikes = 5;
				Game.level = 0;

				game.gameState = Game.State.Game;

				Player p = new Player(64, 64, 0, 0, Game.WIDTH/2-64, Game.HEIGHT/2-64, Color.black, ID.Player, handler, game);
				handler.addObject(p);
				Spike s = new Spike(64, 8, -20, 0, Game.WIDTH-76, r.nextInt(Game.HEIGHT), Color.black, ID.Spike, handler, game);
				handler.addObject(s);

				
			}

			if (isOverOptionsEnd){
				game.gameState = Game.State.Options;
			}

			if(isOverQuit){
				System.exit(0);
				
			}

		}

		if (game.gameState == Game.State.Options){

			if(isOverBack){
				game.gameState = Game.State.Start;
				
				
			} 
			
			else if (isOverAlgo){
				Options.algorithmMode = !(Options.algorithmMode);
			}
		}

		if (game.gameState == Game.State.ComputerTurn){
			if (isOverComputersTurn){
				HUD.score = 0;
				Options.computersturn = true;
				game.gameState = Game.State.Game;
				ComputerPlayer p = new ComputerPlayer(64, 64, 0, 0, Game.WIDTH/2-64, Game.HEIGHT/2-64, Color.black, ID.Player, handler, game);
				handler.addObject(p);
			}
			else if (isOverComputerQuit){
				System.exit(0);
			}
		}

		if (game.gameState == Game.State.ComputerEnd){
			if (isOverComputerPlayAgain){
				game.gameState = Game.State.Start;
				Options.computersturn = false;
			}
			else if (isOverComputerOptionsEnd){
				Options.computersturn = false;
				game.gameState = Game.State.Options;
			} else if (isOverComputerEndQuit){
				System.exit(0);
			}
		}

	}

	public void mouseReleased(MouseEvent e){

	}

	private boolean mouseIsOver(int mx, int my, int x, int y, int width, int height){
		if (mx > x && mx <x + width){
			if (my > y && my < y + height){
				return true;
			} else return false;

		}else return false;
	}

	public void tick(){

	}

	
	public void render(Graphics g){
		g.setColor(Color.black);

		Font f = new Font("Arial", Font.BOLD, 100);
		g.setFont(f);
		
		g.drawString("Dodge the Spikes!", 515, 200);
		g.drawString("Play", Game.WIDTH/2-125, Game.HEIGHT/2-100);
		g.drawRect(Game.WIDTH/2-375, Game.HEIGHT/2-200, 700, 128);

		g.drawString("Options", Game.WIDTH/2-200, Game.HEIGHT/2+100);
		g.drawRect(Game.WIDTH/2-375, Game.HEIGHT/2, 700, 128);
	}
}