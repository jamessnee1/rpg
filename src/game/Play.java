package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;
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
	
	//true map player position
	int trueMapPosX = 10, trueMapPosY = 7;
	
	//tilemap bound
	int tileMapEdge = 49;
	
	//rectangle to create around world collision objects
	static Shape worldCollisionBox;
	
	//global delta accessable to all methods
	int globaldelta;
	
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
		g.drawString("Player Map Position X: " + trueMapPosX +"\nPlayer Map Position Y: "+ trueMapPosY, 400, 100);
		

	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		//every input is stored here
		Input input = gc.getInput();
		
		globaldelta = delta;
				
		
		mousePosX = Mouse.getX();
		mousePosY = Mouse.getY();
		
		mousepos = "Position x: " + mousePosX + " y: " + mousePosY;
		
		player1.update(gc, sbg, delta);
		pause.update(gc, sbg, delta);
		health.update(gc, sbg, delta);

		
		//map updating
		if (player1.getPlayerPosX() < 0){
			
			//collision detection - If the tile above, to the left, to the right
			//or to the bottom of the player is a colliding tile, we must create
			//a collisionbox around the tile and then see if it intersects with the 
			//player collision box
			
			//go right
			mapX++;
			trueMapPosX++;
			player1.setPlayerPosX(32);
			getRightBounds();
		}
		if (player1.getPlayerPosX() > 32){
			//go left
			mapX--;
			trueMapPosX--;
			player1.setPlayerPosX(0);
			getLeftBounds();
		}
		if (player1.getPlayerPosY() < 0){
			//go down
			mapY++;
			trueMapPosY++;
			player1.setPlayerPosY(32);
			getDownBounds();
		}
		if (player1.getPlayerPosY() > 32){
			//go up
			mapY--;
			trueMapPosY--;
			player1.setPlayerPosY(0);
			getUpBounds();
		}
		
		//display menu
		if (input.isKeyDown(input.KEY_ESCAPE)){
			PauseMenu.setQuitGame(true);
					
		}

	}
	
	//checks the tiles above, below, to the left and to the right of the player
	public void getLeftBounds(){
		
		if (isBlocked(trueMapPosX-1, trueMapPosY, 3)){
			//tile is blocked, so set collided to true
			player1.setCollided(true);
			System.out.println("collision detected!");
		}
		
		System.out.println("Rectangle to the left of player is " + (trueMapPosX-1) + " , " + trueMapPosY);
		
	}
	
	public void getRightBounds(){
		
		if (isBlocked(trueMapPosX+1, trueMapPosY, 3)){
			player1.setCollided(true);
			System.out.println("collision detected!");
		}
		
		System.out.println("Rectangle to the right of player is " + (trueMapPosX+1) + " , " + trueMapPosY);
	}
	
	public void getUpBounds(){
		
		if (isBlocked(trueMapPosX, trueMapPosY-1, 3)){
			player1.setCollided(true);
			System.out.println("collision detected!");
		}
		
		System.out.println("Rectangle north of player is " + trueMapPosX + " , " + (trueMapPosY-1));
	}
	
	public void getDownBounds(){
		
		if (isBlocked(trueMapPosX, trueMapPosY+1, 3)){
			player1.setCollided(true);
			System.out.println("collision detected!");
			System.out.println("collision rectangle created!");
		}
		
		System.out.println("Rectangle south of player is " + trueMapPosX + " , " + (trueMapPosY+1));		
	}
	
	public boolean isBlocked(int x, int y, int tileMapLayer){
		
		//check to see if we have gone over the edge of the map
		//to prevent game crash
		if (x < 0 || x > tileMapEdge){
			System.out.println("X is out of bounds! Failed check");
			return false;
		}
		if (y < 0 || y > tileMapEdge){
			System.out.println("Y is out of bounds! Failed check");
			return false;
		}
		
		int checkTileID = world1.getTileId(trueMapPosX, trueMapPosY, 3);
		String blocked = world1.getTileProperty(checkTileID, "blocked", "no value");
		System.out.println("The tile at this position is: " + blocked);
		
		if (blocked == ""){
			//we have detected a collision. return true
			return true;
		}else{
			//no collision, return false
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

	public static Shape getWorldCollisionBox() {
		return worldCollisionBox;
	}

	

}
