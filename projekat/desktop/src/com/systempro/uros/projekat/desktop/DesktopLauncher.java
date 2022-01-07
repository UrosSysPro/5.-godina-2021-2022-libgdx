package com.systempro.uros.projekat.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.systempro.uros.projekat.Rose;
import com.systempro.uros.projekat.shader2.Main2;
import com.systempro.uros.projekat.shaderPaint.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=800;
		config.height=600;

		new LwjglApplication(new Main(), config);
	}
}
