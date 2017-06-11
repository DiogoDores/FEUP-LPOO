# LPOO1617_T2G5

# **Setup/Installation**

### Installing the game

In order to install the game, all you have to do is enter the folder _**Prairie King**_ and download the _**Journey of the Prairie King.jar**_ file to your computer. From that, you just need to run it and that's it.

### Installing the development environment

This game was developed using Android Studio, so the installing procedure will be relative to this IDE.

# **Class Diagram**
Here we display the class diagram of our project just for a better understanding of how it's organized. If you wish to see it in more detail, just enter the folder _**Prairie King**_ and download the _**GameUML.xmi**_ file to your computer and open it with Umbrello or other UML editor compatible with .xmi files.![GameUML]({{site.baseurl}}/http://imgur.com/a/eVNK9)

# **Design Patterns**

Due to the small scope in complexity of our project, many patterns were either overly complicated, or plainly impractical in this context. However, there were a few exceptions, where the implementation of such design patterns could greatly increase the modularity of the code.
	
### STRATEGY:
There are multiple types of enemies in the game, each with their unique behaviour. 

A behaviour Interface was implemented which served as a basis for all the different attack strategies. Each different has its own behaviour, following a specific set of rules, always taking into consideration the position of the hero.

This Design Pattern is fully, and correctly implemented in our game. 

### DECORATOR: 

Decorator was going to be used for the powerups the Hero might catch in random game areas. However, we could not correctly implement this, opting instead for a different approach, where each powerup’s ability would be stored by their respective owner (the Hero, or it’s Gun). 

This Design Pattern was not implemented as originally planned.
	
### POOLING:

We ended up using this Design Pattern, despite having originally never stated it. Since the hero has a gun which fires projectiles, it made sense to use Pooling to store “destroyed” bullets, and fire them again if necessary. Since they are essentially the same, only varying in velocity and position, it was relatively easy to use.

This Design Pattern is fully, and correctly implemented in our game.

### RELEVANT DESIGN DECISIONS:

One of our goals was to include different maps, advancing as the player reached a certain enemy kill count. Unfortunately, there was no time to implement this feature, and so the gameplay area is locked to the first zone. Another mechanic that needed to be removed was the coins. In our early visions, enemies could randomly drop coins that could be used to permanently upgrade the weapon.

Since there needs to be a feedback about the player’s current statistics, a HUD interface was built, storing the main information, this being the current active powerup, and the amount of player lives left.

One main feature of the game was, surprisingly, not foreseen. We made an ending to the game, which can be triggered after 400 enemy kills. This is no easy feat, but it can be achieved! For the sake of avoiding spoilers, no end game screenshots are in this READ.ME.

Since this is a semi-endless shooter, there is an algorithm for the increase of difficulty, that responds to the amount of enemies kills. The higher this number is, the harder the game will become. This is made possible by increasing the maximum amount of enemies in the screen at any given time, and spawning tougher enemies.

# **MAJOR DIFFICULTIES**

The hardest thing to do was adapt to the new framework. Neither of us knew how to approach LibGDX, and most, if not all, the struggles of this project inherit from this initial problem. One of the hardest to overcome was the impact different measurement units had on the game. It proved quite troublesome, after many failed attempts and wrong camera and viewports adjustments.

Regarding game logic, one of the toughest area of the game was the overall design of the powerups. We were having some difficulties with the implementation of the Decorator because when we started development, we had not in mind the importance of powerups in the class structure. This eventually lead to one of the hardest workarounds.

# **LESSONS LEARNED**

After the beginning of the project, it became clear of just how important it is to plan and have deadlines. These two can help a lot during mid-development hell, where you get stuck, and end up losing enormous amounts of time for something that will not have that big of an impact in the end game. It makes the whole development more structured, and more reliable. 

To have this big of a project finished was also really important to understand more advanced coding techniques, the relevance of Design Patterns, and LibGDX was a fun and liberating framework after you get the past the initial barriers.

# **OVERALL DEVELOPMENT TIME**

Pedro Reis –> 95 - 105 hours.

Diogo Dores –> 90 - 95 hours.

As for the work distribution between us, Pedro Reis did the overall logic (Artificial Intelligence, Hero's Movement, etc.). Diogo Dores handled the visual part of the game and the unitary tests. Percentually, 55% of the project was taken care of by Pedro Reis and the remaining 45% by Diogo Dores. 


# **EXTRA**

### Credits
[Main Menu](http://mockingjay1701.deviantart.com/art/Pixel-art-landscape-525082296) (used with artist's permission) – Made by: [MockingJay1701](http://mockingjay1701.deviantart.com/)

[Defeat Menu](http://corykeks.deviantart.com/art/Pixel-Art-Night-Forest-513462484) (used with artist's permission) – Made by: [CoryKeks](http://corykeks.deviantart.com/)

# **User Manual**

After installing the game you'll be presented with the game's Main Menu. ![Main Menu]({{site.baseurl}}/https://github.com/DiogoDores/LPOO1617_T2G5/blob/finalRelease/Prairie%20King/Screenshots%20LPOO/MainMenu.png)

From there, you can press Spacebar to begin the game. Immediately after, there will be a small How To Play pop up in the center of your screen. ![How To Play]({{site.baseurl}}/https://github.com/DiogoDores/LPOO1617_T2G5/blob/finalRelease/Prairie%20King/Screenshots%20LPOO/HowToPlay.png)

If you lose all your lives you'll be directed to a lose screen displaying you highscore. From there you can either press Esc to exit the game or Spacebar to play it again.![Lose Screen]({{site.baseurl}}/https://github.com/DiogoDores/LPOO1617_T2G5/blob/finalRelease/Prairie%20King/Screenshots%20LPOO/LoseScreen.png)


