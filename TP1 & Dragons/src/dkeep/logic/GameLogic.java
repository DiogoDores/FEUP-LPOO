package dkeep.logic;
package dkeep.cli;
import java.util.Scanner;

public class GameLogic {
	public static boolean levelOne(char[][] a) {
		boolean won = false, lost = false;
		Scanner scan = new Scanner(System.in);

		showMap(a);
		do {
			a = move(a, scan.next().charAt(0));

			if (a[5][0] == 'H' || a[6][0] == 'H')
				won = true;
			if (checkPresence(a)) {  
				lost = true;
				won = false;
			}

		} while (!won && !lost);
		return won;
	}
}
