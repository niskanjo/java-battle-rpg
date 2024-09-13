package org.example;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("-*-Battle Game v3.0-*-");

        String saveFile = "player.sav";
        GameCharacter player;
        boolean saveFileExists;
        try {
            player = (GameCharacter) FileUtils.loadObject(saveFile);
            saveFileExists = true;
        } catch (RuntimeException e) {
            // Execute if save file fails to load
            saveFileExists = false;
            System.out.println("No existing save file found.");
            // Generate new player object
            player = newPlayer();
        }

        // Execute if save file exists
        if (saveFileExists) {
            System.out.println("You have an existing save file.");
            System.out.print(player.getStats());
            /*while (true) {
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("Y")) {
                    // Save file is loaded by default
                    System.out.print("Save file loaded.\n");
                    break;
                } else if (userInput.equalsIgnoreCase("N")) {
                    player = newPlayer();
                    break;
                } else {
                    System.out.println("Invalid input, please enter a valid character (Y/N).");
                }
            }*/
            // Accept only characters Y or N, case-insensitive
            String inputLoadSave = UiUtils.validateInput("(?i)[YN]",
                    "Would you like to continue from your previous session? (Y/N)",
                    "Invalid input, please enter a valid character (Y/N).");

            // Act accordingly to user input
            if (inputLoadSave.equalsIgnoreCase("Y")) {
                // Save file is loaded by default
                System.out.print("Save file loaded.\n");
            } else {
                // Generate new player
                player = newPlayer();
            }
        }

        System.out.printf("Welcome, %s. Let the Battle Begin!\n", player.getName());

        // Main game loop
        while (true) {

            // NPC character
            GameCharacter npc = Npc.spawnNpc();

            GameCharacter attacker = player; // Attacking character
            GameCharacter defender = npc; // Defending character
            GameCharacter temp; // Temporary object for turn swapping



            System.out.printf("A deceitful %s equipped with a %s approaches. You decide to engage.\n",
                    npc.getName(),
                    npc.getEquippedWeapon().getName()
            );

            // Battle loop
            for (int i = 1; player.getHitPoints() > 0 && npc.getHitPoints() > 0; i++) {

                if (i > 1) {
                    // Swap turns
                    temp = defender; // Defender variable is temporarily stored in temp
                    defender = attacker; // Defender is reassigned to attacker of previous turn
                    attacker = temp; // Defender stored in temp is assigned attacker
                }

                if (attacker == player) { // Player's turn
                    boolean cancelAttack = false;
                    int playerInventorySize = player.getInventory().size();

                    if (playerInventorySize > 1) {
                        // Execute if player has more than one (1) item in the inventory
                        System.out.print("-Inventory-\nIndex Name MAX.DMG\n");
                        for (int j = 0; j < playerInventorySize; j++) {
                            Weapon listItem = player.getInventory().get(j);
                            System.out.printf("[%d] %s %d\n",
                                    j + 1,
                                    listItem.getName(),
                                    listItem.getDamage()
                            );
                        }
                        // Check with regex if selected inventory index is in a valid range
                        String validInventoryPattern = String.format("(?i)[1-%d]|Q", playerInventorySize);
                        String userInput = UiUtils.validateInput(validInventoryPattern,
                                "Your turn. Choose a weapon to attack with (enter index number) or flee (q)?",
                                "Invalid input! Please enter a valid character (index number or \"q\").");
                        if (userInput.equalsIgnoreCase("Q")) {
                            cancelAttack = true;
                        } else {
                            // Select weapon from inventory
                            int weaponIndex = Integer.parseInt(userInput);
                            player.setEquippedWeapon(player.getInventory().get(weaponIndex - 1));
                        }

                    } else {
                            /* Prints if there is only one weapon stashed in the inventory...
                        ...meaning that the inventory will NOT be printed as it is redundant
                         */
                        System.out.printf("You are equipped with a %s with a max damage of %d.\n",
                                player.getEquippedWeapon().getName(),
                                player.getEquippedWeapon().getDamage()
                        );

                        System.out.println("Will you attack with this weapon (Enter) or flee (q)?");
                        String userInput = scanner.nextLine();
                        if (userInput.equalsIgnoreCase("Q")) {
                            cancelAttack = true;
                        }
                    }
                    if (cancelAttack) {
                        // Cancel attack and exit battle loop
                        System.out.println(player.getName() + " makes a tactical retreat!");
                        break;
                    }
                }
                int dmg = attacker.attack(defender); // Attacker deals weapon damage to defender, returns damage dealt

                // Print attack message for each turn
                System.out.printf("%s hits %s with a %s for %d damage. %s has %d HP left.\n",
                        attacker.getName(),
                        defender.getName(),
                        attacker.getEquippedWeapon().getName(),
                        dmg,
                        defender.getName(),
                        defender.getHitPoints()
                        );

                // Execute if defender's health drops to 0
                if (defender.getHitPoints() <= 0) {
                    // Announce defeat of defender
                    System.out.println(defender.getName() + " has been defeated.");
                    // Announce victory of attacking character
                    System.out.println(attacker.getName() + " wins!");

                    // Execute if player is the winner of the battle
                    if (attacker == player) {
                        // Increment battles won
                        player.incBattlesWon();
                        // Prompt pickup of weapon
                        String userInput = UiUtils.validateInput("(?i)Y|N",
                                    String.format("Do you wish to add %s (DMG %d) to your inventory? (y/n)", npc.getEquippedWeapon().getName(), npc.getEquippedWeapon().getDamage()),
                                    "Invalid input! Please enter a valid character. (y/n)");

                        if (userInput.equalsIgnoreCase("N")) {
                            // Leave enemy weapon behind
                            System.out.printf("You choose to leave the %s behind.\n", npc.getEquippedWeapon().getName());
                            break;
                        } else if (userInput.equalsIgnoreCase("Y")) {
                            // Add weapon to inventory
                            player.addToInventory(npc.getEquippedWeapon());
                            System.out.printf("%s (DMG %d) added to inventory.\n", npc.getEquippedWeapon().getName(), npc.getEquippedWeapon().getDamage());
                        }
                    }
                }
            }
            /* End of battle loop */

            // Print game over message if player's HP is 0 or below
            if (player.getHitPoints() <= 0) {
                System.out.println("---");
                System.out.println("Game Over!");
                System.out.printf("Total battles fought: %d\nBattles won: %d", player.getBattlesFought(), player.getBattlesWon());
                break;
            }

            // Increment battles fought
            player.incBattlesFought();

            // Heal outside of battle
            int healHp = player.heal();
            System.out.printf("You use a healing item from your pouch.\n%s regains %d HP.\n", player.getName(), healHp);

            // Print player stats
            System.out.println(player.getStats());

            // Prompt continue / quit
            String userInput = UiUtils.validateInput("(?i)Y|N",
                    "Do you wish to continue playing (y/n)?",
                    "Invalid input! Please enter a valid character (y/n).");

            if (userInput.equalsIgnoreCase("N")) {
                // Save progress to file
                FileUtils.saveObject(player, saveFile);
                System.out.println("Farewell traveler, until we meet again!");
                // Exit program
                break;
            }

        }


        // Save file can be loaded on program restart

    }

    // Create new player object if save file does not exist / load is declined by user
    public static Player newPlayer() {
        Scanner scanner = new Scanner(System.in);
        // Welcome message
        System.out.println("Greetings, traveler! What will you name your character?");
        String playerName = scanner.nextLine();
        return new Player(playerName, 100);
    }
}