using UnityEngine;
using UnityEngine.SceneManagement;
using System.Collections;


public class EnemyFollowAi : MonoBehaviour {
    public Transform target;
    public Transform character;
    public Transform myTransform;
    public Animator Anime;
    public int MS;
	public float attackTime;
	public float cooldown;
	public int maxHealth = 100;
	public int curHealth = 100;
	public int cont = 0;

	void Awake() {
		myTransform = transform;
	}

    // Use this for initialization
    void Start () {
		attackTime = 0;
		cooldown = 3.0f;
        Anime = GetComponent<Animator>();
		GameObject go = GameObject.FindGameObjectWithTag ("Player");
		CurrentHealth (0); 
		target = go.transform;
    }
	
	// Update is called once per frame
	void Update () {

		if (cont == 3) {
			SceneManager.LoadScene ("WinScene");
		}

		if (curHealth <= 0) {
			Anime.SetBool ("Death", true);
		} else {

			if (attackTime > 0) {
				attackTime -= Time.deltaTime;
				MS = 1;
			} else if (attackTime < 0) {
				attackTime = 0;
				MS = 4;
			} else {
				MS = 4;
			}

			float distance = Vector3.Distance (target.transform.position, transform.position);

			Vector3 dir = (target.transform.position - transform.position).normalized;

			float direction = Vector3.Dot (dir, transform.forward);

			if (distance < 3f && direction > 0.4f) {
				if (attackTime == 0) {
					Anime.SetBool ("Attack", true);
					Attack ();
					attackTime = cooldown;
				} else {
					Anime.SetBool ("Attack", false);
				}
				
			} 
			Anime.SetBool ("Run", false);

			transform.LookAt (target);

			if (Vector3.Distance (target.transform.position, character.transform.position) < 30 && Vector3.Distance (target.transform.position, character.transform.position) > 2) {
				transform.Translate (Vector3.forward * MS * Time.deltaTime);
				Anime.SetBool ("Run", true);
			} else if (Vector3.Distance (target.transform.position, character.transform.position) < 3) {
				Anime.SetBool ("Run", false);
			} else {
				Anime.SetBool ("Run", false);
			}
		}
    }

	private void Attack() {

		float distance = Vector3.Distance (target.transform.position, transform.position);

		Vector3 dir = (target.transform.position - transform.position).normalized;

		float direction = Vector3.Dot (dir, transform.forward);

		if (distance < 3f && direction > 0.4f) {
			KnightController h = (KnightController)target.GetComponent ("KnightController");
			h.CurrentHealth (-10);
		}

	}

	public void CurrentHealth (int adj) {
		curHealth += adj;

		if (curHealth > maxHealth) {
			curHealth = maxHealth;
		}


	}
}
