using UnityEngine;
using UnityEngine.SceneManagement;
using System.Collections;
using System.Collections.Generic;

public class KnightController : MonoBehaviour {

	CharacterController cc;
	public GameObject target;
	public Animator Anime;
	public float attackTime;
	public float cooldown;
	public float deathTime;

	public int maxHealth = 100;
	public int curHealth = 100;

	public float healthBarLength;
    public int MS = 4;

    // Use this for initialization
    void Start()
    {
		deathTime = 3;
		attackTime = 0;
		cooldown = 2.0f;
        Anime = GetComponent<Animator>();
		cc = GetComponent<CharacterController> ();
		healthBarLength = Screen.width / 2;
    }

    // Update is called once per frame
    void Update()
    {
		
		CurrentHealth (0); 

		if (curHealth <= 0) {
			if (deathTime > 0) {
				Anime.SetBool ("Death", true);
				deathTime -= Time.deltaTime;
			} else {
				SceneManager.LoadScene ("LoseScene");
			}

		} else {
			if (attackTime > 0) {
				attackTime -= Time.deltaTime;
				MS = 1;
			} else if (attackTime < 0) {
				attackTime = 0;
				MS = 4;
				Anime.SetBool ("Attack", false);
			} else {
				MS = 4;
				Anime.SetBool ("Attack", false);
			}

			if (Input.GetKeyDown (KeyCode.F)) {
				if (attackTime == 0) {
					Anime.SetBool ("Attack", true);
					Attack ();
					attackTime = cooldown;

				} else { 
					Anime.SetBool ("Attack", false);
				}

			} 





			if (Input.GetKey (KeyCode.W)) {
				Anime.SetBool ("Run", true);
				transform.Translate (Vector3.forward * MS * Time.deltaTime);

			} else {
				Anime.SetBool ("Run", false);
			}


			if (Input.GetKey(KeyCode.S))
			{
				Anime.SetBool("WalkB", true);
				transform.Translate(Vector3.back * MS * Time.deltaTime);

			}
			else
			{
				Anime.SetBool("WalkB", false);
			}

			Anime.SetFloat("Direction", 0);
			Anime.SetBool("Rotation", false);

			if (Input.GetKey(KeyCode.A))
			{
				Anime.SetFloat("Direction", -1);
				Anime.SetBool("Rotation", true);
				transform.Rotate(transform.up, -180 * Time.deltaTime);

			}

			if (Input.GetKey(KeyCode.D))
			{
				Anime.SetFloat("Direction", 1);
				Anime.SetBool("Rotation", true);
				transform.Rotate(transform.up, 180 * Time.deltaTime);
			}



		} 
	}



	void OnGUI() {
		GUI.Box (new Rect (10, 10, healthBarLength, 20), curHealth + "/" + maxHealth);
	}

	public void CurrentHealth (int adj) {
		curHealth += adj;

		if (curHealth < 0) {
			curHealth = 0;
		}

		if (curHealth > maxHealth) {
			curHealth = maxHealth;
		}



		healthBarLength = (Screen.width / 2) * (curHealth / (float)maxHealth);
	}


	private void Attack() {

		float distance = Vector3.Distance (target.transform.position, transform.position);

		Vector3 dir = (target.transform.position - transform.position).normalized;

		float direction = Vector3.Dot (dir, transform.forward);
		GetComponent<AudioSource>().Play ();
		Debug.Log (direction);
		if (distance < 3f && direction > 0.4f) {
			EnemyFollowAi eh = (EnemyFollowAi)target.GetComponent ("EnemyFollowAi");
			eh.CurrentHealth (-25);
		}

	}
}