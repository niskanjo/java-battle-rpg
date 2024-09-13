# Java Battle RPG
## A text-based role-playing game running on Java

<p>Java Battle RPG imitates turn-based battle systems typically found in RPGs. The player will face enemies one by one endlessly until they lose (HP reaches 0) or they choose to quit playing.</p>
<br>

## Background
This was originally a school project in which the objective was to implement basic object-oriented programming in Java. The project has since been improved with new features, such as the `UiUtils` class to make input validation more concise.

## Gameplay
### Startup
On startup, the program will check for an existing save file `player.sav` and then prompt the user to load it if it exists. If the save file does not exist, the user will immediately be asked to enter a name for their player character.
<br><br>
A new `Player` object will be created, containing the player's name, health, hardcoded dexterity stat, weapon inventory and equipped weapon. The player will be equipped by default with a `Broadsword` with a random damage stat between two hardcoded integer values (in class `Gamecharacter`).

### Battle
The player will automatically encounter an enemy `Npc` object, which initiates a battle. The player will be prompted to flee or attack the enemy with a weapon of their choice (if there are more than 1 weapon in the inventory). If the player chooses to attack, they will deal damage to the enemy according to the following formula presented in pseudocode: `Random.range((Weapon.minDmg * Player.DEX), Weapon.maxDmg)`
<br><br>
The enemy will respond with an attack of its own, dealing damage to the player and completing the round. After this, it will be the player's turn again.
<br><br>
The battle will continue until the player chooses to flee or the HP of either the player or the enemy reaches 0.
### After the battle
#### Player loses
If the player's HP reaches 0 first, the battle will end and result in a Game Over, terminating the program. If the player has properly quit the game before without losing, there may be a save file that can be loaded. Otherwise a new game will have to be started upon running the program again.

#### Player wins
If the enemy's HP reaches 0 first, the battle will end with the player's victory. Some of the player's HP will automatically be restored. The player will be prompted to pick up the defeated enemy's weapon. If accepted, the weapon will be added to the player's inventory. The new weapon can be used in later battles.

#### Player flees
If the player chooses to flee, the battle will end and some health will be restored as in the case of the player's victory. This is a tactic that may be used to avoid a Game Over when the player's HP is low.

## Quitting the game
After surviving a battle, the player will be prompted to continue playing or quit the game. If the player chooses to quit, the `Player` object will be saved into a `.sav` file which can be loaded on startup.

## Afterword
The focus of this project is not game design but rather a Java OOP demo. The functionality may be expanded upon later if the developer has time and motivation.