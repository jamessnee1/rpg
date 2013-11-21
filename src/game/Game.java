package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {
	
	public static final String gamename = "8-Bit Quest v1.0";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int quit = 2;
	
	public Game(String gamename) {
		
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		
	}
	
	// Inherited method
	//GameContainer handles main game loop etc
	public void initStatesList(GameContainer gc) throws SlickException {
		
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		//first game screen
		this.enterState(menu);
		
		
	}
	

	public static void main(String[] args) {
		
		//create game window
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gamename));
			//width, height, fullscreen
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
			
			
		}catch(SlickException e){
			
			e.printStackTrace();
			
		}

		
	}

}
