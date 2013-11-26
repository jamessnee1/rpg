package game;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/*				 RPG Quest
 * 		   		By James Snee
 * 		Uses Slick2D and LWJGL Libraries
 * 	Todo: 
 * 
 * 
 * 		  Collision Detection
 * 		  Messaging System
 * 		  Inventory System						
 * 		  Health System
 * 	 	  Enemy AI/System
 * 		  Fighting System
 * 		  All pixel art
 * 		  
 * 
 * 										*/


public class Game extends StateBasedGame {
	
	public static final String gamename = "8-Bit Quest v1.0";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int intro = 2;
	
	
	public Game(String gamename) {
		
		super(gamename);
		this.addState(new Intro(intro));
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		
	}
	
	// Inherited method
	//GameContainer handles main game loop etc
	public void initStatesList(GameContainer gc) throws SlickException {
		
		this.getState(intro).init(gc, this);
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		//first game screen
		this.enterState(menu);
		
		
		
	}
	
	//create game window
	static AppGameContainer appgc;

	public static void main(String[] args) {
		
	
		try{
			appgc = new AppGameContainer(new Game(gamename));
			//width, height, fullscreen
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
			
			
		}catch(SlickException e){
			
			//e.printStackTrace();
			Game.processException(e);
			
		}
		

		
	}
	
	//this method puts SlickExceptions into a readable form for printing
	public static void processException(SlickException e){
			
		//Set a string with all errors in it for JDialog
		String error = new String("Error: " +e.getMessage());
			
			JOptionPane errorPane = new JOptionPane(error, JOptionPane.ERROR_MESSAGE);
			   JDialog errorDialog = errorPane.createDialog("Error");
			   errorDialog.setAlwaysOnTop(true);
			   errorDialog.setVisible(true);
			   
			System.err.println("Error message: " + e.getMessage());
			
			
		}

	public static int getMenu() {
		return menu;
	}

	public static int getPlay() {
		return play;
	}

	public static AppGameContainer getAppgc() {
		return appgc;
	}

}
