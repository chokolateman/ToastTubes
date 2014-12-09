package com.rkarina.toasttubes.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ToastTubes extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	
	public void create(){
		batch = new SpriteBatch();
		
		font = new BitmapFont();
		
		// Creates a main menu screen before playing the game screen
		// and sets the current screen the user will see
		this.setScreen(new MainMenuScreen(this));
	}
	
	public void render(){
		super.render();
	}
	
	public void dispose(){
		batch.dispose();
		font.dispose();
	}
}