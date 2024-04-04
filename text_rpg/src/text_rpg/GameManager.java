package text_rpg;

import java.util.HashMap;
import java.util.Map;

public class GameManager {

	private Map<String, Stage> stageList = new HashMap<String, Stage>();
	private String curStage;
	public static String nextStage;
	private static boolean isEnd;

	public boolean isRun() {
		return nextStage.equals("END") || isEnd ? false : true;
	}

	private GameManager() {
		setStages();
		curStage = "";
		nextStage = "TITLE";
	}

	private static GameManager instance = new GameManager();

	public static GameManager getInstace() {
		return instance;
	}

	private void setStages() {
		stageList.put("TITLE", new StageTitle());
		stageList.put("LOBBY", new StageLobby());
	}

	// 공백 출력
	public void printSpace() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	// 로딩 효과 출력
	public void showLoading() {
		String text = "로딩중...";
		printSpace();
		for (int i = 0; i < text.length(); i++) {
			System.out.print(text.charAt(i));
			delay(500);
		}
	}

	// 딜레이 효과
	public void delay(int mills) {
		try {
			Thread.sleep(mills);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 텍스트 출력효과
	public void showText(String text, int delay) {
		String[] show = text.split("\n");

		for (int i = 0; i < show.length; i++) {
			System.out.println(show[i]);
			delay(delay);
		}
	}

	private void changeStage() {
		curStage = nextStage;
		Stage stage = stageList.get(curStage);
		stage.updateStage();
	}

	public void playGame() {
		while (isRun()) {
			changeStage();
		}
		System.out.println("게임종료");
	}
}
