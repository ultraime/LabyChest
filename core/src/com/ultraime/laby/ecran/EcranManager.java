package com.ultraime.laby.ecran;

import com.badlogic.gdx.Gdx;
import com.ultraime.game.gdxtraime.ecran.EcranDebug;
import com.ultraime.game.gdxtraime.ecran.EcranManagerAbstract;
import com.ultraime.game.gdxtraime.parametrage.Parametre;

public class EcranManager extends EcranManagerAbstract {

	public EcranPrincipal ecranPrincipal;

	@Override
	public void create() {
		Parametre.initLangue();
		Gdx.graphics.setTitle(Parametre.TITRE_JEU);
		ecranDebug = new EcranDebug(this);

		ecranPrincipal = new EcranPrincipal();
		initialiserEcran(ecranPrincipal);
		ecranActuel.create(this);

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
