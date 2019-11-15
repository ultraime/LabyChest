package com.ultraime.game.gdxtraime.entite.metier;

import java.io.Serializable;

import com.ultraime.game.gdxtraime.entite.EntiteVivante.TypeEntiteVivante;

public class HabiliterGeneral implements Serializable {

	/**
	 * Pour les stats de base
	 */
	public TypeEntiteVivante type;

	public transient static final int GAIN = 0;
	public transient static final int ACTUEL = 1;
	public transient static final int MAX = 2;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// stat de base
	public float vitesse = 4f;// 3f
	public int sante[];
	public int energie[];

	public HabiliterGeneral() {
		sante = new int[3];
		energie = new int[3];
	}

	public HabiliterGeneral(final HabiliterGeneral habiliter) {
		this();
		this.type = habiliter.type;
		initStatAvecHabiliter(habiliter);

	}

	private void initStatAvecHabiliter(final HabiliterGeneral habiliter) {
		sante[GAIN] = new Integer(habiliter.sante[GAIN]);
		sante[ACTUEL] = new Integer(habiliter.sante[ACTUEL]);
		sante[MAX] = new Integer(habiliter.sante[MAX]);

		energie[GAIN] = new Integer(habiliter.energie[GAIN]);
		energie[ACTUEL] = new Integer(habiliter.energie[ACTUEL]);
		energie[MAX] = new Integer(habiliter.energie[MAX]);
	}

	public void gererGain(int[] stat) {
		stat[ACTUEL] = stat[ACTUEL] + stat[GAIN];
		if (stat[ACTUEL] < 0) {
			stat[ACTUEL] = 0;
		} else if (stat[ACTUEL] > stat[MAX]) {
			stat[ACTUEL] = stat[MAX];
		}
	}

}
