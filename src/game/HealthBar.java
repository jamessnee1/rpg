package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class HealthBar extends Image {
	
	private float minHealth = 0;
	private float maxHealth = 100;
	private float currentHealth = Play.getPlayer1().getHealth();
	private boolean initHealth = false;
	private Rectangle bar;
	private long time = 0;
	
		
	public HealthBar(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.setColor(Color.white);
		g.drawString("Health", 50, 10);
		g.setColor(Color.black);
		//x , y , variable, height
		bar = new Rectangle(50, 30, maxHealth, 10);
		g.fill(bar);
		g.draw(bar);
		g.setColor(Color.green);
		g.fillRect(50, 30, currentHealth, 10);
		
		
		
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		//renders health bar initial animation 
		for (float i = 0; i < 1; i++){
			if (currentHealth < 100){
			currentHealth++;
			}
		}
		
		
	}

	public float getCurrentHealth() {
		return currentHealth;
	}	

	public void setCurrentHealth(float currentHealth) {
		this.currentHealth = currentHealth;
	}

}
