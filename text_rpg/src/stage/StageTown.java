package stage;

import java.util.ArrayList;

import text_rpg.GameManager;

public class StageTown extends Stage implements Init {

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
		String interaction = inputString("방향 입력 (로비이동 x) : ");
		move(interaction);
		if (!check(GameManager.pY, GameManager.pX, interaction)) {
			GameManager.pY = recordY;
			GameManager.pX = recordX;
		}
	}

	@Override
	public void setMap() {
		// 맵 파싱은 해당맵 이동 후 1회만
		if (strTownMap.isEmpty()) {
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
		}
		parsingMap(strTownMap);
		this.setIsSet();
	}

	@Override
	public boolean check(int y, int x, String interaction) {
		ArrayList<ArrayList<Integer>> map = Maps.map.get(GameManager.curStage);

		if (y < 0 || y >= map.size() || x < 0 || x >= map.get(y).size())
			return false;

		if (map.get(y).get(x).equals(WALL))
			return false;

		if (map.get(y).get(x).equals(GUILD) && interaction.equals("q")) {
			String text = "[길드]로 이동합니다.\n";
			GameManager.getInstace().showText(text, 1000);
			GameManager.nextStage = "GUILD";
			GameManager.getInstace().showLoading();
		}

		if (map.get(y).get(x).equals(POTAL_FOREST) && (interaction.equals("q") || interaction.equals("Q"))) {
			String text = "던전[숲]으로 이동합니다.\n";
			GameManager.getInstace().showText(text, 1000);
			GameManager.nextStage = "FOREST";
			GameManager.getInstace().showLoading();
		}

		if (map.get(y).get(x).equals(POTAL_CAVE) && GameManager.isOpenCave
				&& (interaction.equals("q") || interaction.equals("Q"))) {
			String text = "던전[동글]로 이동합니다.\n";
			GameManager.getInstace().showText(text, 1000);
			GameManager.nextStage = "CAVE";
			GameManager.getInstace().showLoading();
		} else if (map.get(y).get(x).equals(POTAL_CAVE) && !GameManager.isOpenCave) {
			String text = GameManager.red + "아직 해당 던전을 이용할 수 있는 자격이 없습니다." + GameManager.exit;
			GameManager.getInstace().showText(text, 1000);
			return false;
		}

		if (map.get(y).get(x).equals(POTAL_CASTLE) && GameManager.isOpenCastle) {
			String text = "던전[성채]로 이동합니다.\n";
			GameManager.getInstace().showText(text, 1000);
			GameManager.nextStage = "CASTLE";
			GameManager.getInstace().showLoading();
		} else if (map.get(y).get(x).equals(POTAL_CASTLE) && !GameManager.isOpenCastle) {
			return false;
		}

		if (!GameManager.nextStage.equals("TOWN")) {
			saveAndLoadMap(GameManager.curStage);
			this.setIsSet();
		}

		return true;
	}

}
