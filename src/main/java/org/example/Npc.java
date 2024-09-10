package org.example;

import java.util.Random;

public class Npc extends GameCharacter {
    Random rnd = new Random();
    public Npc(String name, int hitPoints) {
        super(name, hitPoints);
        super.dexterity = 0.5; // Default DEX for NPC
        // NPC-specific default weapon with random damage
        super.equippedWeapon = new Weapon("Dagger", rnd.nextInt(15, 25));
    }

    public Npc(String name, int hitPoints, double dexterity) {
        super(name, hitPoints, dexterity);
        // NPC-specific default weapon with random damage
        super.equippedWeapon = new Weapon("Dagger", rnd.nextInt(15, 25));
    }

    // Spawn NPC with random stats
    static GameCharacter spawnNpc() {
        Random rnd = new Random();

        String[] npcNames = {"Imp", "Goblin", "Orc", "Skeleton", "Zombie", "Demon"};
        String[] npcWeapons = {"Club", "Dagger", "Scythe", "Bat", "Mace", "Axe"};

        String randomName = npcNames[rnd.nextInt(0, npcNames.length - 1)];
        String randomWeapon = npcWeapons[rnd.nextInt(0, npcWeapons.length - 1)];

        GameCharacter newNpc = new Npc(randomName, rnd.nextInt(40, 80));
        Weapon newWeapon = new Weapon(randomWeapon, rnd.nextInt(15, 30));
        newNpc.setEquippedWeapon(newWeapon);

        return newNpc;
    }

}
