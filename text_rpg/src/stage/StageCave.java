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
		
		if (openBoss() && !isexsist)
			isexsist = true;

		if (isexsist && !show) {
			// 연출은 한번만
			GameManager.getInstace().printSpace();
			showAnimation();
			this.show = !this.show;
		}

		GameManager.getInstace().printSpace();
		String message = "보스 해금 조건 > ";
		message += (GameManager.BatCnt >= 5 ? GameManager.green : GameManager.red)
				+ String.format("박쥐 : [%d/5]   ", GameManager.BatCnt) + GameManager.exit;
		message += (GameManager.OrcCnt >= 5 ? GameManager.green : GameManager.red)
				+ String.format("오크 : [%d/5]   ", GameManager.OrcCnt) + GameManager.exit;
		message += (GameManager.GolemCnt >= 5 ? GameManager.green : GameManager.red)
				+ String.format("골렘 : [%d/5]   ", GameManager.GolemCnt) + GameManager.exit;
		System.out.println(message);
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

		// 맵바뀌면 즉시 종료
		if (GameManager.curStage != GameManager.nextStage)
			return;

		boolean spawn = random.nextInt(3) < 1 ? true : false;
		if (spawn) {
			// 현재 스테이지의 기준에 따라 몹생성
			GameManager.preStage = GameManager.nextStage;
			GameManager.nextStage = "BATTLE";
			System.out.println("[동굴] 몬스터와 전투 시작!");
			GameManager.getInstace().delay(1000);
		}

	}
	
	private boolean openBoss() {

		boolean isOpen = (GameManager.BatCnt >= 5 && GameManager.OrcCnt >= 5 && GameManager.GolemCnt >= 5);

		return isOpen;
	}

	@Override
	public void setMap() {
		if (strCaveMap.isEmpty()) {
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
			strCaveMap = saveAndLoadMap(GameManager.curStage);
		}
		if (isexsist)
			show = true;
		GameManager.pY = 5;
		GameManager.pX = 0;
		parsingMap(strCaveMap);
		this.setIsSet();

	}

	@Override
	public boolean check(int y, int x, String interaction) {
		ArrayList<ArrayList<Integer>> map = Maps.map.get(GameManager.curStage);

		if (y < 0 || y >= map.size() || x < 0 || x >= map.get(y).size())
			return false;

		if (map.get(y).get(x).equals(WALL))
			return false;

		if (map.get(y).get(x).equals(POTAL_TOWN) && (interaction.equals("q") || interaction.equals("Q"))) {
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
		}

		if (map.get(y).get(x).equals(BOSS)) {
			String text = "보스 [킹 케슬 골렘]과 전투를 시작합니다.";
			GameManager.getInstace().showText(text, 1000);
			GameManager.preStage = GameManager.curStage + "보스";
			GameManager.nextStage = "BATTLE";
		}

		return true;
	}

	private void showAnimation() {

		ArrayList<ArrayList<Integer>> map = Maps.map.get(GameManager.curStage);
		// 보스연출
		for (int i = 1; i < 4; i++) {
			ArrayList<Integer> changeMap = map.get(bY);
			changeMap.set(bX - i, LOAD);
			map.set(bY, changeMap);
			System.out.println("================[ CAVE ]================");
			showMap();
			GameManager.getInstace().delay(1000);
			GameManager.getInstace().printSpace();
		}
	}

}
