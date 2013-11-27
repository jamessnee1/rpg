package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Intro extends BasicGameState {
	
	private Image intro1;
	private Image intro2;
	boolean fade;
	boolean fade2 = false;
	private int timer;
	private int waitTime = 2000;
	
	
	public Intro(int State){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		intro1 = new Image("res/sprites/intro1.png");
		intro1.setAlpha(0);
		intro2 = new Image("res/sprites/intro2.png");
		intro2.setAlpha(0);
		System.out.println("In init menu state");
		

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.setColor(Color.black);
		g.drawRect(0, 0, 640, 480);
		g.drawImage(intro1, 0, 0);
		
		

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		
	}

	@Override
	public int getID() {
		return 2;
	}

}
