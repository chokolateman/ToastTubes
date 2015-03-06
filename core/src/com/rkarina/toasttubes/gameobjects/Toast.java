package com.rkarina.toasttubes.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.rkarina.toasttubes.helpers.GameManager;

public class Toast extends Image {
	
	// Animation
	private static Animation animation = null;
	private float stateTime = 0;// state time variable to change the animation 
	// Physics attributes 
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	private Vector2 centre; 
	private float time;
	
	public boolean isStarted = false;
	public GameManager gm = null;
	public int initV = -5;
	public Rectangle bottom, left, right, top;
	public Rectangle rect;
	public float rotation; // For handling toast rotation 
	public boolean launched = false;
	
	// Main menu animation constructor 
	public Toast(Animation anim){
		
		super(anim.getKeyFrame(0));
		
		animation = anim;
		
		rect = new Rectangle();
		
		rect.setSize(this.getWidth(), this.getHeight());
	}
	
	/**
	 * Creates Toast object with animation and actions attached.
	 * 
	 * 
	 */
	public Toast(){
		super(animation.getKeyFrame(0));
		
		TextureRegion[] regions = new TextureRegion[4];
		// Animation sequence
		regions[0] = new TextureRegion(new Texture(Gdx.files.internal("sprite/piloe.png")));
		regions[1] = new TextureRegion(new Texture(Gdx.files.internal("sprite/piloe2.png")));
		regions[2] = new TextureRegion(new Texture(Gdx.files.internal("sprite/piloe.png")));
		regions[3] = new TextureRegion(new Texture(Gdx.files.internal("sprite/piloe2.png")));
		// Add the animation of toast
		animation = new Animation(0.5f, regions);
		
		//Add piloes movement action to its list of actions.
		this.addAction(Actions.moveTo(250, 220+this.getWidth()/2, 2f, Interpolation.sine));
		
		rect = new Rectangle();
		//Movement
		velocity = new Vector2(100,200);
		acceleration = new Vector2(0, -100);
		position = new Vector2(47, 78);
		
		centre = new Vector2(this.getX()+this.getImageWidth()/2, 
							this.getY()+this.getImageHeight()/2);
		
		rect.setSize(this.getWidth(), this.getHeight());
		isStarted = true;
		
		
	}
	

	// Game constructor
	public Toast(float x, float y){
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 100);
	}
	
	
	@Override
	public void act(float delta){
		
		// Loops the animation of object/image based on time being passed 
		((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(stateTime+=delta, true));
		
		super.act(delta);
		
		// When game starts, run the code below 
		if(isStarted){
			
			// Projectile motion formulas
//			double Viyrad = Math.sin(45)*(50);
//			double Vixrad = Math.cos(45)*(50);
//			double Viy = Math.toDegrees(Viyrad);
//			double Vix = Math.toDegrees(Vixrad);
			
			// Displacement calculation
			//velocity.y = (float)Viy * delta + (float)(.5*((-100)*Math.pow(delta, 2)));
			//velocity.x = (float)Vix * delta;
			
			velocity.add(acceleration.cpy().scl(delta));
			position.add(velocity.cpy().scl(delta));
			
			this.setPosition(position.x, position.y);
			
			// Collision detection
			rect.setPosition(this.getX(), this.getY());
		}
	}
	
	
	public void update(float delta){
		velocity.add(acceleration.cpy().scl(delta));
		
		if (velocity.y > 480){
			velocity.y = 480;
		}
		
		position.add(velocity.cpy().scl(delta));
		
	}
	
	
	
	// For collision detection
	public Rectangle getRect(){
			
		return rect;
	}
	
	public void projMotion(int Vi, float delta){
		this.time = (this.time + delta)/100;
		// Projectile motion formulas
		double Viyrad = Math.sin(70)*(-Vi);
		double Vixrad = Math.cos(70)*(-Vi);
		double Viy = Math.toDegrees(Viyrad);
		double Vix = Math.toDegrees(Vixrad);
		
		// Displacement calculation
		double Dy = Viy * this.time + (.5*((9.8)*Math.pow(this.time, 2)));
		double Dx = Vix * this.time;
		this.addAction(Actions.moveBy((float)Dx,(float)Dy));
	}
	
	//
	public void forceMove(){
		
		SequenceAction seq = 
				new SequenceAction(Actions.moveBy(1, 3));
						//Actions.moveTo(this.getX()+2, this.getY()+3),
						//Actions.moveTo(this.getX()+2, this.getY()+3),
						//Actions.moveTo(this.getX()+3, this.getY()+2),
						//Actions.moveTo(this.getX()+3, this.getY()+1));
		this.addAction(seq);
	}
	
	public void onLaunched(){
	    velocity.y = -250;
	}
	
	public float getXpos() {
        return position.x;
    }

    public float getYpos() {
        return position.y;
    }

    public float getRotation() {
        return rotation;
    }
	
	
	
	
	
	
}
