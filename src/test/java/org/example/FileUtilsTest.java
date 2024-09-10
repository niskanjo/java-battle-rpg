package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {
    @Test
    void testSaveLoadFile() {
        GameCharacter foo = new Player("foo", 100);
        String saveFile = "testSave.sav";

        // Save object to file
        FileUtils.saveObject(foo, saveFile);

        // Load object from file
        GameCharacter bar = (GameCharacter) FileUtils.loadObject(saveFile);

        assertEquals("foo", bar.getName());
        // Assert weapon data
        assertEquals(foo.getEquippedWeapon().getName(), bar.getEquippedWeapon().getName());
        assertEquals(foo.getEquippedWeapon().getDamage(), bar.getEquippedWeapon().getDamage());

    }

}