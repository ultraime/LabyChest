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
	}

	/**
	 * @param batch
	 * @param posX
	 * @param posY
	 */
	public void render(final SpriteBatch batch, final float posX, final float posY) {
		if (animationManager != null) {
			if(this.lastDirection == Direction.DROITE) {
				this.animationIdlDroite.render(batch, posX, posY, 0);
			}else {
				this.animationIdlGauche.render(batch, posX, posY, 0);
			}
		}
	}

}
