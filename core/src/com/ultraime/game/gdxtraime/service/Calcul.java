package com.ultraime.game.gdxtraime.service;

public class Calcul {

	public static float arrondirFloat(float f) {
		f = f * 1000;
		f = Math.round(f);
		return f / 1000;
	}
	public static float arrondirFloatmax(float f) {
		f = f * 1000000;
		f = Math.round(f);
		return f / 1000000;
	}
	public static int random(final int min, final int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

}
