package com.rkarina.toasttubes.helpers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.rkarina.toasttubes.gameobjects.Toast;
import com.rkarina.toasttubes.gameobjects.Tube;
import com.rkarina.toasttubes.screens.GameScreen;
import com.rkarina.toasttubes.screens.ToastTubes;

public class GameManager implements  com.badlogic.gdx.scenes.scene2d.EventListener{
	
	private final static int WIDTH = 800;
	private final static int HEIGHT = 480;

	


	private static int gap = 250; 
	private ToastTubes game = null;
	private Stage gameStage = null;
	private Pool<Tube> tubePool = null;
	private Array<Tube> tubeArray = new Array<Tube>();
	public Array<Tube> getTubeArray() {
		return tubeArray;
	}




	private GameScreen gameScreen = null;
	private int posXBck1=0, posXBck2=0;
	private int maxX = 275;
	private boolean move;
	private int distance = 0;
	private static float gravity = -9.8f;
	private int initVel = 3;
	private TubeData td = new TubeData();
	
	public Toast piloe = null;
	public int score = 0;
	public boolean gameStart = false;
	public boolean gameOver = false;
	
	
	
	public GameManager(ToastTubes gm, GameScreen gmScreen, Stage gmStage){
		
		game = gm;
		
		gameStage = gmStage;
	
		gameScreen = gmScreen;
		
	}
	
	public void createToast(){
		// Initial position in the game screen
		piloe = new Toast();
		piloe.addListener(this);
		//gameStage.addActor(piloe);
	}
	
	public void initTube(){
		
		//Creating tube animation.
		TextureRegion[] regions = new TextureRegion[1];
		regions[0] = new TextureRegion(new Texture(Gdx.files.internal("entrance_tubes/greentube2.png")));
		final Animation animation = new Animation(.05f, regions);
		
		Tube tube = new Tube(animation);
		tube.init(250, HEIGHT);
		
		
		tube.addAction(Actions.moveTo(tube.getX(), HEIGHT/2, 2f));
		
		//gameStage.addActor(tube);
		tubeArray.add(tube);
	}
	
	public void createTubes(){
		if(tubeArray.size <4){
			TextureRegion[] regions = new TextureRegion[1];
			
			regions[0] = new TextureRegion(
						new Texture(Gdx.files.internal("middle_tubes/greentube"+randInt(1,2)+".png")));
			
			final Animation animation = new Animation(.05f, regions);
			
			Tube tube = new Tube(animation);
			tube.init(gap+(tubeArray.size*gap), HEIGHT);
			tube.addAction(Actions.moveTo(tube.getX(), 52f, 4f));
			gameStage.addActor(tube);
			tubeArray.add(tube);
		}
		
	/*	Timer tubeTimer = new Timer();*/
		
	
		
		
		/*
		Timer.schedule(new Task(){
		
			@Override
			public void run(){
		final Tube tube = tubePool.obtain();
				
		tube.init(gap+(tubeArray.size*gap), HEIGHT);
				
		tubeArray.add(tube);
				
		tube.addAction(Actions.sequence(Actions.moveTo(tube.getX(), 52f, 4f), 
				
				Actions.run(new Runnable(){
							
					@Override
					public void run(){
						tubeArray.removeValue(tube,  true);
						tube.remove();
						tubePool.free(tube);
					}
				})
				));
					
			// Stops tube from falling on click 
			tube.addListener(new InputListener(){
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}
				public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
					tube.onClick();
				}
			});
			
		gameStage.addActor(tube);
		}
	},1,3);*/
	}
	
	// Checks whether two images collide or not
	public void checkPiloeCollisions(){
		
		Iterator<Tube> it = tubeArray.iterator();
		
		Tube tube;
		
		while(it.hasNext()){
			
			tube = it.next();
			
			boolean collision = Intersector.overlaps(tube.getRect(), piloe.getRect());
			
			if(collision){
				createTubes();
				
			}
		}
		
	}
	
	
	
	
	
//		public void bgLoop(){
//			    posXBck2 = posXBck1 + (int)gameStage.getActors().first().getWidth();
//			    if(gameStage.set>=posXBck2+cam.viewportWidth/2){
//			        posXBck1=posXBck2;
//			    }
//			}
//			
//		}
	
	// Random number generator for generating random tubes 
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	public static int getHeight() {
		return HEIGHT;
	}
	
	


	@Override
	public boolean handle(Event event) {
		// TODO Auto-generated method stub
		return true;
	}
	
}

