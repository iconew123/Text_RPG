package stage;

import java.util.ArrayList;

import text_rpg.GameManager;

public class StageTown extends Stage implements Init {

	private ArrayList<ArrayList<Integer>> townMap;
	private String strTownMap = "";

	@Override
	public void updateStage() {
		if (!this.getIsSet())
			setMap();

		GameManager.getInstace().printSpace();
		System.out.println("================[ TOWN ]================");
		showMap();

		int recordY = GameManager.pY;
		int recordX = GameManager.pX;
		move(inputString("방향 입력 (로비이동 x) : "));
		if (!check(GameManager.pY, GameManager.pX)) {
			GameManager.pY = recordY;
			GameManager.pX = recordX;
		}
	}

	@Override
	public void setMap() {
		// 맵 파싱은 최초 게임로드 후 1회만
		strTownMap += "0,9,0,0,0,9,9,9,0,1\n";
		strTownMap += "0,9,0,4,0,9,9,9,0,9\n";
		strTownMap += "0,9,0,0,0,9,9,9,0,9\n";
		strTownMap += "0,9,0,0,0,0,9,0,0,9\n";
		strTownMap += "0,9,9,0,0,9,9,0,0,9\n";
		strTownMap += "0,0,0,0,0,0,0,0,0,2\n";
		strTownMap += "0,0,0,0,0,9,9,0,0,9\n";
		strTownMap += "0,9,0,0,0,9,9,0,0,9\n";
		strTownMap += "0,9,0,0,0,9,9,0,0,3\n";
		strTownMap += "0,9,9,9,9,9,9,9,9,9\n";
		parsingMap();
		this.setIsSet();
	}

	private void parsingMap() {
		townMap = new ArrayList<ArrayList<Integer>>();
		String[] parseMap = strTownMap.split("\n");

		for (String parse : parseMap) {
			String[] oneLine = parse.split(",");
			ArrayList<Integer> oneSection = new ArrayList<Integer>();
			for (String one : oneLine) {
				int section = Integer.parseInt(one);
				oneSection.add(section);
			}
			townMap.add(oneSection);
		}
	}

	private void showMap() {
		for (int i = 0; i < townMap.size(); i++) {
			for (int j = 0; j < townMap.get(i).size(); j++) {
				if (i == GameManager.pY && j == GameManager.pX) {
					// 플레이어 위치에 플레이어 아이콘 출력
					System.out.print(GameManager.green + "[★] " + GameManager.exit);
				} else {
					Object section = townMap.get(i).get(j);
					if (section.equals(LOAD))
						System.out.print("    ");
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
					else if (section.equals(WALL))
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

	private void showOperating() {
		System.out.println("        [w]     ");
		System.out.println("[a]     [s]     [d]");
	}

	public boolean check(int y, int x) {

		if (y < 0 || y >= townMap.size() || x < 0 || x >= townMap.get(y).size())
			return false;

		if (townMap.get(y).get(x).equals(WALL))
			return false;

		if (townMap.get(y).get(x).equals(GUILD)) {
			String text = "길드로 이동합니다.\n";
			GameManager.getInstace().showText(text, 1000);
			GameManager.nextStage = "GUILD";
		}

		if (townMap.get(y).get(x).equals(POTAL_FOREST)) {
			String text = "던전[숲]으로 이동합니다.\n";
			GameManager.getInstace().showText(text, 1000);
			GameManager.nextStage = "FOREST";
		}

		if (townMap.get(y).get(x).equals(POTAL_CAVE) && GameManager.isOpenCave) {
			String text = "던전[동글]로 이동합니다.\n";
			GameManager.getInstace().showText(text, 1000);
			GameManager.nextStage = "CAVE";
		} else if (townMap.get(y).get(x).equals(POTAL_CAVE) && !GameManager.isOpenCave) {
			String text = GameManager.red + "아직 해당 던전을 이용할 수 있는 자격이 없습니다." + GameManager.exit;
			GameManager.getInstace().showText(text, 1000);
			return false;
		}

		if (townMap.get(y).get(x).equals(POTAL_CASTLE) && GameManager.isOpenCastle) {
			String text = "던전[성채]로 이동합니다.\n";
			GameManager.getInstace().showText(text, 1000);
			GameManager.nextStage = "CASTLE";
		} else if (townMap.get(y).get(x).equals(POTAL_CASTLE) && !GameManager.isOpenCastle) {
			String text = GameManager.red + "아직 해당 던전을 이용할 수 있는 자격이 없습니다." + GameManager.exit;
			GameManager.getInstace().showText(text, 1000);
			return false;
		}

		return true;
	}

}
