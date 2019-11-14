package com.ultraime.game.gdxtraime.monde;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.ultraime.game.gdxtraime.parametrage.Parametre;

public class Monde {

	// Le vrai monde, avec les stats normal (petit nombre)
	public World world;
	public ArrayList<Body> bodiesEntiteVivant = new ArrayList<Body>();

	// World utilisé surtout pour le debug
	public World worldAffichage;
	public ArrayList<Body> bodiesAffichageEntiteVivant = new ArrayList<Body>();

	public Box2DDebugRenderer debugRenderer;

	private SpriteBatch batch;

	/**
	 * correspond à la taille d'un tile. Par défaut, le multiplicateur = 64
	 */
	public static int MULTIPLICATEUR = 64;

	private Body bodyTouche;

	private int tempsAnimation = 0;

	public Monde() {
		world = new World(new Vector2(0, 0), true);
		worldAffichage = new World(new Vector2(0, 0), true);
		this.debugRenderer = new Box2DDebugRenderer();
		this.batch = new SpriteBatch();

	}

	public void render() {
		if (!Parametre.PAUSE) {
			world.step(1 / 60f, 6, 2);
			worldAffichage.step(1 / 60f, 6, 2);
		}
		tempsAnimation += Gdx.graphics.getDeltaTime();
		this.batch.begin();

		gestionBodies();

		this.batch.end();
	}

	public Body recupererBodyFromEntite(final EntiteVivante entiteVivante) {
		Body retourBody = null;
		ArrayList<Body> bodies = Monde.getInstance().bodiesEntiteVivant;
		for (int i = 0; i < bodies.size(); i++) {
			final Body body = bodies.get(i);
			if (body.getUserData() instanceof EntiteVivante) {
				final EntiteVivante ev = (EntiteVivante) body.getUserData();
				if (ev == entiteVivante) {
					retourBody = body;
					break;
				}
			}
		}
		return retourBody;
	}

	private void gestionBodies() {

		ArrayList<Body> bodies = bodiesEntiteVivant;

		for (int i = 0; i < bodies.size(); i++) {
			final Body body = bodies.get(i);
			if (body.getUserData() instanceof EntiteVivante) {
				final EntiteVivante entiteVivante = (EntiteVivante) body.getUserData();
				// entiteJoueur.doMetier();
				entiteVivante.doAction(body, world, worldAffichage);
				entiteVivante.render(batch);
			}

		}

	}

	/**
	 * @param OrthographicCamera camera
	 */
	public void renderDebug(final OrthographicCamera camera) {
		if (Parametre.MODE_DEBUG) {
			try {
				this.debugRenderer.render(world, camera.combined);
				this.debugRenderer.render(worldAffichage, camera.combined);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
	}

	public void creerCollision(final int x, final int y) {
		EntiteStatic entiteStatic = new EntiteStatic(x, y, 1, 1);
		Base.getInstance().creerRectangleStatic(this.world, this.worldAffichage, x, y, 1, 1, entiteStatic);
	}

	public void retirerCollision(int posX, int posY) {
		Base.getInstance().retirerRectangleStatic(this.world, this.worldAffichage, posX, posY);
	}

	/**
	 * @param TiledMapTileLayer layer
	 */
	public void initialiserCollision(final TiledMapTileLayer layer) {
		for (int x = 0; x < layer.getWidth(); x++) {
			for (int y = 0; y < layer.getHeight(); y++) {
				final Cell cell = layer.getCell(x, y);
				if (cell != null && cell.getTile() != null) {
					final int id = cell.getTile().getId();
					if (TileMurManager.isMurEnBois(id)) {
						EntiteStatic entiteStatic = new EntiteStatic(x, y, 1, 1);
						Base.getInstance().creerRectangleStatic(this.world, this.worldAffichage, x, y, 1, 1,
								entiteStatic);
						ElementEarth elementEarth = new ElementEarth(
								Base.getInstance().recupererElementEarthByNom("mur_en_bois"));
						elementEarth.x = x;
						elementEarth.y = y;
						Base.getInstance().ajouterElementEarth(elementEarth);
					}
				}
			}
		}
	}

	/**
	 * @param screenX
	 * @param screenY
	 * @param pointer
	 * @param button
	 * @param OrthographicCamera
	 * @return
	 */
	public Body touchDown(final int screenX, final int screenY, final int pointer, final int button,
			final OrthographicCamera camera) {
		Body bodyToucheAretourner = null;
		final Vector3 point = new Vector3();
		point.set(screenX, screenY, 0);
		camera.unproject(point);

		QueryCallback callback = new QueryCallback() {
			@Override
			public boolean reportFixture(Fixture fixture) {
				if (fixture.testPoint(point.x, point.y)) {
					bodyTouche = fixture.getBody();
					return false;
				} else {
					return true;
				}

			}
		};
		worldAffichage.QueryAABB(callback, point.x - 0.1f, point.y - 0.1f, point.x + 0.1f, point.y + 0.1f);
		if (bodyTouche != null) {
			bodyToucheAretourner = bodyTouche;
			bodyTouche = null;
		}
		return bodyToucheAretourner;
	}

	public void updateCamera(OrthographicCamera camera) {
		batch.setProjectionMatrix(camera.combined);

	}

}
