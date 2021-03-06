package com.tristan.games.revisitinghorror.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tristan.games.revisitinghorror.RevisitingHorror;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 1024;
		config.width = 1200;
		config.title = "Revisiting Horror";
		config.useGL30 = true;
		new LwjglApplication(new RevisitingHorror(), config);
	}
}
