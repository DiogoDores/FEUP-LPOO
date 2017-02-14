
public class dungeonEscape {
	public static void main(String[] args ){
		boolean won = false;
		char[][] a = {{'X','X','X','X','X','X','X','X','X','X'},{'X','H',' ',' ','I',' ','X',' ','G','X'},{'X','X','X',' ','X','X','X',' ',' ','X'},{'X',' ','I',' ','I',' ','X',' ',' ','X'},{'X','X','X',' ','X','X','X',' ',' ','X'},{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},{'X','X','X',' ','X','X','X','X',' ','X'},{'X',' ','I',' ','I',' ','X','K',' ','X'},{'X','X','X','X','X','X','X','X','X','X'}};
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
}
