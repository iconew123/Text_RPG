package stage;

import java.util.Random;
import java.util.Scanner;

abstract public class Stage {

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

	abstract public void updateStage();
}
