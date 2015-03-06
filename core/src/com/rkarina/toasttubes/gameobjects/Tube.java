package com.rkarina.toasttubes.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.rkarina.toasttubes.helpers.GameManager;
import com.rkarina.toasttubes.helpers.TubeData;



public class Tube extends Image implements Poolable{
	
	protected Animation animation = null;
	private float stateTime = 0;
	public boolean visible = true;
	
	// Has it been stopped?
	private boolean stopped = false;
	
	public Rectangle rectEnt, rectEx;
	
	public Tube(Animation anim){ 
		
		super(anim.getKeyFrame(0));
		animation = anim;
		visible = true;
		rectEnt = new Rectangle();
		rectEnt.setSize(this.getWidth(), this.getHeight());
	}
	
	@Override 
	public void act(float delta){
		
		// Animation for the tubes 
		((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(stateTime+delta, true));
				
		// Tubes act method
		super.act(delta);
		
		// Rectangle needs to follow the tube
		rectEnt.setPosition(this.getX(), this.getY());
	}
	
	// Initialize the position of the tube
	public void init(float posX, float posY){
		this.setPosition(posX, posY);
		visible = true;
	}
	
	@Override
	public void reset(){
		this.setPosition(600, 25);
		this.clearActions();
		visible = false;
		rectEnt.setPosition(this.getX(), this.getY());
		
	}
	
	// For collision detection
	public Rectangle getRect(){
		return rectEnt;
	}
		
	// Stops the tube if it has been tapped
	public void onClick(){
		this.stopped = true;
		this.addAction(Actions.moveBy(0,0));
	}
}
