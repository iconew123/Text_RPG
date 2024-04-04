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
		int sel = inputNumber(">>");
	}

	@Override
	public void setMap() {
		// 맵 파싱은 최초 게임로드 후 1회만
		strTownMap += "0,9,0,0,0,9,9,0,0,1\n";
		strTownMap += "0,9,0,4,0,9,9,0,0,9\n";
		strTownMap += "0,9,0,0,0,9,9,0,0,9\n";
		strTownMap += "0,9,0,0,0,0,9,0,0,9\n";
		strTownMap += "0,9,9,0,0,9,9,0,0,9\n";
		strTownMap += "0,0,0,0,0,0,0,0,0,2\n";
		strTownMap += "0,0,0,0,0,9,0,0,0,9\n";
		strTownMap += "0,9,0,0,0,9,0,0,0,9\n";
		strTownMap += "0,9,0,0,0,9,0,0,0,9\n";
		strTownMap += "0,9,9,9,9,9,9,9,9,3\n";
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
		for (ArrayList tmp : townMap) {
			for (Object section : tmp) {
				if (section.equals(1))
					System.out.print(GameManager.blue + "[℗] " + GameManager.exit);
				else if (section.equals(2)) {
					// 포탈 입장 가능 색 입히기
					String openMapColor = GameManager.isOpenCave ? GameManager.blue : GameManager.red;
					System.out.print(openMapColor + "[℗] " + GameManager.exit);
				} else if (section.equals(3)) {
					// 포탈 입장 가능 색 입히기
					String openMapColor = GameManager.isOpenCastle ? GameManager.blue : GameManager.red;
					System.out.print(openMapColor + "[℗] " + GameManager.exit);
				} else if (section.equals(4))
					System.out.print(GameManager.purple + "[♣] " + GameManager.exit);
				else if (section.equals(9))
					System.out.print(GameManager.black + "■■■ " + GameManager.exit);
				else if (section.equals(8))
					System.out.print(GameManager.green + "[★] " + GameManager.exit);
				else
					System.out.print("    ");
			}
			System.out.println();
		}
		System.out.println("========================================");
	}

}
