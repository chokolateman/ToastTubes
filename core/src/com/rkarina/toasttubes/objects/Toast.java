package com.rkarina.toasttubes.objects;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;;

public class Toast {
	Rectangle bottom, left, right, top;
	Sprite sprite;
	int action;
	float velocityY;
	private Texture bucketImage;
	public Rectangle bucket;
	public float x;
	public int y;
	
	public Toast(){
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		bucket = new Rectangle(0f,0f,64f,64f);
		bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
	    bucket.y = 20; // bottom left corner of the bucket is 20 pixels above
	                    // the bottom screen edge
	    bucket.width = 64;
	    bucket.height = 64;
		bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
        bucket.y = 20; // bottom left corner of the bucket is 20 pixels above
                        // the bottom screen edge
        bucket.width = 64;
        bucket.height = 64;
	}

	public void hits(Rectangle r){
		
	}
	
	public void update(float delta){
	
	}
	
	public void setPosition(){
		
	}
	
	public void draw(SpriteBatch batch){
	
	}
	
}
