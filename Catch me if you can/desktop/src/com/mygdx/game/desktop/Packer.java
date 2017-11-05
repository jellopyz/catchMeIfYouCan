package com.mygdx.game.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class Packer {

	public static void main(String[] args) {
		String input = "C:\\Users\\BANK\\Desktop\\oop game\\Catch me if you can\\core\\assets\\exit";
		String output = "C:\\Users\\BANK\\Desktop\\oop game\\Catch me if you can\\core\\assets";
		String packFileName = "exit";
		Settings settings =  new TexturePacker.Settings();
		settings.useIndexes = true;
		TexturePacker.processIfModified(settings, input, output, packFileName);
	}

}
