package com.ultraime.laby.monde;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.ConeLight;
import box2dLight.RayHandler;

public class Lumiere {
	public RayHandler rayHandler;
	private ConeLight coneLight;

	public Lumiere(final World world) {
		// creation des éléments
		rayHandler = new RayHandler(world);
		rayHandler.setAmbientLight(0f);
		// 3840.0 5817.9
//		coneLight = new ConeLight(rayHandler, 500, new Color(0x540000FF), 1000, 3840, 5517, 90, 35);
//		coneLight.setActive(true);
	}

	/**
	 * @param OrthographicCamera
	 *            camera
	 */
	@SuppressWarnings("deprecation")
	public void renderLumiere(OrthographicCamera camera) {
		rayHandler.setCombinedMatrix(camera.combined);
		rayHandler.updateAndRender();

	}

	public RayHandler getRayHandler() {
		return rayHandler;
	}

	public void setRayHandler(RayHandler rayHandler) {
		this.rayHandler = rayHandler;
	}
}
