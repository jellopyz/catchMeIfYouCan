package com.mygdx.game.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class Packer {

	public static void main(String[] args) {
		String input = "C:\\Users\\BANK\\Desktop\\oop game\\Catch me if you can\\core\\assets\\loading1";
		String output = "C:\\Users\\BANK\\Desktop\\oop game\\Catch me if you can\\core\\assets";
		String packFileName = "loading";
		Settings settings =  new TexturePacker.Settings();
		settings.useIndexes = true;
		settings.maxWidth = 8192;
		settings.maxHeight = 8192;
		TexturePacker.processIfModified(settings, input, output, packFileName);
	}

}
