package com.rkarina.toasttubes.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture texture;
	public static TextureRegion bg;
	
	public static Animation toastAnimation;
	public static TextureRegion toast, toastDown, toastUp;
	
	public static TextureRegion tubeUp, tubeDown, bar;
	
	public static void load(){
		texture = new Texture (Gdx.files.internal("mainmenu/bg.jpg"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg = new TextureRegion(texture, 0, 0, 460, 800);
		bg.flip(false, true);
		
		toastDown = new TextureRegion(texture, 136, 0, 17, 12);
		toastDown.flip(false,  true);
		
		toastUp = new TextureRegion(texture, 170, 0, 17, 12);
		toastUp.flip(false, true);
		
		TextureRegion[] toasts = { toastDown, toast, toastUp };
		toastAnimation = new Animation(0.06f, toasts);
		toastAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		tubeUp = new TextureRegion(texture, 192, 0, 24, 14);
		// Create by flipping existing tubeUp
		tubeDown = new TextureRegion(tubeUp);
		tubeDown.flip(false, true);
		
		bar = new TextureRegion(texture, 136, 16, 22, 3);
		bar.flip(false,  true);
		
	}
	
	public static void dispose(){
		 // We must dispose of the texture when we are finished
		texture.dispose();
	}
	
}
