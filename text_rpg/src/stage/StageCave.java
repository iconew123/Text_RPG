package stage;

import java.util.ArrayList;

import text_rpg.GameManager;

public class StageCave extends Stage implements Init {

	private String strCaveMap = "";
	public static boolean isexsist;
	private boolean show;
	private int bY = 5;
	private int bX = 9;

	@Override
	public void updateStage() {
		if (!this.getIsSet())
			setMap();

		if (isexsist && !show) {
			// 연출은 한번만
			GameManager.getInstace().printSpace();
			showAnimation();
			this.show = !this.show;
		}

		GameManager.getInstace().printSpace();
		System.out.println("================[ CAVE ]================");
		showMap();

		int recordY = GameManager.pY;
		int recordX = GameManager.pX;

		String interaction = inputString("방향 입력 (로비이동 x) : ");
		move(interaction);
		if (!check(GameManager.pY, GameManager.pX, interaction)) {
			GameManager.pY = recordY;
			GameManager.pX = recordX;
		}

		boolean spawn = random.nextInt(3) < 1 ? true : false;
		if (spawn) {
			// 현재 스테이지의 기준에 따라 몹생성
			GameManager.preStage = GameManager.nextStage;
			GameManager.nextStage = "BATTLE";
		}

	}

	@Override
	public void setMap() {
		if (!isexsist) {
			strCaveMap += "9,9,9,9,9,9,9,9,9,9\n";
			strCaveMap += "9,0,0,9,0,0,0,0,0,9\n";
			strCaveMap += "9,0,9,9,0,0,0,9,0,9\n";
			strCaveMap += "9,0,9,9,0,0,9,9,9,9\n";
			strCaveMap += "9,0,9,9,0,0,9,9,9,9\n";
			strCaveMap += "-1,0,0,0,0,0,9,9,9,8\n";
			strCaveMap += "9,0,0,0,0,0,9,9,9,9\n";
			strCaveMap += "9,0,0,0,9,0,9,0,9,9\n";
			strCaveMap += "9,0,0,9,9,0,0,0,0,9\n";
			strCaveMap += "9,9,9,9,9,9,9,9,9,9\n";
		} else {
			strCaveMap += "9,9,9,9,9,9,9,9,9,9\n";
			strCaveMap += "9,0,0,9,0,0,0,0,0,9\n";
			strCaveMap += "9,0,9,9,0,0,0,9,0,9\n";
			strCaveMap += "9,0,9,9,0,0,9,9,9,9\n";
			strCaveMap += "9,0,9,9,0,0,9,9,9,9\n";
			strCaveMap += "-1,0,0,0,0,0,0,0,0,8\n";
			strCaveMap += "9,0,0,0,0,0,9,9,9,9\n";
			strCaveMap += "9,0,0,0,9,0,9,0,9,9\n";
			strCaveMap += "9,0,0,9,9,0,0,0,0,9\n";
			strCaveMap += "9,9,9,9,9,9,9,9,9,9\n";
			show = true;
		}
		GameManager.pY = 5;
		GameManager.pX = 0;
		parsingMap(strCaveMap);
		this.setIsSet();

	}

	@Override
	public boolean check(int y, int x, String interaction) {

		if (y < 0 || y >= Map.map.size() || x < 0 || x >= Map.map.get(y).size())
			return false;

		if (Map.map.get(y).get(x).equals(WALL))
			return false;

		if (Map.map.get(y).get(x).equals(POTAL_TOWN) && interaction.equals("q")) {
			String text = "[마을]로 이동합니다.\n";
			GameManager.getInstace().showText(text, 1000);
			GameManager.getInstace().showLoading();
			GameManager.nextStage = "TOWN";
		}

		if (!GameManager.nextStage.equals("CAVE")) {
			// 마을의 첫번째 포탈 위치로 이동
			GameManager.pY = 5;
			GameManager.pX = 9;
			this.setIsSet();
			strCaveMap = "";
		}

		// 보스전투 구현

		return true;
	}

	private void showAnimation() {
		// 보스연출
		for (int i = 1; i < 4; i++) {
			ArrayList<Integer> changeMap = Map.map.get(bY);
			changeMap.set(bX - i, LOAD);
			Map.map.set(bY, changeMap);
			System.out.println("================[ CAVE ]================");
			showMap();
			GameManager.getInstace().delay(1000);
			GameManager.getInstace().printSpace();
		}
	}

}
