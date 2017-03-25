package dkeep.cli;
import java.util.Scanner;

import dkeep.logic.*;
import dkeep.test.*;

public class UserInterface {
 

	public static void main(String[] args){
		GameLogic game = new GameLogic();
		System.out.println(" ---------------------------- ");
		System.out.println("| WELCOME TO DUNGEON ESCAPE! |");
		System.out.println(" ---------------------------- \n");
		int level = selectLevel();
		char typeOfGuard = 0;

 
		System.out.println("\n\nType W/A/S/D to move. Activate the lever and escape!\n\n");

		if(level == 1){
			GuardMap guardMap = new GuardMap();
			game.changeCurrentMap(guardMap);
			game.createHero(1, 1);

			typeOfGuard = game.createGuard(1,8);			
		} else if (level == 2){
			game.setLevelTwo(0);
		}

		if(typeOfGuard == 'D'){
			System.out.println("The stench of wine fills the air...\n\n");
		} else if(typeOfGuard == 'S'){
			System.out.println("'Hmm? What was that?'\n\n");
		}

		int playing = 0;
		
		Scanner read = new Scanner(System.in);
		game.currentMap.drawMap(game);
		
		while(playing == 0){
			char key = read.next().charAt(0);
			playing = game.startGame(key);
			game.currentMap.drawMap(game);	
		}

		if(playing == 2){
			System.out.println("\n ------------ ");
			System.out.println("| GAME OVER! |");
			System.out.println(" ------------ \n");
		} else if (playing == -1){
			System.out.println("\n ---------- ");
			System.out.println("| YOU WON! |");
			System.out.println(" ---------- \n");
		}
	}
	
	public static int selectLevel() {
		Scanner read = new Scanner(System.in);
		int level;

		do{
			System.out.print("Select level: ");
			level = read.nextInt();

		} while(level < 1 || level > 2);
		return level;
	}
}