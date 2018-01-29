using UnityEngine;
using System.Collections;

public class TurnBasedCombatStateMachine : MonoBehaviour {

	public enum BattleStates {START, PLAYER, PLAYERANIME, ENEMY, ENEMYANIME, LOSE, WIN}

	private BattleStates currentState;

	// Use this for initialization
	void Start () {
		currentState = BattleStates.START;
	}
	
	// Update is called once per frame
	void Update () {
		Debug.Log (currentState);
		switch(currentState) {
		case (BattleStates.START):
			//SETUP BATTLE FUNCTION
			break;

		case (BattleStates.PLAYER):
			break;
		
		case (BattleStates.PLAYERANIME):
			break;

		case (BattleStates.ENEMY):
			break;

		case (BattleStates.ENEMYANIME):
			break;

		case (BattleStates.LOSE):
			break;

		case (BattleStates.WIN):
			break;
		}
	}

	void OnGUI() {
		if (GUILayout.Button ("NEXT STATE")) {
			if (currentState == BattleStates.START) {
				currentState = BattleStates.PLAYER;
			} else if (currentState == BattleStates.PLAYER) {
				currentState = BattleStates.PLAYERANIME;
			} else if (currentState == BattleStates.PLAYERANIME) {
				currentState = BattleStates.ENEMY;
			} else if (currentState == BattleStates.ENEMY) {
				currentState = BattleStates.ENEMYANIME;
			} else if (currentState == BattleStates.ENEMYANIME) {
				currentState = BattleStates.LOSE;
			} else if (currentState == BattleStates.LOSE) {
				currentState = BattleStates.WIN;
			} else if (currentState == BattleStates.WIN) {
				currentState = BattleStates.START;
			} 		
		}
	}

}
