﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AllyHealth : MonoBehaviour {
	public int maxHealth = 100;
	public int curHealth = 100;

	public float healthBarLength;

	// Use this for initialization
	void Start () {
		healthBarLength = Screen.width / 2;
	}
	
	// Update is called once per frame
	void Update () {
		CurrentHealth (0); 
	}

	void OnGUI() {
		GUI.Box (new Rect (10, 40, healthBarLength, 20), curHealth + "/" + maxHealth);
	}

	public void CurrentHealth (int adj) {
		curHealth += adj;

		if (curHealth < 0) {
			curHealth = 0;
		}

		if (curHealth > maxHealth) {
			curHealth = maxHealth;
		}

		if (curHealth < 1) {
			curHealth = 1;
		}

		healthBarLength = (Screen.width / 2) * (curHealth / (float)maxHealth);
	}
}
