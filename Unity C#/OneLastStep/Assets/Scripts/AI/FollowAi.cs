using UnityEngine;
using System.Collections;

public class FollowAi : MonoBehaviour {
    public Transform target;
    public Transform character;
    public Transform myTransform;

    public Animator Anime;

    public int MS = 4;

    void Start()
    {
        Anime = GetComponent<Animator>();
    }
    
    // Update is called once per frame
    void Update () {
        Anime.SetBool("Run", false);
        transform.LookAt(target);

        if (Vector3.Distance(target.transform.position, character.transform.position) > 1)
        {
            transform.Translate(Vector3.forward * MS * Time.deltaTime);
            Anime.SetBool("Run", true);
        }  else
        {
            Anime.SetBool("Run", false);
        }
        
	}
}
