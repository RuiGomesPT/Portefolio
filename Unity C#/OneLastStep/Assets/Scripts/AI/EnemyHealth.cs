using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyHealth : MonoBehaviour {

	public int maxHealth = 100;
	public int curHealth = 100;


	// Use this for initialization
	void Start () {
		
	}

	// Update is called once per frame
	void Update () {
		CurrentHealth (0); 
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


	}
}
