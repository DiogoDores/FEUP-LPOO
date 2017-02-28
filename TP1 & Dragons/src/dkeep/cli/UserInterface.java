package dkeep.cli;
import dkeep.logic.*;

public class UserInterface {

	public static boolean finish = false;
	public  static boolean isLevelTwo = false;
	
	public static void main(String[] args) {
		boolean won = false;	
		System.out.println("Welcome to Dungeon Escape!\nTo make your hero move, use the WASD keys.\n\nGood luck!\n");
		
		 
		while (!finish) {
			if (!isLevelTwo) {
				won = GameLogic.levelOne();
				if (won) {
					System.out.println("Congratulations! You escaped!... To another level. \nTo finally reach the exit, you need to grab the key\nand head for the door! \n\nBetter watch out!\n");
					isLevelTwo = true;
				}
				
				else {
					System.out.println("You've been caught! Better luck next time!\n");
					finish = true;
				}
			} 

			else {
				won = false;
				won = GameLogic.levelTwo();
				if (won)
					System.out.println("WOW! That's moderately impressive!\n");
				else
					System.out.println("Darn it. So close!\n");
				finish = true;
			}
		}
		System.out.println("\nThank you for playing Dungeon Escape!\n");
		return;
	}
	 
	public static void showMap(char[][] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		System.out.println("\n");
	}
}
