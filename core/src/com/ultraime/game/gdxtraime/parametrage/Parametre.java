package com.ultraime.game.gdxtraime.parametrage;

import java.util.Locale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

public class Parametre {

	// parametre non modifiable du jeu
	public static final String TITRE_JEU = "LabyChest";

	// parametre modifiable du jeu
	public static boolean ACTIVER_LUMIERE = false;

	// nombre de case pour la taille du monde
	public static final int MONDE_Y = 100;
	public static final int MONDE_X = 100;

	// chemin des polices
	public static final String FONT = "fonts/OpenSans-Bold.fnt";

	// vitesse du jeu
	public static int VITESSE_DE_JEU = 1;
	public static int VITESSE_NORMAL = 1;
	public static int VITESSE_RAPIDE = 2;
	public static int VITESSE_TRES_RAPIDE = 3;
	public static boolean PAUSE = false;

	// taille et paramétrage de l'écran
	/**
	 * Gdx.graphics.getWidth();
	 */
	public static int LARGEUR_ECRAN = 0;

	/**
	 * Gdx.graphics.getHeight();
	 */
	public static int HAUTEUR_ECRAN = 0;
	public static int DECALAGE_Y = 0;

	// mode debug
	public static boolean MODE_DEBUG = false;
	public static boolean MODE_DEBUG_ERR_DEPLACEMENT = false;
	public static boolean MODE_DEBUG_CLICK_CASE = false;
	public static boolean SHOW_FPS = true;

	// AETOILE
	public static int AETOILE_BASE = 250;
	public static int AETOILE_BASE_2 = 1000;

	// langue
	public static I18NBundle bundle;

	// CAMERA
	public static int CAMERA_MAX_HAUT = 550;
	public static int CAMERA_MAX_BAS = 525;
	public static int CAMERA_MAX_GAUCHE = 1000;
	public static int CAMERA_MAX_DROITE = 965;

	public static void initEcran(int largeurEcran, int hauteurEcran) {
		LARGEUR_ECRAN = largeurEcran;
		HAUTEUR_ECRAN = hauteurEcran;
		if (HAUTEUR_ECRAN < 1050) {
			DECALAGE_Y = -10;
			CAMERA_MAX_HAUT = CAMERA_MAX_HAUT - 185;
			CAMERA_MAX_BAS = CAMERA_MAX_BAS - 150;// 375
			CAMERA_MAX_GAUCHE = CAMERA_MAX_GAUCHE - 353;
			CAMERA_MAX_DROITE = CAMERA_MAX_DROITE - 320;
		}
	}

	public static float y(float y) {
		// return y ;
		return (int) y * HAUTEUR_ECRAN / 1080f;
	}

	public static float x(float x) {
		// return x ;
		return (int) x * LARGEUR_ECRAN / 1920f;
	}

	public static void initLangue() {
		FileHandle baseFileHandle = Gdx.files.internal("i18n/Bundle");
		Locale locale = new Locale(Locale.FRANCE.getLanguage(), Locale.FRANCE.getCountry());
		bundle = I18NBundle.createBundle(baseFileHandle, locale);
	}

	public synchronized static void gererPause() {
		if (PAUSE) {
			PAUSE = false;
		} else {
			PAUSE = true;
		}
	}

	public static void gererVitesse(int vitesse) {
		VITESSE_DE_JEU = vitesse;
	}

}
