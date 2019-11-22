package com.ultraime.laby.ecran;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.ultraime.game.gdxtraime.carte.Carte;
import com.ultraime.game.gdxtraime.ecran.Ecran;
import com.ultraime.game.gdxtraime.ecran.EcranManagerAbstract;
import com.ultraime.game.gdxtraime.monde.CameraGame;
import com.ultraime.game.gdxtraime.monde.Monde;
import com.ultraime.laby.EntiteJoueur;
import com.ultraime.laby.monde.Lumiere;
import com.ultraime.laby.service.JoueurService;
import com.ultraime.laby.service.LabyService;

public class EcranLaby extends Ecran {

	public Monde monde;
	private CameraGame cameraGame;
	private LabyService labyService;
	private Lumiere lumiere;
	public JoueurService joueurService;
	private boolean isDispose = false;

	@Override
	public void changerEcran(InputMultiplexer inputMultiplexer) {
		inputMultiplexer.addProcessor(this);
	}

	@Override
	public void create(final EcranManagerAbstract ecranManager) {
		this.ecranManager = (EcranManager) ecranManager;
		this.batch = new SpriteBatch();
		Carte carte = new Carte("carte/laby1.tmx");

		this.monde = new Monde(carte);
		this.labyService = new LabyService(monde);
		this.cameraGame = new CameraGame();

		final EntiteJoueur entiteJoueur = new EntiteJoueur(60, 95, 40f);
		final Body bodyJoueur = monde.addEntiteVivante(entiteJoueur);
		joueurService = new JoueurService(entiteJoueur, bodyJoueur, this.ecranManager);

		this.cameraGame.camera.position.x = entiteJoueur.x * 64;
		this.cameraGame.camera.position.y = entiteJoueur.y * 64;

		this.lumiere = new Lumiere(monde.worldAffichage);
		joueurService.initialiserTorchePersonnage(lumiere.rayHandler, new Color(0x540000FF), 500f);

		this.labyService.initialiserCollision(lumiere.rayHandler);

	}

	private void updateCamera() {
		this.cameraGame.updateCamera();

		float posX = MathUtils.round(10f * joueurService.body.getPosition().x * 64) / 10f;
		float posY = MathUtils.round(10f * joueurService.body.getPosition().y * 64) / 10f;
		this.cameraGame.camera.position.x = posX;
		this.cameraGame.camera.position.y = posY;
		OrthographicCamera camera = this.cameraGame.camera;
		batch.setProjectionMatrix(camera.combined);
		this.monde.updateCamera(camera);

	}

	@Override
	public void render() {
		updateCamera();
		this.joueurService.update();
		if (!isDispose) {
			this.batch.begin();
			this.monde.render();
			this.monde.renderDebug(cameraGame.camera);
			this.batch.end();
			this.lumiere.renderLumiere(cameraGame.camera);
		}
	}

	@Override
	public void dispose() {
		this.batch.dispose();
		this.monde.dispose();
		isDispose = true;
	}

	@Override
	public boolean keyDown(int keycode) {
		joueurService.keyDown(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		joueurService.keyUp(keycode);
		switch (keycode) {
		case Input.Keys.E:
			labyService.changementLumiere(joueurService.torche);
		default:
			break;
		}
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
		this.cameraGame.zoom(amount);
		return false;
	}

}
