package com.ultraime.laby.ecran;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ultraime.game.gdxtraime.ecran.Ecran;
import com.ultraime.game.gdxtraime.ecran.EcranManagerAbstract;
import com.ultraime.game.gdxtraime.entite.metier.HabiliterGeneral;
import com.ultraime.laby.service.JoueurService;

public class EcranHud extends Ecran {
	private BitmapFont font;
	private SpriteBatch batch;
	public JoueurService joueurService;

	@Override
	public void create(EcranManagerAbstract ecranManager) {
		this.ecranManager = (EcranManager) ecranManager;
		font = new BitmapFont();
		font.setColor(Color.RED);
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		batch.begin();
		final int vieActuel = joueurService.entiteJoueur.habiliter.sante[HabiliterGeneral.ACTUEL];
		final int vieMax = joueurService.entiteJoueur.habiliter.sante[HabiliterGeneral.MAX];
		font.draw(batch, vieActuel+"/"+vieMax, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 10);

		batch.end();

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void changerEcran(InputMultiplexer inputMultiplexer) {
		inputMultiplexer.addProcessor(this);
	}

	@Override
	public void dispose() {
	}

}
