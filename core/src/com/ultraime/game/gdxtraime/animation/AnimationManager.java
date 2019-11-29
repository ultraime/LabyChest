package com.ultraime.game.gdxtraime.animation;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class AnimationManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Animation spécifique
	protected transient Animation<TextureRegion> animation[];
	protected transient TextureRegion regionCourante;
	protected float largeur;
	protected float hauteur;
	protected float vitesseAnimation;
	protected transient Texture texture;
	// pour que les plantes ne bouge pas en même temps
	private float tempsAnimation = 0;
	private String lienImage;
	

	/**
	 * @param texture
	 * @param largeur
	 * @param hauteur
	 * @param vitesseAnimation
	 */
	public AnimationManager(Texture texture, int largeur, int hauteur, float vitesseAnimation) {
		creerAnimation(texture, largeur, hauteur, vitesseAnimation);
	}


	private AnimationManager(final float largeur, final float hauteur, final float vitesseAnimation) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.vitesseAnimation = vitesseAnimation;
	}

	/**
	 * @param largeur
	 * @param hauteur
	 * @param vitesseAnimation
	 * @param lienImage
	 */
	public AnimationManager(final float largeur, final float hauteur, final float vitesseAnimation,
			final String lienImage) {
		this(largeur, hauteur, vitesseAnimation);
		this.lienImage = lienImage;
		creerAnimationByLienImage();
	}

	public AnimationManager(AnimationManager animationManager) {
		this(animationManager.largeur, animationManager.hauteur, animationManager.vitesseAnimation);
		this.texture = animationManager.texture;
		this.lienImage = animationManager.lienImage;
		creerAnimation(animationManager.texture, largeur, hauteur, vitesseAnimation);
	}

	/**
	 * @param largeur
	 * @param hauteur
	 * @param vitesseAnimation
	 * @param lienImage
	 */
	public void creerAnimationByLienImage() {
		try {
		Texture txt = new Texture(Gdx.files.internal(lienImage));
		this.texture = txt;
		creerAnimation(txt, largeur, hauteur, vitesseAnimation);
		}catch (Exception e) {
			//TODO Exception qui doit être analysé.
			e.printStackTrace();
			System.err.println("erreur d'animation. Le lien était : "+lienImage);
		}
	}

	/**
	 * @param texture
	 * @param largeur
	 * @param hauteur
	 * @param vitesseAnimation
	 */
	@SuppressWarnings("unchecked")
	public void creerAnimation(Texture texture, float largeur, float hauteur, float vitesseAnimation) {
		int largeur_texture = texture.getWidth();
		int hauteur_texture = texture.getHeight();
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.vitesseAnimation = vitesseAnimation;
		int nbLargeurImage = getNombreImage(largeur_texture, this.largeur);
		int nbHauteurImage = getNombreImage(hauteur_texture, this.hauteur);
		TextureRegion[][] tmp = TextureRegion.split(texture, largeur_texture / nbLargeurImage,
				hauteur_texture / nbHauteurImage);
		this.animation = new Animation[nbHauteurImage];
		for (int i = 0; i < nbHauteurImage; i++) {
			this.animation[i] = new Animation<TextureRegion>(vitesseAnimation, tmp[i]);
		}
		this.regionCourante = (TextureRegion) this.animation[0].getKeyFrame(0, true);
	}

	/**
	 * @param batch
	 * @param x
	 * @param y
	 * @param nbLigne
	 */
	public void render(final SpriteBatch batch, final float x, final float y, final int nbLigne) {
		if (this.animation == null) {
			creerAnimationByLienImage();
		}
		this.tempsAnimation += Gdx.graphics.getDeltaTime();
		this.regionCourante = (TextureRegion) this.animation[nbLigne].getKeyFrame(this.tempsAnimation, true);
		batch.draw(this.regionCourante, x, y, this.largeur, this.hauteur);
	}
	/**
	 * @param batch
	 * @param x
	 * @param y
	 * @param nbLigne
	 */
	public void renderStop(final SpriteBatch batch, final float x, final float y, final int nbLigne) {
		this.tempsAnimation += Gdx.graphics.getDeltaTime();
		this.regionCourante = (TextureRegion) this.animation[nbLigne].getKeyFrame(this.tempsAnimation, false);
		
		batch.draw(this.regionCourante, x, y, this.largeur, this.hauteur);
	}
	/**
	 * @param big_largeur
	 * @param small_largeur
	 * @return big_largeur/small_largeur
	 */
	private int getNombreImage(float big_largeur, float small_largeur) {
		int retour = (int) (big_largeur / small_largeur);
		return retour;
	}

}