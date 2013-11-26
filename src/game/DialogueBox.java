package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class DialogueBox {
	
	private Image box;
	private boolean boxUp;
	float boxPos = 600;
	
	public DialogueBox() throws SlickException{
		
		box = new Image("res/sprites/dialoguebox.png");
		boxUp = true;
		
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		
		box.draw(10,300);
		
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		if (boxPos > 150){
			boxPos -= delta * .4f;
		}
		
		
	}
	
	

}
