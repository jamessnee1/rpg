package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

public class Play extends BasicGameState {
	
	//create a new player
	private Player player1;
	
	//health bar
	private HealthBar health;

	//mouse position
	int mousePosX, mousePosY;
	
	//keeps track of where we are on the map (for scrolling)
	private static int mapX;
	private static int mapY;
	
	private PauseMenu pause;
	
	
	//collision layer

	String mousepos = "no input detected!";

	//world and menu stuff
	private TiledMap world1;
	FadeOutTransition fo;
	Music overworld;

	public Play(int state){
		
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		System.out.println("In init play state");
		
		player1 = new Player(gc, sbg);
		
		health = new HealthBar(gc, sbg);
		
		pause = new PauseMenu();
		
		
		
		
		//load tmx for first level
		world1 = new TiledMap("res/overworld.tmx");
		fo = new FadeOutTransition(Color.black, 2000);

		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		//every input is stored here
		Input input = gc.getInput();
		
		//draw world, will only render whats on screen
		//player position, map position, map position + tile
		world1.render((int)player1.getPlayerPosX()-32, (int)player1.getPlayerPosY()-32, mapX, mapY, mapX+40, mapY+40);
		
		//draw player
		player1.render(gc, sbg, g);
		
		//draw healthbar
		health.render(gc, sbg, g);
		
		//sets colour back to white for text
		g.setColor(Color.white);


		if (PauseMenu.isQuitGame()){
			pause.render(gc, sbg, g);
		}
		
		g.drawString(mousepos, 200, 400);	
		g.drawString("Map position x:" + mapX + "\nMap position y: "+ mapY, 400, 60);
		

	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		//every input is stored here
		Input input = gc.getInput();

		mousePosX = Mouse.getX();
		mousePosY = Mouse.getY();
		
		mousepos = "Position x: " + mousePosX + " y: " + mousePosY;
		
		player1.update(gc, sbg, delta);
		pause.update(gc, sbg, delta);

		
		//map updating
		if (player1.getPlayerPosX() < 0){
			//go left
			mapX++;
			player1.setPlayerPosX(32);
		}
		if (player1.getPlayerPosX() > 32){
			//go right
			mapX--;
			player1.setPlayerPosX(0);
		}
		if (player1.getPlayerPosY() < 0){
			//go up
			mapY++;
			player1.setPlayerPosY(32);
		}
		if (player1.getPlayerPosY() > 32){
			//go down
			mapY--;
			player1.setPlayerPosY(0);
		}
		
		//display menu
		if (input.isKeyDown(input.KEY_ESCAPE)){
			PauseMenu.setQuitGame(true);
					
		}

	}
	
	public int getID(){
		
		return 1;
	}

	public static int getMapX() {
		return mapX;
	}

	public static int getMapY() {
		return mapY;
	}

	

}
