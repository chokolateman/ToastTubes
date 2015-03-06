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
	protected Animation animation = null;
	
	// State time variable to change animation frames 
	private float stateTime = 0;
	
	// Physics variables
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration; 
	private Vector2 center;
	public int initV = -5;
	
	Rectangle bottom, left, right, top;
	
	public Rectangle rect;
	
	// Main menu animation constructor 
	public Toast(Animation anim){
		
		super(anim.getKeyFrame(0));
		animation = anim;
		rect = new Rectangle();
		
		// Movement for update
		velocity = new Vector2(100,220);
		acceleration = new Vector2(0, -100);
		position = new Vector2(47, 78);
		center = new Vector2(this.getX()+this.getImageWidth()/2, 
							this.getY()+this.getImageHeight()/2);
		
		rect.setSize(this.getWidth(), this.getHeight());
	}
	
	@Override
	public void act(float delta){
		
		// Loops the animation of object/image based on time being passed 
		((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(stateTime+=delta, true));
		super.act(delta);
		
		// Physics handling
		velocity.add(acceleration.cpy().scl(delta));
		position.add(velocity.cpy().scl(delta));
		this.setPosition(position.x, position.y);
			
		// Collision detection
		rect.setPosition(this.getX(), this.getY());
	}
	
	// For collision detection
	public Rectangle getRect(){
		return rect;
	}
}
