package stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import text_rpg.GameManager;

abstract public class Stage {

	public final int LOAD = 0;
	public final int POTAL_TOWN = -1;
	public final int POTAL_FOREST = 1;
	public final int POTAL_CAVE = 2;
	public final int POTAL_CASTLE = 3;
	public final int GUILD = 4;
	public final int BOSS = 8;
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

	public void parsingMap(String map) {
		Map.map = new ArrayList<ArrayList<Integer>>();
		String[] parseMap = map.split("\n");

		for (String parse : parseMap) {
			String[] oneLine = parse.split(",");
			ArrayList<Integer> oneSection = new ArrayList<Integer>();
			for (String one : oneLine) {
				int section = Integer.parseInt(one);
				oneSection.add(section);
			}
			Map.map.add(oneSection);
		}
	}

	public void showMap() {
		for (int i = 0; i < Map.map.size(); i++) {
			for (int j = 0; j < Map.map.get(i).size(); j++) {
				if (i == GameManager.pY && j == GameManager.pX) {
					// 플레이어 위치에 플레이어 아이콘 출력
					System.out.print(GameManager.green + "[★] " + GameManager.exit);
				} else {
					Object section = Map.map.get(i).get(j);
					if (section.equals(LOAD))
						System.out.print("    ");
					else if (section.equals(POTAL_TOWN))
						System.out.print(GameManager.blue + "[℗] " + GameManager.exit);
					else if (section.equals(POTAL_FOREST))
						System.out.print(GameManager.blue + "[℗] " + GameManager.exit);
					else if (section.equals(POTAL_CAVE)) {
						// 포탈 입장 가능 색 입히기
						String openMapColor = GameManager.isOpenCave ? GameManager.blue : GameManager.red;
						System.out.print(openMapColor + "[℗] " + GameManager.exit);
					} else if (section.equals(POTAL_CASTLE)) {
						// 포탈 입장 가능 색 입히기
						String openMapColor = GameManager.isOpenCastle ? GameManager.blue : GameManager.red;
						System.out.print(openMapColor + "[℗] " + GameManager.exit);
					} else if (section.equals(GUILD))
						System.out.print(GameManager.purple + "[♣] " + GameManager.exit);
					else if (section.equals(BOSS)) {
						if (GameManager.curStage.equals("FOREST")) {
							showForestSection();
						} else if (GameManager.curStage.equals("CAVE")) {
							showCaveSection();
						}
					} else if (section.equals(WALL))
						System.out.print(GameManager.black + "■■■ " + GameManager.exit);
					else if (section.equals(LOAD))
						System.out.print(GameManager.green + "[★] " + GameManager.exit);
				}
			}
			System.out.println();
		}
		System.out.println("========================================");
		showOperating();
		System.out.println("========================================");
	}

	private void showForestSection() {
		if (!StageForest.isexsist)
			System.out.print(GameManager.black + "■■■ " + GameManager.exit);
		else
			System.out.print(GameManager.red + "[▣] " + GameManager.exit);
	}

	private void showCaveSection() {
		if (!StageCave.isexsist)
			System.out.print(GameManager.black + "■■■ " + GameManager.exit);
		else
			System.out.print(GameManager.red + "[▣] " + GameManager.exit);
	}

	private void showOperating() {
		Object section = Map.map.get(GameManager.pY).get(GameManager.pX);
		if (section.equals(POTAL_FOREST) || section.equals(POTAL_CAVE) || section.equals(POTAL_CASTLE)
				|| section.equals(GUILD) || section.equals(POTAL_TOWN))
			System.out.println("        [w]     [q]");
		else
			System.out.println("        [w]     ");
		System.out.println("[a]     [s]     [d]");
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
