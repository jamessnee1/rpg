package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

//This class handles all player movement, collision etc

public class Player extends BasicGameState {
	
	//sprite sheets
	private SpriteSheet playerUp, playerDown, playerLeft, playerRight;
	
	//player position
	private double playerPosX, playerPosY;
	
	//animations
	private Animation player, movingUp, movingDown, movingLeft, movingRight;
	
	//quit game bool
	boolean quitGame = false;
	
	//collision detection - todo
	
	
	
	public Player(){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		//set up player position
		playerPosX = 0;
		playerPosY = 0;
		
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
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		//draw player
		player.draw(320,240);
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		//every input is stored here
		Input input = gc.getInput();
		
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
		
		//detects if player is not moving, stop the anim and display frame 0
		if (!input.isKeyDown(input.KEY_UP) && !input.isKeyDown(input.KEY_DOWN) && !input.isKeyDown(input.KEY_LEFT) &&
				!input.isKeyDown(input.KEY_RIGHT)){
					player.stop();
					player.setCurrentFrame(0);
					
				}
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//getters and setters
	public Double getPlayerPosX(){
		return this.playerPosX;
	}
	
	public Double getPlayerPosY(){
		return this.playerPosY;
	}

}
