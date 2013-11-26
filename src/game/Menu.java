package game;

import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;
import org.newdawn.slick.state.transition.SelectTransition;
import org.lwjgl.input.*;

public class Menu extends BasicGameState {
	
	Image logo;
	Image background;
	float logopos = 600;
	Image playnow;
	Image exitgame;
	String mousepos = "No input detected!";
	String copyright = "Copyright \u00a9 2013 JSS Studios. Uses the Slick2D and LWJGL libraries.";
	FadeOutTransition fo;
	FadeInTransition fi, white;
	Music intro;
	Music overworld;
	
	public Menu(int state){
		
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		
		//initialise graphics
		logo = new Image("res/sprites/8bit.png");
		background = new Image("res/sprites/background.png");
		//copyright = new Image("res/sprites/copyright.png");
		fo = new FadeOutTransition(Color.black, 2000);
		fi = new FadeInTransition(Color.black, 2000);
		white = new FadeInTransition(Color.white, 1000);
		//initialise buttons
		playnow = new Image("res/sprites/playNow.png");
		exitgame = new Image("res/sprites/exitGame.png");
		//initialise music
		intro = new Music("res/sound/music/intro.wav");
		overworld = new Music("res/sound/music/overworld.ogg");
		intro.play();
		System.out.println("In init menu state");
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

		//render background
		Color bluesky = new Color(1,95,165);
		g.setColor(bluesky);
		g.fillRect(0, 0, 640, 480);
		
		//draw clouds
		background.draw(0,0);
		
		//draw logo and buttons
		g.setColor(Color.white);
		logo.draw(logopos, 20);
		g.drawString(mousepos, 200, 300);
		//draw copyright text
		g.setColor(Color.black);
		g.drawString(copyright, 10, 450);
		playnow.draw(200, 125);
		exitgame.draw(200, 200);
		
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		
		 
		
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		
		if (logopos > 150){
			logopos -= delta * .2f;
		}
		
		mousepos = "Position x: " + xpos + " y: " + ypos;
		
		//new game button
		if ((xpos > 204 && xpos < 404) && (ypos > 308 && ypos < 350)){
			
			if (Mouse.isButtonDown(0)){
				
				if (intro.playing()){
					intro.stop();
				}
				sbg.enterState(1, fo, fi);
				overworld.loop();
				overworld.setVolume(0.2f);
				
			}
			
		}
		
		if ((xpos > 204 && xpos < 404) && (ypos > 237 && ypos < 275)){
			
			if (Mouse.isButtonDown(0)){
				System.exit(0);
			}
			
		}
		
		
	}
	
	public int getID(){
		
		return 0;
	}


}
