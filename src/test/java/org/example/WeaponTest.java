package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {
    @Test
    void testInstantiateWeapon() {
        Weapon w = new Weapon("Sword", 20);
        assertEquals("Sword", w.getName());
    }

}