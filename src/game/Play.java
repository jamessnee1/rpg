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
	
	//sprite sheets
	private SpriteSheet playerUp, playerDown, playerLeft, playerRight;
	
	//player position
	private double playerPosX, playerPosY;
	
	//mouse position
	int mousePosX, mousePosY;
	
	//keeps track of where we are on the map (for scrolling)
	private int mapX, mapY;
	
	String mousepos = "no input detected!";
	
	//anims
	Animation player, movingUp, movingDown, movingLeft, movingRight;
	
	//world and menu stuff
	private TiledMap world1;
	boolean quitGame = false;
	private Image resume;
	private Image menu;
	private Image quit;
	FadeOutTransition fo;
	Music overworld;
	
	//anim duration
	int[] duration = {200, 200};

	public Play(int state){
		
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		System.out.println("In init play state");
		
		//set up player position
		playerPosX = 0;
		playerPosY = 0;
		
		//load tmx for first level
		world1 = new TiledMap("res/overworld.tmx");
		fo = new FadeOutTransition(Color.black, 2000);
		
		//initialise anims
		playerUp = new SpriteSheet("res/sprites/playerup.png", 32, 32);
		playerDown = new SpriteSheet("res/sprites/playerdown.png", 32, 32);
		playerLeft = new SpriteSheet("res/sprites/playerleft.png", 32, 32);
		playerRight = new SpriteSheet("res/sprites/playerright.png", 32, 32);
		
		
		//assigning the animations (spritesheet, duration)
		movingUp = new Animation(playerUp, 200);
		movingDown = new Animation(playerDown, 200);
		movingLeft = new Animation(playerLeft, 200);
		movingRight = new Animation(playerRight, 200);
		
		//idle/default anim
		player = movingDown;
		player.stop();
		
		//menu stuff
		resume = new Image("res/sprites/resume.png");
		menu = new Image("res/sprites/menu.png");
		quit = new Image("res/sprites/exitGame.png");
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

		
		//draw world, will only render whats on screen
		//player position, map position, map position + tile
		world1.render((int)playerPosX-32, (int)playerPosY-32, mapX, mapY, mapX+26, mapY+26);
		
		//draw player
		player.draw(320,240);
		
		
		g.setColor(Color.white);
		g.drawString("Player position x: "+ playerPosX+ "\nPlayer position y: " + playerPosY, 400,20);
		g.drawString(mousepos, 200, 400);		
		//menu code
		if (quitGame == true){
			

			Color c = new Color(0, 0, 0, 85);
			g.setColor(c);
			g.fillRect(0,0,640,480);
			resume.draw(200, 125);
			menu.draw(200, 200);
			quit.draw(200 , 275);
			
			
			if (quitGame == false){
				g.clear();
			} 
			
		}
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		//every input is stored here
		Input input = gc.getInput();
		
		mousePosX = Mouse.getX();
		mousePosY = Mouse.getY();
		
		mousepos = "Position x: " + mousePosX + " y: " + mousePosY;
		
		//update world
		int objectlayer = world1.getLayerIndex("Objects");
		//int tileID = world1.getTileId(0, 0, objectlayer);
		
		//player movement
		if (input.isKeyDown(input.KEY_UP) && quitGame != true){
			
			player = movingUp;
			player.start();
			playerPosY += delta * .1f;
			
			
		}

		
		if (input.isKeyDown(input.KEY_DOWN) && quitGame != true){

			player = movingDown;
			player.start();
			playerPosY -= delta * .1f;

		}
		
		if (input.isKeyDown(input.KEY_LEFT) && quitGame != true){
			
			player = movingLeft;
			player.start();
			playerPosX += delta * .1f;

		}

		
		if (input.isKeyDown(input.KEY_RIGHT) && quitGame != true){
			
			player = movingRight;
			player.start();
			playerPosX -= delta * .1f;
		

		}
		
		//map updating
		if (playerPosX < 0){
			mapX++;
			playerPosX = 32;
		}
		if (playerPosX > 32){
			mapX--;
			playerPosX = 0;
		}
		if (playerPosY < 0){
			mapY++;
			playerPosY = 32;
		}
		if (playerPosY > 32){
			mapY--;
			playerPosY = 0;
		}
		
		//detects if player is not moving, stop the anim and display frame 0
		if (!input.isKeyDown(input.KEY_UP) && !input.isKeyDown(input.KEY_DOWN) && !input.isKeyDown(input.KEY_LEFT) &&
				!input.isKeyDown(input.KEY_RIGHT)){
			player.stop();
			player.setCurrentFrame(0);
			
		}
		
		
		
		//display menu
		if (input.isKeyDown(input.KEY_ESCAPE)){
			quitGame = true;
		}
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
	
	public int getID(){
		
		return 1;
	}

}
