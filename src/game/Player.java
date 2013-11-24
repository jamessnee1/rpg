package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	
	//player health
	private int health;
	//player graphics
	private SpriteSheet playerUp, playerDown, playerLeft, playerRight;
	//player co-ordinates
	private double playerPosX, playerPosY;
	//player animations
	private Animation player, movingUp, movingDown, movingLeft, movingRight;
	//map co-ordinates
	private int mapX;
	private int mapY;
	
	
	public Player(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		this.health = 100;

		
		mapX = Play.getMapX();
		mapY = Play.getMapY();
		
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
		this.player = movingDown;
		this.player.stop();		
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//draw player
		this.player.draw(320,240);
		
		g.setColor(Color.white);
		g.drawString("Player position x: "+ playerPosX+ "\nPlayer position y: " + playerPosY, 400,20);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		//every input is stored here
		Input input = gc.getInput();
		
		//player movement
		if (input.isKeyDown(input.KEY_UP) && PauseMenu.isQuitGame() != true){
					
			player = movingUp;
			player.start();
			playerPosY += delta * .1f;
						
		}

		if (input.isKeyDown(input.KEY_DOWN) && PauseMenu.isQuitGame() != true){

			player = movingDown;
			player.start();
			playerPosY -= delta * .1f;

		}
				
		if (input.isKeyDown(input.KEY_LEFT) && PauseMenu.isQuitGame() != true){
					
			player = movingLeft;
			player.start();
			playerPosX += delta * .1f;

		}

				
		if (input.isKeyDown(input.KEY_RIGHT) && PauseMenu.isQuitGame() != true){
					
			player = movingRight;
			player.start();
			playerPosX -= delta * .1f;
				

		}
		
				
		//detects if player is not moving, stop the anim and display frame 0
		if (!input.isKeyDown(input.KEY_UP) && !input.isKeyDown(input.KEY_DOWN) && !input.isKeyDown(input.KEY_LEFT) &&
			!input.isKeyDown(input.KEY_RIGHT)){
			
			player.stop();
			player.setCurrentFrame(0);
					
		}

		
	}
	
	
	//getters and setters
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public double getPlayerPosX() {
		return playerPosX;
	}

	public void setPlayerPosX(double playerPosX) {
		this.playerPosX = playerPosX;
	}

	public double getPlayerPosY() {
		return playerPosY;
	}

	public void setPlayerPosY(double playerPosY) {
		this.playerPosY = playerPosY;
	}

	public Animation getPlayer() {
		return player;
	}

	public Animation getMovingUp() {
		return movingUp;
	}

	public Animation getMovingDown() {
		return movingDown;
	}

	public Animation getMovingLeft() {
		return movingLeft;
	}

	public Animation getMovingRight() {
		return movingRight;
	}
	
	
	

}
