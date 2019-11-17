package com.ultraime.laby.service;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.ultraime.game.gdxtraime.entite.EntiteStatic;
import com.ultraime.game.gdxtraime.monde.Monde;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class LabyService {
	public Monde monde;

	public LabyService(final Monde monde) {
		this.monde = monde;
	}

	public void initialiserCollision(RayHandler rayHandler) {
		final TiledMapTileLayer layer = monde.carte.getLayers("mur");
		for (int x = 0; x < layer.getWidth(); x++) {
			for (int y = 0; y < layer.getHeight(); y++) {
				final Cell cell = layer.getCell(x, y);
				if (cell != null && cell.getTile() != null) {
					float largeur = 1f;
					float longueur = 1f;
					float posX = x;
					float posY = y;
					if (cell.getTile().getId() == 5) {
						longueur = 0.5f;
						posY = y + 0.25f;
					} else if (cell.getTile().getId() == 6) {
						longueur = 0.5f;
						posY = y - 0.25f;
					}
					EntiteStatic entiteStatic = new EntiteStatic(posX, posY, largeur, longueur);

					if (cell.getTile().getId() == 4) {
						final float distance = 300f;
						PointLight torche = new PointLight(rayHandler, 500, new Color(0x540000FF), distance,
								x * 64 + 32, y * 64 + 32);
						torche.setSoftnessLength(distance /4);
					}

					monde.addEntiteStatic(entiteStatic);

				}
			}
		}
	}

}
