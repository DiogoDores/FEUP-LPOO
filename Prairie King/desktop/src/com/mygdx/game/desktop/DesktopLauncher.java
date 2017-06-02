package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.prairieKing.PrairieKing;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;

		config.vSyncEnabled = true;
		config.height = 768;
		config.width = 1366;
		config.fullscreen = true;
		new LwjglApplication(new PrairieKing(), config);
	}
}
