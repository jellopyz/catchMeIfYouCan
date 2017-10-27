package com.mygdx.game.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class Packer {

	public static void main(String[] args) {
		String input = "C:\\Users\\Mickey\\Downloads\\test\\core\\assets\\input";
		String output = "C:\\Users\\Mickey\\Downloads\\test\\core\\assets\\output";
		String packFileName = "arrowweapon";
		Settings settings =  new TexturePacker.Settings();
		settings.useIndexes = true;
		TexturePacker.processIfModified(settings, input, output, packFileName);
	}

}
