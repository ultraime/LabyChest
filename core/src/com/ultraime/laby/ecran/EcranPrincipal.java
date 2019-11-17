package com.ultraime.laby.ecran;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ultraime.game.gdxtraime.composant.Bouton;
import com.ultraime.game.gdxtraime.ecran.Ecran;
import com.ultraime.game.gdxtraime.ecran.EcranManagerAbstract;
import com.ultraime.game.gdxtraime.parametrage.Parametre;

public class EcranPrincipal extends Ecran {

	private Bouton boutonStartPartieTest;
	private Bouton boutonStartPartie;
	private Bouton boutonLoadPartie;

	@Override
	public void changerEcran(InputMultiplexer inputMultiplexer) {
		inputMultiplexer.addProcessor(this);
	}

	@Override
	public void create(final EcranManagerAbstract ecranManager) {
		this.ecranManager = (EcranManager) ecranManager;
		this.batch = new SpriteBatch();
		String label = "Lancer partie Test";
		this.boutonStartPartieTest = new Bouton(Parametre.x(752), Parametre.y(780), Parametre.x(300), Parametre.y(50),
				label, Bouton.CLASSIQUE);

		String label2 = Parametre.bundle.get("txt.menu.start");
		this.boutonStartPartie = new Bouton(Parametre.x(752), Parametre.y(600), Parametre.x(300), Parametre.y(50),
				label2, Bouton.CLASSIQUE);

		String label3 = Parametre.bundle.get("txt.menu.load");
		this.boutonLoadPartie = new Bouton(Parametre.x(752), Parametre.y(420), Parametre.x(300), Parametre.y(50),
				label3, Bouton.CLASSIQUE);

	}

	@Override
	public void render() {
		this.batch.begin();
		this.boutonStartPartieTest.render(batch);
		this.boutonStartPartie.render(batch);
		this.boutonLoadPartie.render(batch);
		this.batch.end();
	}

	@Override
	public void dispose() {
		this.batch.dispose();
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

		this.boutonStartPartieTest.isClique(screenX, screenY);
		if (this.boutonStartPartie.isClique(screenX, screenY)) {
			this.ecranManager.ecranLaby.create(this.ecranManager);
			this.ecranManager.initialiserEcran(this.ecranManager.ecranLaby);
		}
		this.boutonLoadPartie.isClique(screenX, screenY);

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		this.boutonStartPartieTest.touchUP(screenX, screenY);
		this.boutonStartPartie.touchUP(screenX, screenY);
		this.boutonLoadPartie.touchUP(screenX, screenY);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		this.boutonStartPartieTest.isOver(screenX, screenY);
		this.boutonStartPartie.isOver(screenX, screenY);
		this.boutonLoadPartie.isOver(screenX, screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
