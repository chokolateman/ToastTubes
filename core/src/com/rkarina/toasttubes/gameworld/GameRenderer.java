package com.rkarina.toasttubes.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.World;

/***
 *  Helper class for rendering game objects
 * @author Rahmadhy Karina 
 *
 */
public class GameRenderer {
	
	private GameWorld myWorld;
	private OrthographicCamera cam;
	
	public GameRenderer(GameWorld world, float gameWidth, float gameHeight){
		myWorld = world;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, gameHeight);
	}

	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
