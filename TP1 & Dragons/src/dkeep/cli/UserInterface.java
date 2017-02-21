package dkeep.cli;
import  dkeep.logic.GameLogic;

public class UserInterface {
	public static boolean finish = false;
	public static boolean isLevelTwo = false;

	public static void main(String[] args) {
		boolean won = false, lost = false;
		System.out.println("Welcome to Dungeon Escape!\nTo make your hero move, use the WASD keys.\n\nGood luck!\n");
		while (!finish) {
			if (!isLevelTwo) {
				UserInterface.showMap(Maps.ogreMap);
			} else
				UserInterface.showMap(Maps.a);
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
