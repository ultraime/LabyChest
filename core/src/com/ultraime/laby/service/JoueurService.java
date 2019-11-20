package com.ultraime.laby.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ultraime.game.gdxtraime.entite.EntiteVivante;
import com.ultraime.game.gdxtraime.entite.metier.HabiliterGeneral;
import com.ultraime.game.gdxtraime.parametrage.Parametre;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class JoueurService {
	public enum Etat {
		NORMAL, COURS, SAUTE, TOMBE;
	}

	public enum Direction {
		HAUT, BAS, GAUCHE, DROITE, AUCUNE
	}

	public float SPEED = 0;
	public Etat etat = Etat.NORMAL;
	public Direction direction = Direction.AUCUNE;

	public EntiteVivante entiteJoueur;
	public Body body;

	// lumiere du personnage.
	public PointLight torche;

	// variable utilitaire
	public float debut_chute = 0;

	/**
	 * @param rayHandler
	 * @param color
	 * @param distance
	 * @param posX
	 * @param posY
	 */
	public void initialiserTorchePersonnage(final RayHandler rayHandler, final Color color, final float distance) {
		torche = new PointLight(rayHandler, 5000, color, distance, entiteJoueur.x, entiteJoueur.y);
		torche.setSoftnessLength(distance / 4);

	}

	public JoueurService(final EntiteVivante entiteJoueur, final Body body) {
		this.entiteJoueur = entiteJoueur;
		this.entiteJoueur.habiliter.sante[HabiliterGeneral.MAX] = 100;
		this.entiteJoueur.habiliter.sante[HabiliterGeneral.ACTUEL] = 100;
		this.body = body;
	}

	public void update() {

		float vitesse = entiteJoueur.habiliter.vitesse * Parametre.VITESSE_DE_JEU;
		if (body.getLinearVelocity().y > 0.3f) {
			etat = Etat.SAUTE;
		} else if (body.getLinearVelocity().y < -1.5f) {
			if (debut_chute == 0) {
				debut_chute = body.getPosition().y;
			}
			etat = Etat.TOMBE;
		} else if (body.getLinearVelocity().x > 0.01f || body.getLinearVelocity().x < -0.01f) {
			etat = Etat.COURS;
			gestionChute();
			gestionCourse(vitesse);
		} else {
			gestionChute();
			etat = Etat.NORMAL;
		}

		this.entiteJoueur.x = body.getPosition().x * 64;
		this.entiteJoueur.y = body.getPosition().y * 64;
		torche.setPosition(this.entiteJoueur.x + 32, this.entiteJoueur.y + 32);
	}

	private void gestionChute() {
		if (debut_chute != 0) {
			final float fin_chute = body.getPosition().y;
			final float hauteur_chute = debut_chute - fin_chute;
			if (hauteur_chute >= 4f) {
				int sante[] = this.entiteJoueur.habiliter.sante;
				int degat = (int) (hauteur_chute * (hauteur_chute - 2));
				sante[HabiliterGeneral.GAIN] = -(degat);
				this.entiteJoueur.habiliter.gererGain(sante);

				if (sante[HabiliterGeneral.ACTUEL] == 0) {
					//TODO Ecran game over.
					System.err.println("JoueurService : GameOVer");
				}

			}
			debut_chute = 0;
		}
	}

	private void gestionCourse(float vitesse) {
		final Vector2 velocityActual = body.getLinearVelocity();
		if (direction.equals(Direction.DROITE)) {
			SPEED += Gdx.graphics.getDeltaTime() * entiteJoueur.habiliter.acceleration * Parametre.VITESSE_DE_JEU;
			if (SPEED > vitesse) {
				SPEED = vitesse;
			} else if (SPEED < 3f) {
				SPEED = 3f;
			}
		} else if (direction.equals(Direction.GAUCHE)) {
			SPEED -= Gdx.graphics.getDeltaTime() * entiteJoueur.habiliter.acceleration * Parametre.VITESSE_DE_JEU;
			if (SPEED < -vitesse) {
				SPEED = -vitesse;
			} else if (SPEED > -3f) {
				SPEED = -3f;
			}
		}
		body.setLinearVelocity(SPEED, velocityActual.y);
	}

	public void keyDown(int keycode) {
//		float vitesse = entiteJoueur.habiliter.vitesse * Parametre.VITESSE_DE_JEU;

		final Vector2 velocityActual = body.getLinearVelocity();
		switch (keycode) {
		case Input.Keys.SPACE:
			if (!etat.equals(Etat.SAUTE) && !etat.equals(Etat.TOMBE)) {
				body.applyForceToCenter(0, entiteJoueur.habiliter.saut, true);
			}
			break;
		case Input.Keys.D:
			body.setLinearVelocity(SPEED + 3f, velocityActual.y);
			direction = Direction.DROITE;
			break;
		case Input.Keys.Q:
			body.setLinearVelocity(SPEED - 3f, velocityActual.y);
			direction = Direction.GAUCHE;
			break;
		default:
			break;
		}
		this.entiteJoueur.x = body.getPosition().x;
		this.entiteJoueur.y = body.getPosition().y;
	}

	public void keyUp(int keycode) {
		final Vector2 velocityActual = body.getLinearVelocity();
		switch (keycode) {
		case Input.Keys.D:
			stopPersonnage(velocityActual);
			break;
		case Input.Keys.Q:
			stopPersonnage(velocityActual);
			break;
		default:
			break;
		}
		this.entiteJoueur.x = body.getPosition().x;
		this.entiteJoueur.y = body.getPosition().y;

	}

	private void stopPersonnage(final Vector2 velocityActual) {
		body.setLinearVelocity(0, velocityActual.y);
		direction = Direction.AUCUNE;
		SPEED = 0;
	}
}
