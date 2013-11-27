package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class PauseMenu {
	
	private static boolean quitGame = false;

	
	
	//mouse position
	private int mousePosX, mousePosY;
	
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

		Color c = new Color(0, 0, 0, 85);
		g.setColor(c);
		g.fillRect(0,0,640,480);
		Image logo = new Image("res/sprites/8bit.png");
		Image resume = new Image("res/sprites/resume.png");
		Image menu = new Image("res/sprites/menu.png");
		Image quit = new Image("res/sprites/exitGame.png");
		logo.draw(150,20);
		resume.draw(200, 125);
		menu.draw(200, 200);
		quit.draw(200 , 275);
			
			
			if (quitGame == false){
				g.clear();
			} 
			
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

		Input input = gc.getInput();
		mousePosX = Mouse.getX();
		mousePosY = Mouse.getY();
		
		
		//when menu is up
		if (quitGame == true){
			
			//resume button
			if ((mousePosX > 204 && mousePosX < 404) && (mousePosY > 308 && mousePosY < 350)){
				
				if (Mouse.isButtonDown(0)){
					quitGame = false;
					
				}
				
			}
			//menu button
			if ((mousePosX > 204 && mousePosX < 404) && (mousePosY > 237 && mousePosY < 275)){
				
				if (Mouse.isButtonDown(0)){
					quitGame = false;
					sbg.enterState(0, new FadeOutTransition(Color.black, 2000), new FadeInTransition(Color.black, 2000));
				}
				
			}
			
			//quit button
			if ((mousePosX > 204 && mousePosX < 404) && (mousePosY > 160 && mousePosY < 201)){
				
				if (Mouse.isButtonDown(0)){
					System.exit(0);
				}
				
			}
			
			

			if (input.isKeyDown(Input.KEY_R)){
				quitGame = false;
			}
			
			
			if (input.isKeyDown(Input.KEY_M)){
				quitGame = false;
				sbg.enterState(0, new FadeOutTransition(Color.black, 2000), new FadeInTransition(Color.black, 2000));

				
			}
			
			if (input.isKeyDown(Input.KEY_Q)){
				System.exit(0);
			}
			
		}
	}
	
	public static boolean isQuitGame() {
		return quitGame;
	}

	public static void setQuitGame(boolean quitGame) {
		PauseMenu.quitGame = quitGame;
	}



}
