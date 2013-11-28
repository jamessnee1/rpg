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

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Play extends BasicGameState {
	
	//create a new player
	private static Player player1;
	
	//create background
	private Image background;
	
	//health bar
	private HealthBar health;

	//mouse position
	int mousePosX, mousePosY;
	
	//true map corner
	double trueMapPosX = 12, trueMapPosY = 10;
	
	//keeps track of where the map is in the top left corner (for scrolling)
	private static int mapX;
	private static int mapY;
	
	private PauseMenu pause;

	String mousepos = "no input detected!";

	//world and menu stuff
	private TiledMap world1;
	FadeOutTransition fo;
	Music overworld;
	

	public Play(int state){
		
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		System.out.println("In init play state");
		
		background = new Image("res/sprites/background.png");
		
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
		
		//set sky to blue
		Color bluesky = new Color(1,95,165);
		g.setColor(bluesky);
		g.fillRect(0, 0, 640, 480);
		
		//draw background
		background.draw(0,0);
		
		//draw world, will only render whats on screen
		//x location to render, y location to render, x tile location, y tile location, width to render, height to render
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
		health.update(gc, sbg, delta);

		
		//map updating
		if (player1.getPlayerPosX() < 0){
			//go right
			//collision detection
			
			getRightBounds();
			mapX++;
			player1.setPlayerPosX(32);
		}
		if (player1.getPlayerPosX() > 32){
			//go left
			getLeftBounds();
			mapX--;
			player1.setPlayerPosX(0);
		}
		if (player1.getPlayerPosY() < 0){
			//go up
			getUpBounds();
			mapY++;
			player1.setPlayerPosY(32);
		}
		if (player1.getPlayerPosY() > 32){
			//go down
			getDownBounds();
			mapY--;
			player1.setPlayerPosY(0);
		}
		
		//display menu
		if (input.isKeyDown(input.KEY_ESCAPE)){
			PauseMenu.setQuitGame(true);
					
		}

	}
	
	//returns true or false if tile is blocked
	public boolean getLeftBounds(){
		System.out.println("Rectangle to the left of player is " + (mapX-1) + " , " + mapY);
		//get collision layer of tilemap
		int collisionLayer = world1.getLayerIndex("Collision");
		return (isBlocked(world1, collisionLayer, mapX-1, mapY));
		
	}
	public boolean getRightBounds(){
		System.out.println("Rectangle to the right of player is " + (mapX+1) + " , " + mapY);
		//get collision layer of tilemap
		int collisionLayer = world1.getLayerIndex("Collision");
		return (isBlocked(world1, collisionLayer, mapX+1, mapY));
	}
	public boolean getUpBounds(){
		System.out.println("Rectangle north of player is " + mapX + " , " + (mapY-1));
		//get collision layer of tilemap
		int collisionLayer = world1.getLayerIndex("Collision");
		return (isBlocked(world1, collisionLayer, mapX, mapY-1));
		
	}
	public boolean getDownBounds(){
		System.out.println("Rectangle south of player is " + mapX + " , " + (mapY+1));
		//get collision layer of tilemap
		int collisionLayer = world1.getLayerIndex("Collision");
		return (isBlocked(world1, collisionLayer, mapX, mapY+1));
	}
	
	
	//this method checks to see which if a given tile has the blocked property
	public boolean isBlocked(TiledMap world, int tileMapLayer, int x, int y){
		
		if (x < 0){
			
			System.out.println("X check failed! out of bounds");
			return false;
		}
		
		if (y < 0){
			
			System.out.println("Y check failed! out of bounds");
			return false;
		}
		
		int checkTileID = world.getTileId(x, y, tileMapLayer);
		System.out.println("global ID of tile is: " + checkTileID);
		String result = world.getTileProperty(checkTileID, "blocked", "not blocked");
		if (result == "blocked"){
			System.out.println("Tile is blocked");
			return true;
		}
		else{
			System.out.println("Tile not blocked");
		return false;
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

	public static void setMapX(int mapX) {
		Play.mapX = mapX;
	}

	public static void setMapY(int mapY) {
		Play.mapY = mapY;
	}

	public static Player getPlayer1() {
		return player1;
	}

	

}
