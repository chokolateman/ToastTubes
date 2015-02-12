package com.rkarina.toasttubes.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.rkarina.toasttubes.gameobjects.Toast;
import com.rkarina.toasttubes.helpers.GameManager;

/***
 *  Helper class for updating game objects
 * @author Rahmadhy Karina 
 *
 */
public class GameWorld {
	private Toast piloe;
	private GameManager gm;
	
	public GameWorld(){
		// Initialize toast here 
		piloe = new Toast(47, 78);
	}
	
	public void update(float delta){
		piloe.update(delta);
	}
	
	public Toast getToast(){
		return piloe;
	}
}
