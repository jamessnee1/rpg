package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class HealthBar extends Image {
	
	private int minHealth = 0;
	private int maxHealth = 100;
	private int currentHealth = 100;
	
	
	
	public HealthBar(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.setColor(Color.white);
		g.drawString("Health", 50, 10);
		g.setColor(Color.black);
		//x , y , variable, height
		g.drawRect(50, 30, maxHealth, 10);
		g.fillRect(50, 30, maxHealth, 10);
		g.setColor(Color.green);
		g.fillRect(50, 30, currentHealth, 10);
		
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	public int getCurrentHealth() {
		return currentHealth;
	}	

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

}
