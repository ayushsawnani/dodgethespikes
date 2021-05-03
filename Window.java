import java.awt.*;

import javax.swing.JFrame;

public class Window{

    public static int WIDTH = 1920, HEIGHT = 1920/16 * 9;
	public Window(int width, int height, String title, Game s){
        JFrame frame = new JFrame(title);
        
        frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		//so that the exit button works
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//so u can't resize the screen      
		frame.setResizable(false);
		//so that the game starts in the middle rather than the top left
		frame.setLocationRelativeTo(null);
		//adding the actual game to the JFrame "frame"
		frame.add(s);
		//so that we can see the game
		frame.setVisible(true);
		//so that the game starts
		s.start();

    }




}