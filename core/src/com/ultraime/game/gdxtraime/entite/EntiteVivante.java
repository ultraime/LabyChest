package com.ultraime.game.gdxtraime.entite;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.ultraime.game.gdxtraime.entite.metier.ActionEntite;
import com.ultraime.game.gdxtraime.entite.metier.HabiliterGeneral;
import com.ultraime.game.gdxtraime.monde.Monde;
import com.ultraime.game.gdxtraime.pathfinding.Noeud;

public abstract class EntiteVivante extends Entite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient public static final int BAS = 0;
	transient public static final int GAUCHE = 1;
	transient public static final int DROITE = 2;
	transient public static final int HAUT = 3;

	public static enum TypeEntiteVivante {
		PERSONNAGE, ZOMBIE, POULE
	}

	public static enum TypeShape {
		CERCLE, RECTANGLE
	}

	// Le type de l'entite
	public TypeShape typeShape;
	protected Circle cercleShape;

	// pour l'animation
	protected AnimationManager animationManager;
	protected int direction = 0;

	// pour le deplacement
	private ArrayDeque<Noeud> listeDeNoeudDeplacement;
	private List<ActionEntite> listeAction;
	private ActionEntite actionEntite = null;

	// élément propre au entité
	public HabiliterGeneral habiliter;
	protected TypeEntiteVivante typeEntiteEnum;

	// etat de l'entite
	public Etat etat = Etat.NORMAL;

	public static enum Etat {
		NORMAL
	}

	// Autre
	public String prenom = "Prenom par defaut";

	protected abstract void creerAnimation();

	/**
	 * @param x
	 * @param y
	 * @param radius
	 */
	public EntiteVivante(final float x, final float y, final float radius, final TypeEntiteVivante typeEntite) {
		super(x, y);
		this.listeDeNoeudDeplacement = new ArrayDeque<Noeud>();
		this.listeAction = new ArrayList<ActionEntite>();
		this.typeShape = TypeShape.CERCLE;
		this.cercleShape = new Circle(x, y, radius);
		this.habiliter = new HabiliterGeneral();
		this.typeEntiteEnum = typeEntite;
		creerAnimation();
	}

	/**
	 * @param batch
	 * @param posX
	 * @param posY
	 */
	public void render(final SpriteBatch batch, final float posX, final float posY) {
		this.animationManager.render(batch, posX, posY, this.direction);
	}

	public List<ActionEntite> getListeAction() {
		return listeAction;
	}

	public void setListeAction(List<ActionEntite> listeAction) {
		this.listeAction = listeAction;
	}

	public void ajouterAction(ActionEntite actionEntite) {
		listeAction.add(actionEntite);

	}

	public ArrayDeque<Noeud> getListeDeNoeudDeplacement() {
		return listeDeNoeudDeplacement;
	}

	public void setListeDeNoeudDeplacement(ArrayDeque<Noeud> listeDeNoeudDeplacement) {
		this.listeDeNoeudDeplacement = listeDeNoeudDeplacement;
	}

	public Circle getCercleShape() {
		return cercleShape;
	}

	public void setCercleShape(Circle cercleShape) {
		this.cercleShape = cercleShape;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
