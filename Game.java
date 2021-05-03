import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = -2472033984939159889L;
    private Thread thread;
    private Handler handler;
    private boolean running = false;
    public static final int WIDTH = 1920, HEIGHT = WIDTH/16 * 9;
    private HUD hud;
    private Startscreen s;
	private Endscreen e;
	private Random r;
	public static int count;
	public static int currentScore = 0;
	public static int spikes, speed = -20, level = 0, highscore = 0;
	public static int frames;
	private int framees;
	private Options o;
	private ComputerTurn c;
	private ComputerEnd ce;
    public enum State {
        Start,
		Options,
        Game,
		ComputerTurn,
		End,
		ComputerEnd
    };

    public State gameState = State.Start;


    public Game(){
		r = new Random();
        handler = new Handler();
        hud = new HUD();
		
        this.addKeyListener(new KeyInput(handler));
        s = new Startscreen(this, handler);
		e = new Endscreen(this, handler);
		o = new Options();
		c = new ComputerTurn(this, handler);
		ce = new ComputerEnd(this, handler);
		this.addMouseListener(s);
        new Window(WIDTH, HEIGHT, "Dodge the Spikes!", this); 
            
    }
    public static void main(String[] args){
        new Game();        
    }

    public synchronized void start(){
        System.out.println("Starting");
        thread = new Thread(this);
		thread.start();
		running = true;
    }

    public synchronized void stop(){
        try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
    }

    public static int clamp(int var, int min, int max){
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

    public void run() {
        this.requestFocus();
		spikes = 5;
		count = 0;
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		frames = 0;
		while(running){
			if (gameState == State.Game){
				HUD.score++;
			} else {
				count = 0;
				spikes = 5;
				speed = -20;
				level = 0;
			}
			count++;
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				framees = frames;
				frames = 0;
			}
		}
		stop();
        
    }

    

    private void tick(){
        handler.tick();
        if (gameState == State.Game){
			hud.tick();
			int spikerate = 100;
			if (Options.algorithmMode) spikerate = 200;
			if (count >= spikerate){
				count = 0;
				level++;
				speed -= 1;
				Spike s = new Spike(64, 8, speed, 0, Game.WIDTH-76, r.nextInt(Game.HEIGHT-16), Color.black, ID.Spike, handler, this);
				handler.addObject(s);
			}
		} else if (gameState == State.Start){
			s.tick();
		} else if (gameState == State.End){
			e.tick();
		} else if (gameState == State.ComputerTurn){
			c.tick();
		} else if (gameState == State.ComputerEnd){
			ce.tick();
		}


    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3/*how many buffers it creates*/);
			return;
		}
        
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.orange);
        g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.GREEN);
		Font ft = new Font("Sans_Serif", Font.BOLD, 25);
		g.setFont(ft);
		if (framees < 30) g.setColor(Color.RED);
		g.drawString("FPS: " + framees, 1750, 50);
        handler.render(g);
        if (gameState == State.Game){
			hud.render(g);
		} else if (gameState == State.Start){
			s.render(g);
		} else if (gameState == State.Options){
			o.render(g);
		}else if (gameState == State.End){
			e.render(g);
		} else if (gameState == State.ComputerTurn){
			c.render(g);
		} else if (gameState == State.ComputerEnd){
			ce.render(g);
		}

        g.dispose();
        bs.show();
    }
    
}
