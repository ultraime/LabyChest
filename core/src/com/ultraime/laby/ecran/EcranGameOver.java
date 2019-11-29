package com.ultraime.laby.ecran;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ultraime.game.gdxtraime.composant.Bouton;
import com.ultraime.game.gdxtraime.ecran.Ecran;
import com.ultraime.game.gdxtraime.ecran.EcranManagerAbstract;
import com.ultraime.game.gdxtraime.parametrage.Parametre;

public class EcranGameOver extends Ecran {

	private Bouton boutonRejouer;
	private Bouton boutonQuitter;
	private Sprite background;
	private Sprite gameOver;
	public boolean isDispose = false;
	@Override
	public void changerEcran(InputMultiplexer inputMultiplexer) {
		inputMultiplexer.addProcessor(this);
	}

	@Override
	public void create(final EcranManagerAbstract ecranManager) {
		this.ecranManager = (EcranManager) ecranManager;
		this.batch = new SpriteBatch();

		String label2 = Parametre.bundle.get("txt.menu.restart");
		this.boutonRejouer = new Bouton(Parametre.x(752), Parametre.y(600), Parametre.x(300), Parametre.y(50), label2,
				Bouton.CLASSIQUE);

		String label3 = Parametre.bundle.get("txt.menu.quit");
		this.boutonQuitter = new Bouton(Parametre.x(752), Parametre.y(420), Parametre.x(300), Parametre.y(50), label3,
				Bouton.CLASSIQUE);
		background = new Sprite( new Texture(Gdx.files.internal("images/bouton/btn_over.png")));
		this.background.setSize(Parametre.LARGEUR_ECRAN, Parametre.HAUTEUR_ECRAN);
		
		
		gameOver = new Sprite( new Texture(Gdx.files.internal("images/menu/game_over.png")));
		gameOver.setPosition(Parametre.x(752),700);
	}

	@Override
	public void render() {
		
		this.batch.begin();
		this.background.draw(batch);
		this.gameOver.draw(batch);
		this.boutonRejouer.render(batch);
		this.boutonQuitter.render(batch);
		this.batch.end();
		if(isDispose) {
			dispose();
		}
	}

	@Override
	public void dispose() {
//		this.batch.dispose();
		this.boutonQuitter.dispose();
		this.boutonRejouer.dispose();
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

		if (this.boutonRejouer.isClique(screenX, screenY)) {
//			isDispose  = true;
			
			this.ecranManager.ecranLaby.create(this.ecranManager);
			this.ecranManager.initialiserEcran(this.ecranManager.ecranLaby);
			this.ecranManager.ecranHudLaby.joueurService = this.ecranManager.ecranLaby.joueurService;
		}

		if (this.boutonQuitter.isClique(screenX, screenY)) {
			System.exit(0);
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		this.boutonRejouer.touchUP(screenX, screenY);
		this.boutonQuitter.touchUP(screenX, screenY);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		this.boutonRejouer.isOver(screenX, screenY);
		this.boutonQuitter.isOver(screenX, screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
