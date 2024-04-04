package stage;

import java.util.Random;
import java.util.Scanner;

import text_rpg.GameManager;

abstract public class Stage {

	public static int Y_SIZE;
	public static int X_SIZE;

	public final int LOAD = 0;
	public final int POTAL_FOREST = 1;
	public final int POTAL_CAVE = 2;
	public final int POTAL_CASTLE = 3;
	public final int GUILD = 4;
	public final int WALL = 9;

	public Random random = new Random();
	public Scanner scan = new Scanner(System.in);

	private boolean isSet;

	public boolean getIsSet() {
		return this.isSet;
	}

	public void setIsSet() {
		this.isSet = !this.isSet;
	}

	public int inputNumber(String text) {
		int number = -1;
		System.out.print(text);
		try {
			String input = scan.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자만 입력하세요.");
		}

		return number;
	}

	public String inputString(String text) {
		System.out.print(text);
		return scan.next();
	}

	public void move(String direction) {
		if (direction.equals("x") || direction.equals("X"))
			GameManager.nextStage = "LOBBY";

		if (direction.equals("A") || direction.equals("a"))
			GameManager.pX--;
		else if (direction.equals("D") || direction.equals("d"))
			GameManager.pX++;
		else if (direction.equals("W") || direction.equals("w"))
			GameManager.pY--;
		else if (direction.equals("S") || direction.equals("s"))
			GameManager.pY++;

	}

	abstract public void updateStage();
}
