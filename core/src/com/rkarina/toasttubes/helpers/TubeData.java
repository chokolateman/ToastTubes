package com.rkarina.toasttubes.helpers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

// Class that stores all the data of the tubes
public class TubeData {
	public Array<Vector2> greentube1 = new Array<Vector2>();
	
	public TubeData(){
		
		// Greentube1 data
		greentube1.add(new Vector2(25,33));
		greentube1.add(new Vector2(41,34));
		greentube1.add(new Vector2(64,44));
		greentube1.add(new Vector2(79,62));
		greentube1.add(new Vector2(83,80));
		greentube1.add(new Vector2(83,115));
	}
	
}
