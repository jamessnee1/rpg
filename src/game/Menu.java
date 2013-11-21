package game;

import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;
import org.newdawn.slick.state.transition.SelectTransition;
import org.lwjgl.input.*;

public class Menu extends BasicGameState implements MusicListener {
	
	Image logo;
	Image background;
	float logopos = 600;
	Image playnow;
	Image exitgame;
	String mousepos = "No input detected!";
	FadeOutTransition fo;
	FadeInTransition fi, white;
	Music intro;
	Music overworld;
	
	public Menu(int state){
		
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		logo = new Image("res/sprites//8bit.png");
		background = new Image("res/sprites/background.png");

		
		
		fo = new FadeOutTransition(Color.black, 2000);
		fi = new FadeInTransition(Color.black, 2000);
		white = new FadeInTransition(Color.white, 1000);
		playnow = new Image("res/sprites/playNow.png");
		exitgame = new Image("res/sprites/exitGame.png");
		intro = new Music("res/sound/music/intro.wav");
		overworld = new Music("res/sound/music/overworld.ogg");
		intro.play();
		System.out.println("In init menu state");
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

		Color bluesky = new Color(1,95,165);
		g.setColor(bluesky);
		g.fillRect(0, 0, 640, 480);
		
		background.draw(0,0);
		
		g.setColor(Color.white);
		logo.draw(logopos, 20);
		g.drawString(mousepos, 200, 400);
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
	
	

	@Override
	public void musicEnded(Music m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void musicSwapped(Music m, Music m2) {
		
		
	}

}
