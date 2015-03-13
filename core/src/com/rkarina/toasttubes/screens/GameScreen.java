package com.rkarina.toasttubes.screens;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.rkarina.toasttubes.gameobjects.Toast;
import com.rkarina.toasttubes.gameworld.GameRenderer;
import com.rkarina.toasttubes.gameworld.GameWorld;
import com.rkarina.toasttubes.helpers.GameManager;
import com.rkarina.toasttubes.helpers.InputHandler;

public class GameScreen implements Screen {
	
	Stage gameStage = null;
	
	private Image game_bg = null;
	private Texture background1 = null;
	private Texture background2 = null;
	ToastTubes game = null;
	
	private GameManager manager = null;
	
	private GameWorld world;
	private GameRenderer renderer;
	
	private OrthographicCamera camera;
	private float runTime;
	
	public GameScreen(ToastTubes gam){
		
		game = gam;
		
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		world = new GameWorld();
		renderer = new GameRenderer(world, screenWidth, screenHeight); 
		
		Toast piloe = world.getToast();
		
		//camera = new OrthographicCamera();
		//camera.setToOrtho(false, 800, 480);
		
		//Gdx.input.setInputProcessor(new InputHandler(world.getToast()));
	}
	
	@Override
	public void render(float delta) {
		
		// Actors on the show method runs first
		
		
		// Call update using the GameWorld
		//world.update(delta);
		
		// Call render method using GameRenderer
		//renderer.render();
		runTime += delta;
		gameStage.draw();
		gameStage.act();
		gameStage.addAction(Actions.moveBy(-.8f, 0));
		manager.checkPiloeCollisions();
		System.out.println(gameStage.getActors());
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
		gameStage = new Stage(new StretchViewport (800,480));
		background1 = new Texture(new Pixmap(Gdx.files.internal("mainmenu/background.jpg")));
		background1.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		background2 = new Texture(new Pixmap(Gdx.files.internal("mainmenu/background.jpg")));
		
		game_bg = new Image(background1);
		
		gameStage.addActor(game_bg);
		
		manager = new GameManager(game, this, gameStage);
		manager.createToast();
		manager.gameStart = true;
		
		// The first tube which automatically 
		manager.initTube();
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
	}

	@Override
	public void dispose() {
	}

}
