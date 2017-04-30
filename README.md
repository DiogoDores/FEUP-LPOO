# LPOO1617_T2G5

GUI Functionalities
    In the active game screen, the user interface will be simple. The gameplay will take place in a centre square, and the information to the user will be present will be displayed in two black borders. 
    On the left one, the top left corner shows the stored weapon (the player can choose when to trigger it), the current amount of lives and the coins he has gathered. 
On the bottom left, the icons regarding the permanent upgrades the player has won will be shown.
    The opposite side displays the highscore, as well as a progress bar on the current map. 
    Since this is only a Desktop game, the GUI serves no purpose other than displaying information to its viewer.

----------------------------------------------------------------------------------------------------------------------------------------

Expected Design Patterns
	Due to the small scope in complexity of our project, many patterns are either overly complicated, or plainly impractical in this context. However, there are a few exceptions, where the implementation of such design patterns could greatly increase the effectiveness of the code.
    STRATEGY: There are going to be multiple enemy types in this game, each with their unique behaviour. On top of that, some power ups the hero may collect can alter the AI behaviour, which makes the use of this pattern easy to understand. 
	Regarding the implementation, a public interface will be set containing the basic behaviour to be overwritten by each enemy class. There, the specifics of the move(), attack() (if the type of enemy actively attempts to damage the hero) are created. This way we can implement new behavioural methods without replacing nor damaging existing code. 
	DECORATOR: One of the core gameplay elements are the power ups. Randomly popping up in reachable areas, these grant either bonuses to the hero’s statistics, or harm all or random enemies on the game area. What makes this pattern so valuable in this context is the possibility of the hero collecting multiple power ups, each making him more powerful. Instead of building a complex state machine to think of possible combinations, we simply use a decorator class.
	This new interface will be implemented by the power ups, overriding some of the hero’s characteristics, e.g. gaining an extra life, or become invincible for a short period of time, essentially making the hero’s die() function irrelevant.
	
----------------------------------------------------------------------------------------------------------------------------------------

Expected Test Cases

There are two key components in our gameplay that need to be tested. Movement and Actions, so we will separate them for easier reading and understanding. These only are a part of our Model And Controller packages.
Movement:
Player – Detecting and evaluating position according to Input. 
Enemy – Testing behavioural aspects of multiple enemy types, as well as taking into consideration the hero’s position.
Collisions – Evaluating collision detection and behaviour reaction when bumping into barriers, either by the hero or enemies.
Specific Power Ups and Upgrades – Some power ups change the hero’s movement stats, and therefore these must be properly tested. 
Actions:
Shooting – Testing projectile physics, as well as power ups to the main gun.
Collision Detection – Bullets collide with enemies, therefore we should check if not only the collision is happening correctly, but also if the proper game objects are being disposed. 
Getting Power Up and Upgrades – Different power ups grant different buffs. This is meant to test if the stats are being correctly modified. 
Level Change – Tests if the level change status is reached, and assesses the new Map.
Spawning – The enemies will appear from four different spawning positions, located in the middle of each side. However, for fairness, there is a maximum number of enemies that can be on the screen at one time, and this number will increase as the levels become more difficult. The enemies will the spawn from random places, meaning that the player will need to adapt their strategy to win.

----------------------------------------------------------------------------------------------------------------------------------------

UML
	Full UML
	![18216117_1110184042419835_1378945937_o](https://cloud.githubusercontent.com/assets/23073989/25567890/3e82b6dc-2def-11e7-8c41-9bb7ed7bc8ab.jpg)
	Dynamic UML Picture
	![18235919_1110016879103218_726938871_o](https://cloud.githubusercontent.com/assets/23073989/25567817/04258934-2dee-11e7-844b-d78ec0b21a01.png)
	
	


----------------------------------------------------------------------------------------------------------------------------------------

Mock Ups
	The following screenshots are the files generated using Balsamiq. All the information regarding the visual aspect are present in the pictures themselves. It is interactable, so if you wish, you can open the Mock Up project in this branch with the software to test the gameflow.
	Main Menu 
	![1 - main menu](https://cloud.githubusercontent.com/assets/23073989/25567715/d1c889ca-2deb-11e7-8510-f09ec3943856.png)
	Game State
	![2 - game state](https://cloud.githubusercontent.com/assets/23073989/25567714/d1c55fa2-2deb-11e7-8db5-c0ba83e32004.png)
	Lose State
	![3 - lose screen](https://cloud.githubusercontent.com/assets/23073989/25567713/d1c44c84-2deb-11e7-9dee-d22455d35419.png)
