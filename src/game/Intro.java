package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Intro extends BasicGameState {
	
	private Image intro1;
	private Image intro2;
	boolean fade;
	boolean fade2 = false;
	int time;
	
	
	
	public Intro(int State){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		intro1 = new Image("res/sprites/intro1.png");
		intro2 = new Image("res/sprites/intro2.png");
		System.out.println("In init menu state");
		

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.setColor(Color.black);
		g.drawRect(0, 0, 640, 480);
		g.drawImage(intro1, 0, 0);
		intro1.setAlpha(0);
		g.drawImage(intro2, 0, 0);
		intro2.setAlpha(0);
		
		

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		fadeIn(intro1, delta);
		fadeOut(intro1, delta);
		fadeIn(intro2, delta);
		fadeOut(intro2, delta);
		
	}

	@Override
	public int getID() {
		return 2;
	}
	
	public void fadeIn(Image image, int delta){
		
		
	}
	public void fadeOut(Image image, int delta){
		
		
	}

}
