package com.mygdx.game.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class Packer {

	public static void main(String[] args) {
		String input = "C:\\Users\\BANK\\Desktop\\oop game\\Catch me if you can\\core\\assets\\player";
		String output = "C:\\Users\\BANK\\Desktop\\oop game\\Catch me if you can\\core\\assets\\player";
		String packFileName = "player1";
		Settings settings =  new TexturePacker.Settings();
		settings.useIndexes = true;
		TexturePacker.processIfModified(settings, input, output, packFileName);
	}

}
