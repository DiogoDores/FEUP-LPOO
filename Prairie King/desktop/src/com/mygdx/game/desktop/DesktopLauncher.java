package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.prairieKing.controller.PrairieKing;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;

		config.vSyncEnabled = true;
		//config.fullscreen = true;
		//config.useGL30 = true;
		config.height = 720;
		config.width = 1280;
		new LwjglApplication(new PrairieKing(), config);
	}
}
