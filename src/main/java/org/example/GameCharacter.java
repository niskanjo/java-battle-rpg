package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

abstract class GameCharacter implements Serializable {
    String name;
    int hitPoints;
    Weapon equippedWeapon;
    double dexterity;
    ArrayList<Weapon> inventory;
    int battlesFought;
    int battlesWon;

    public GameCharacter(String name, int hitPoints) {
        Random rnd = new Random();
        this.name = name;
        this.hitPoints = hitPoints;
        this.equippedWeapon = new Weapon("Broadsword", rnd.nextInt(15, 25));
        this.battlesFought = 0;
        this.battlesWon = 0;
        this.inventory = new ArrayList<>();
        inventory.add(equippedWeapon);
    }

    public GameCharacter(String name, int hitPoints, double dexterity) {
        Random rnd = new Random();
        this.name = name;
        this.hitPoints = hitPoints;
        this.dexterity = dexterity;
        this.equippedWeapon = new Weapon("Broadsword", rnd.nextInt(15, 30));
        this.battlesFought = 0;
        this.battlesWon = 0;
        this.inventory = new ArrayList<>();
        inventory.add(equippedWeapon);
    }

    // Attack specified GameCharacter
    public int attack(GameCharacter defender) {
        Random rnd = new Random();
        double minDmg = equippedWeapon.getDamage() * getDexterity();
        double maxDmg = equippedWeapon.getDamage();
        int dmg = (int) Math.round(rnd.nextDouble(minDmg, maxDmg));
        defender.takeDamage(dmg);
        return dmg;
    }

    // Heal character by a random amount of HP
    public int heal() {
        Random rnd = new Random();
        int hp = getHitPoints();
        int healHp = rnd.nextInt(10, 30);

        hp += healHp;
        // Max HP is 100
        if (hp > 100) {
            hp = 100;
        }
        setHitPoints(hp);

        return healHp;
    }

    public void addToInventory(Weapon newWeapon) {
        inventory.add(newWeapon);
    }

    public String getStats() {
        return String.format("-Player Stats-\n%s\nHP: %s / 100\nDEX: %.1f\nEquipped: %s (MAX.DMG %d)\nBattles fought: %d\nBattles won: %d\n---\n",
                getName(),
                getHitPoints(),
                getDexterity(),
                getEquippedWeapon().getName(),
                getEquippedWeapon().getDamage(),
                getBattlesFought(),
                getBattlesWon()
        );
    }

    public void incBattlesFought() {
        int btFought = getBattlesFought();
        btFought++;
        setBattlesFought(btFought);
    }
    public void incBattlesWon() {
        int btWon = getBattlesWon();
        btWon++;
        setBattlesWon(btWon);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void takeDamage(int damage) {
        setHitPoints(getHitPoints() - damage);
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public double getDexterity() {
        return dexterity;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    public ArrayList<Weapon> getInventory() {
        return inventory;
    }

    public int getBattlesFought() {
        return battlesFought;
    }

    public int getBattlesWon() {
        return battlesWon;
    }

    public void setBattlesFought(int battlesFought) {
        this.battlesFought = battlesFought;
    }

    public void setBattlesWon(int battlesWon) {
        this.battlesWon = battlesWon;
    }
}
