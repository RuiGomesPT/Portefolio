using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Target : MonoBehaviour {
	public List<Transform> targets;
	public Transform selectedTarget;

	private Transform myT;

	// Use this for initialization
	void Start () {
		targets = new List<Transform>();
		selectedTarget = null;
		myT = transform;

		AddAllEnemies ();
	}

	public void AddAllEnemies() {

		GameObject[] go = GameObject.FindGameObjectsWithTag ("Enemy");

		foreach (GameObject enemy in go) {
			AddTarget (enemy.transform);
		}
	}

	public void AddTarget(Transform enemy) {
		targets.Add (enemy);
	}

	private void SortTargets() {
		targets.Sort (delegate(Transform t1, Transform t2) { 
			return Vector3.Distance (t1.position, myT.position).CompareTo (Vector3.Distance (t2.position, myT.position)); 
		});
	}

	private void TargetEnemy() {
		if (selectedTarget == null) {
			SortTargets ();
			selectedTarget = targets [0];

		} else {
			int index = targets.IndexOf (selectedTarget);

			if(index < targets.Count - 1) {
				index++;
			} else {
				index = 0;
			}
			selectedTarget = targets [index];
			SelectTarget ();
		}
	}
		
	private void SelectTarget() {
		KnightController Attack = (KnightController)GetComponent ("KnightController");
		Attack.target = selectedTarget.gameObject;
	}

	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown (KeyCode.Tab)) {
			TargetEnemy ();
		}
	}
}
