import java.util.Random;
import java.util.Scanner;

public class dungeonEscape {


	public static void main(String[] args ){
		boolean won = false, lost = false;
		System.out.println("Welcome to Dungeon Escape!\nTo make your hero move, use the WASD keys.\n\nGood luck!\n");
		Scanner scan = new Scanner(System.in);

		

		//boolean finish = levelOne(a);
		boolean finish = true;
		if (finish == true) {
			System.out.println("You escaped! Good work!\n But wait...\n Oh no, there's an ogre on the loose!\nReach the key and escape!\n\n");
			isLevelTwo = true;
		}
		if (!finish){
			System.out.println("You got caught! Try again!");
			return;
		}
		boolean finishLevelTwo = levelTwo(ogreMap);
		if (finishLevelTwo == true) {
			System.out.println("You have finished the game! Thanks for playing!");
		}
		if (!finishLevelTwo){
			System.out.println("You got caught! Try again!");
			return;
		}

	}




	public static void showMap(char[][] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		System.out.println("\n");
	}
}

