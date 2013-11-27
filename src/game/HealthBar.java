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
	private float currentHealth = 100;
	private float healthAnim = 0;
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
		g.fillRect(50, 30, healthAnim, 10);
		
		
		
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		//initial health startup animation
		if (initHealth == false){
			while (healthAnim < 100){
				//100 pixels over 1 second
				healthAnim += 100 * delta / 1000f;
				
			}
				
		initHealth = true;

		}
		
		
	}

	public float getCurrentHealth() {
		return currentHealth;
	}	

	public void setCurrentHealth(float currentHealth) {
		this.currentHealth = currentHealth;
	}

}
