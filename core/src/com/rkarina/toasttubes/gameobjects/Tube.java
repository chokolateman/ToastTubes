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
	
	private boolean closest = false;
	
	private TubeData tb = new TubeData();
	
	protected Animation animation = null;
	private float stateTime = 0;
	public boolean visible = true;
	// Type of tube (1, 2, 3 or 4)
	public int type;
	
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
	
	// For collision detection
	public Rectangle getRect(){
		
		return rectEnt;
	}
	
	@Override 
	public void act(float delta){
		
		// Animation for the tubes 
		((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(stateTime+delta, true));
				
		// Tubes act
		super.act(delta);
		
		// Moves along the x-axis as the toast does!
//		if(!stopped){
//			this.setPosition(this.getX()-1, this.getY()-1);
//		}
		
		// Rectangle needs to follow the tube
		rectEnt.setPosition(this.getX(), this.getY());
	}
	
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
	
	public void onClick(){
		// if the user touches the area underneath the tube, then it stops
		this.stopped = true;
		this.addAction(Actions.moveTo(this.getX(), this.getY()));
	}
	
	
	
}
