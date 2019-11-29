package com.ultraime.laby;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ultraime.game.gdxtraime.animation.AnimationManager;
import com.ultraime.game.gdxtraime.entite.EntiteVivante;

public class EntiteJoueur extends EntiteVivante {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected AnimationManager animationIdlGauche;
	protected AnimationManager animationIdlDroite;
	protected AnimationManager animationMarcheDroite;
	protected AnimationManager animationMarcheGauche;
	protected AnimationManager animationSautDroite;
	protected AnimationManager animationSautGauche;
	public Direction lastDirection = Direction.DROITE;

	public EntiteJoueur(float x, float y, float radius) {
		super(x, y, radius);
	}

	/**
	 * @param largeur
	 * @param hauteur
	 * @param vitesseAnimation
	 * @param lienImage
	 */
	public void creerAnimation() {
		animationManager = new AnimationManager(64, 64, 0.5f, "personnage/mineur/mineur.png");
		animationIdlGauche = new AnimationManager(64, 64, 0.5f, "personnage/mineur/mineur_idl_gauche.png");
		animationIdlDroite = new AnimationManager(64, 64, 0.5f, "personnage/mineur/mineur_idl_droite.png");
		animationMarcheDroite = new AnimationManager(64, 64, 0.05f, "personnage/mineur/mineur_marche_droite.png");
		animationMarcheGauche = new AnimationManager(64, 64, 0.05f, "personnage/mineur/mineur_marche_gauche.png");
		animationSautDroite = new AnimationManager(64, 64, 0.05f, "personnage/mineur/mineur_saut_droite.png");
		animationSautGauche = new AnimationManager(64, 64, 0.05f, "personnage/mineur/mineur_saut_gauche.png");
	}

	/**
	 * @param batch
	 * @param posX
	 * @param posY
	 */
	public void render(final SpriteBatch batch, final float posX, final float posY) {
		if (animationManager != null) {
			if (this.etat == Etat.SAUTE) {
				if (this.lastDirection == Direction.DROITE) {
					this.animationSautDroite.renderStop(batch, posX, posY, 0);
				}else {
					this.animationSautGauche.renderStop(batch, posX, posY, 0);
				}
			} else {
				if (this.lastDirection == Direction.DROITE) {
					if (this.etat == Etat.COURS) {
						this.animationMarcheDroite.render(batch, posX, posY, 0);
					} else {
						this.animationIdlDroite.render(batch, posX, posY, 0);
					}

				} else {
					if (this.etat == Etat.COURS) {
						this.animationMarcheGauche.render(batch, posX, posY, 0);
					} else {
						this.animationIdlGauche.render(batch, posX, posY, 0);
					}

				}
			}
		}
	}

}
