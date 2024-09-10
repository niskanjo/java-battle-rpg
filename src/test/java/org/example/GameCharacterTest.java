package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTest {

    @Test
    void testInstantiateGameCharacterTwoArgs() {
        GameCharacter g = new Player("foo", 100);
        assertEquals("foo", g.getName());
        // Assert DEX equals default 0.8
        assertEquals(0.8, g.getDexterity());
    }
    @Test
    void testInstantiateGameCharacterThreeArgs() {
        GameCharacter g = new Player("foo", 100, 0.8);
        assertEquals("foo", g.getName());
    }

    @Test
    void testTakeDamage() {
        GameCharacter g = new Player("foo", 100, 0.8);
        g.takeDamage(30);
        assertEquals(70, g.getHitPoints());
    }

    @Test
    void testInstantiatePlayer() {
        Player p = new Player("Player", 100);
        assertEquals("Player", p.getName());
    }

    @Test
    void testInstantiateNpc() {
        Npc n = new Npc("NPC", 50);
        assertEquals("NPC", n.getName());
    }

    @Test
    void testAttackNpc() {
        GameCharacter player = new Player("Player", 100);
        GameCharacter npc = new Npc("NPC", 50);

        player.attack(npc);
        assertEquals(100, player.getHitPoints());
        assertNotEquals(50, npc.getHitPoints());
    }

    @Test
    void testAttackPlayer() {
        GameCharacter player = new Player("Player", 100);
        GameCharacter npc = new Npc("NPC", 50);

        npc.attack(player);
        assertEquals(50, npc.getHitPoints());
        assertNotEquals(100, npc.getHitPoints());
    }

    @Test
    void testSpawnNpc() {
        GameCharacter npc = Npc.spawnNpc();

        assertNotNull(npc);
        // Assert name matches array of possible names
        // Regex help by ChatGPT 3.5
        assertTrue(npc.getName().matches("Imp|Goblin|Orc|Skeleton|Zombie|Demon"));
        // Assert hitPoints is matches given range
        assertTrue(npc.getHitPoints() >= 40 && npc.getHitPoints() <= 80);

        assertNotNull(npc.getEquippedWeapon());
        // Assert equipped weapon matches possible names
        assertTrue(npc.getEquippedWeapon().getName().matches("Club|Dagger|Scythe|Bat|Mace|Axe"));
        // Assert equipped weapon damage matches given range
        assertTrue(npc.getEquippedWeapon().getDamage() >= 15 && npc.getEquippedWeapon().getDamage() <= 30);


    }

}