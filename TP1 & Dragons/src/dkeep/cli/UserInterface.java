package dkeep.cli;
import dkeep.logic.*;

public class UserInterface {
	
	public static boolean finish = false;
	public static boolean isLevelTwo = false;
	public static boolean won = false;
	
	public static void main(String[] args) {
		System.out.println("Welcome to Dungeon Escape!\nTo make your hero move, use the WASD keys.\n\nGood luck!\n");
		while (!finish) {
			if (!isLevelTwo) {
				GameLogic.levelOne(Maps.a);
			} 

			else {
				finish = GameLogic.levelTwo(Maps.ogreMap);
			}
		}
		if (won) {
			System.out.print("WOOW!");
		}
		return ;
	}
	
	public static void showMap(char[][] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		System.out.println("\n");
	}
}
