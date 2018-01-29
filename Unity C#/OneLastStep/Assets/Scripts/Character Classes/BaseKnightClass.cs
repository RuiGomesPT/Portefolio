using UnityEngine;
using System.Collections;

public class BaseKnightClass : BaseCharacterClass
{

    // Use this for initialization
    public BaseKnightClass()
    {
        CharacterClassName = "Knight";
        CharacterClassDescription = "Legio description";

        Stamina = 22;
        Endurance = 18;
        Strength = 24;
        Intellect = 16;
        Agility = 20;
    }
}
