package com.rkarina.toasttubes.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rkarina.toasttubes.screens.ToastTubes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Toast'n'Tubes";
		config.width=800;
		config.height=480;
		new LwjglApplication(new ToastTubes(), config);
	}
}
