package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Intro extends BasicGameState {
	
	private String title;
	private String title2;
	private int fader;
	private int waitTime;
	
	public Intro(int State){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		title = "JSS Studios";
		title2 = "Presents...";
		fader = 0;
		waitTime = 1000;
		System.out.println("In init menu state");
		

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		Color c = new Color(255,255,255,fader);
		g.setColor(c);
		g.drawString(title, 270, 220);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		if (fader < 255){
			fader++;
		}
		
	}

	@Override
	public int getID() {
		return 2;
	}

}
