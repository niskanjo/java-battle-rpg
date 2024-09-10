package org.example;

public class Player extends GameCharacter {
    public Player(String name, int hitPoints) {
        super(name, hitPoints);
        super.dexterity = 0.8; // Default DEX for player
    }

    public Player(String name, int hitPoints, double dexterity) {
        super(name, hitPoints, dexterity);
    }


}
