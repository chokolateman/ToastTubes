package com.rkarina.toasttubes.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rkarina.toasttubes.helpers.AssetLoader;

public class ToastTubes extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	
	MainMenuScreen mainMenu = null;
	
	public final static int WIDTH = 800;
	public final static int HEIGHT = 480;
	
	public void create(){
		
		mainMenu = new MainMenuScreen(this);
		
		// Creates a main menu
		this.setScreen(mainMenu);
	}
	
	public void render(){
		super.render();
	}
	
	public void dispose(){
		batch.dispose();
		font.dispose();
	}
}