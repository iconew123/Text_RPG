package text_rpg;

import java.util.HashMap;
import java.util.Map;

import stage.Stage;
import stage.StageBattle;
import stage.StageCastle;
import stage.StageCave;
import stage.StageForest;
import stage.StageGuild;
import stage.StageLobby;
import stage.StageTitle;
import stage.StageTown;

public class GameManager {

	public static final String black = "\u001B[30m";
	public static final String red = "\u001B[31m";
	public static final String green = "\u001B[32m";
	public static final String yellow = "\u001B[33m";
	public static final String blue = "\u001B[34m";
	public static final String purple = "\u001B[35m";
	public static final String cyan = "\u001B[36m";
	public static final String white = "\u001B[37m";

	public static final String exit = "\u001B[0m";

	private Map<String, Stage> stageList = new HashMap<String, Stage>();

	public static boolean isOpenCave;
	public static boolean isOpenCastle;

	public static String curStage;
	public static String preStage;
	public static String nextStage;
	public static boolean isEnd;
	public static int pY;
	public static int pX;

	public boolean isRun() {
		return nextStage.equals("END") || isEnd ? false : true;
	}

	private GameManager() {
		setStages();
		curStage = "";
		preStage = "";
		nextStage = "TOWN";
		pY = 0;
		pX = 0;
	}

	private static GameManager instance = new GameManager();

	public static GameManager getInstace() {
		return instance;
	}

	private void setStages() {
		stageList.put("TITLE", new StageTitle());
		stageList.put("LOBBY", new StageLobby());
		stageList.put("TOWN", new StageTown());
		stageList.put("GUILD", new StageGuild());
		stageList.put("FOREST", new StageForest());
		stageList.put("CAVE", new StageCave());
		stageList.put("CASTLE", new StageCastle());
		stageList.put("BATTLE", new StageBattle());
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
