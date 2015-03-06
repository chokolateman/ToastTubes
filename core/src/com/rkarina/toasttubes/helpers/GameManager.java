package com.rkarina.toasttubes.helpers;

import java.util.Arrays;
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

public class GameManager {

		private static int gap = 250; 
	
		private ToastTubes game = null;
		private Stage gameStage = null;
		
		public Toast piloe = null;
		
		private Pool<Tube> tubePool = null;
		
		private Array<Tube> tubeArray = new Array<Tube>();
		
		public int score = 0;
		
		public boolean gameStart = false;
		
		public boolean gameOver = false;
		
		private GameScreen gameScreen = null;
		
		private int posXBck1=0, posXBck2=0;
		
		private int maxX = 275;
		
		private int distance = 0;
		
		private static float gravity = -9.8f;
		
		private int initVel = 3;
		
		private TubeData td = new TubeData();
		//OrthographicCamera cam = new OrthographicCamera();
		
		public GameManager(ToastTubes gm, GameScreen gmScreen, Stage gmStage){
			
			game = gm;
			
			gameStage = gmStage;
		
			gameScreen = gmScreen;
			
		}
		
		public void createToast(){
			
			// Creating piloe's animations
			TextureRegion[] regions = new TextureRegion[4];
				
			// Animation sequence
			regions[0] = new TextureRegion(new Texture(Gdx.files.internal("sprite/piloe.png")));
			regions[1] = new TextureRegion(new Texture(Gdx.files.internal("sprite/piloe2.png")));
			regions[2] = new TextureRegion(new Texture(Gdx.files.internal("sprite/piloe.png")));
			regions[3] = new TextureRegion(new Texture(Gdx.files.internal("sprite/piloe2.png")));
					
			
			
			// Add the animation of toast
			Animation animation = new Animation(0.5f, regions);
			
			piloe = new Toast(this, animation);
			
			// Initial position in the game screen 
			piloe.setPosition(47, 78);
			
//			for(int i=0;i<td.green[0].length;i++){
//				String[] coords = td.green[0][i].split(",");
//				System.out.println(coords[0]);
//				System.out.println(coords[1]);
//				piloe.addAction(Actions.moveBy(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));
//			}
			
			piloe.addAction(Actions.moveTo(250, 220+this.piloe.getWidth()/2, 2f, Interpolation.sine));
			
			gameStage.addActor(piloe);
			
			// Static toaster image 
			Image toaster = new Image(new Texture(Gdx.files.internal("mainmenu/toaster2.png"))); 
									
			toaster.setPosition(35,35);
									
			gameStage.addActor(toaster);
		}
		
		// Function for the first tube
		public void initTube(){
			
			TextureRegion[] regions = new TextureRegion[1];
			
			regions[0] = new TextureRegion(
						new Texture(Gdx.files.internal("entrance_tubes/greentube2.png")));
			
			final Animation animation = new Animation(.05f, regions);
			
			this.tubePool = new Pool<Tube>(){
				
				@Override
				protected Tube newObject(){
					
					return new Tube(animation);
				}
			};
			
			final Tube tube = tubePool.obtain();
			tube.type = 2;
			tube.init(250, game.HEIGHT);
			
			tubeArray.add(tube);
			tube.addAction(Actions.moveTo(tube.getX(), game.HEIGHT/2, 2f));
			
			gameStage.addActor(tube);
			System.out.println("Works here");
		}
		
		// Generates a single green tube from the top, falling down. 
		public void generateTube(){
			
			TextureRegion[] regions = new TextureRegion[1];
			
			regions[0] = new TextureRegion(
						new Texture(Gdx.files.internal("middle_tubes/greentube"+randInt(1,2)+".png")));
			
			final Animation animation = new Animation(.05f, regions);
			
			this.tubePool = new Pool<Tube>(){
				
				@Override
				protected Tube newObject(){
					
					return new Tube(animation);
				}
			};
			
			Timer.schedule(new Task(){
			
				@Override
				public void run(){
			final Tube tube = tubePool.obtain();
					
			tube.init(gap+(tubeArray.size*gap), game.HEIGHT);
					
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
		},1,3);
		}
		
		// Checks whether two images collide or not
		public void checkCollisions(){
			
			Iterator<Tube> it = tubeArray.iterator();
			
			Tube tube;
			
			while(it.hasNext()){
				
				tube = it.next();
				
				boolean collision = Intersector.overlaps(tube.getRect(), piloe.getRect());
				
				if(collision){
					
					
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
}
