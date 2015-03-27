/***
 * The main menu screen for the game. 
 */

package com.rkarina.toasttubes.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.rkarina.toasttubes.gameobjects.Toast;
import com.rkarina.toasttubes.helpers.GameManager;

public class MainMenuScreen implements Screen {

	private Stage mainMenu = null;
	
	ToastTubes game = null;
	
	OrthographicCamera camera;
	
	private Music bgMusic;
	
	GameManager gm = null;
	
	public MainMenuScreen(ToastTubes gam){
		
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.WIDTH, game.HEIGHT);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0.2f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		mainMenu.draw();
		mainMenu.act();
		
		camera.update();
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		 
		/*// Background music
		bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/bg_music.mp3"));
		bgMusic.setLooping(true);
	    bgMusic.play();*/
	
	    // Creating main menu stage 
		mainMenu = new Stage(new ScreenViewport(camera));
		
		// Sets the input processor to current stage
		Gdx.input.setInputProcessor(mainMenu);
		
		// Add background, title and tap to start images into the group
		Image bg = new Image(new Texture(Gdx.files.internal("mainmenu/background.jpg")));
		Image title = new Image(new Texture(Gdx.files.internal("mainmenu/title.png")));
		Image tts = new Image(new Texture(Gdx.files.internal("mainmenu/tts_blue.png")));
		Image toaster = new Image(new Texture(Gdx.files.internal("mainmenu/toaster2.png"))); 
		
		 // Creating piloe's animations
		TextureRegion[] regions = new TextureRegion[4];
			
		// Animation sequence
		regions[0] = new TextureRegion(new Texture(Gdx.files.internal("sprite/piloe.png")));
		regions[1] = new TextureRegion(new Texture(Gdx.files.internal("sprite/piloe2.png")));
		regions[2] = new TextureRegion(new Texture(Gdx.files.internal("sprite/piloe.png")));
		regions[3] = new TextureRegion(new Texture(Gdx.files.internal("sprite/piloe2.png")));
				
		// Add the animation of toast
		Animation animation = new Animation(0.5f, regions);
		
		mainMenu.addActor(bg);
		
		// Render piloe onto the screen 
		Toast piloe = new Toast(animation);
		piloe.setPosition(47, 78);
		piloe.isStarted = false;
		mainMenu.addActor(piloe);
		
		// Add the toaster image to screen
		toaster.setPosition(35,35);
		mainMenu.addActor(toaster);
		
		// Add the title image to screen
		title.setPosition(150, 212);
		mainMenu.addActor(title);
		
		// Add the "tap to start" label to screen
		tts.setPosition(200, 93);
		tts.addAction(Actions.forever(Actions.sequence(Actions.fadeOut(1), Actions.fadeIn(1))));
		
		// Goes to the game screen on touch
		tts.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			    return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new GameScreen(game));
				
			}
		});
		
		mainMenu.addActor(tts);
		
		// Add the buttons
		mainMenu.addActor(generateBtns());
		
	}

	private Table generateBtns(){
		
		Table btns = new Table();
		btns.setFillParent(true);
		
		final Image howBtn = new Image(new Texture(Gdx.files.internal("buttons/btn_howto.png")));
		howBtn.setBounds(howBtn.getX(), howBtn.getY(), howBtn.getWidth(), howBtn.getHeight());
		final Image loveBtn = new Image(new Texture(Gdx.files.internal("buttons/btn_love.png")));
		final Image scoreBtn = new Image(new Texture(Gdx.files.internal("buttons/btn_scores.png")));
		final Image settingsBtn = new Image(new Texture(Gdx.files.internal("buttons/btn_settings.png")));
		
		// Add effects to buttons on touch
		howBtn.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				howBtn.addAction(Actions.scaleBy(.1f, .1f, .1f));
			    return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				howBtn.addAction(Actions.scaleBy(-.1f, -.1f, .1f));
			}
		});
		
		scoreBtn.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				scoreBtn.addAction(Actions.scaleBy(.1f, .1f, .1f));
			    return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				scoreBtn.addAction(Actions.scaleBy(-.1f, -.1f, .1f));
			}
		});
		
		settingsBtn.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				settingsBtn.addAction(Actions.scaleBy(.1f, .1f, .1f));
			    return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
			    settingsBtn.addAction(Actions.scaleBy(-.1f, -.1f, .1f));
			}
		});
		
		loveBtn.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			    loveBtn.addAction(Actions.scaleBy(.1f, .1f, .1f));
			    return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				loveBtn.addAction(Actions.scaleBy(-.1f, -.1f, .1f));
			}
		});
		
		// Add the buttons on top of one another (like a stack)
		btns.add(howBtn).row().pad(8);
		btns.add(scoreBtn).row().pad(8);
		btns.add(settingsBtn).row().pad(8);
		btns.add(loveBtn).row();
		
		return btns.right().padRight(40).padBottom(50);
	}
	
	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	
	}

}
