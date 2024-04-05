package stage;

import text_rpg.GameManager;

public class StageBattle extends Stage {

	@Override
	public void updateStage() {

		if (GameManager.preStage.equals("FOREST")) {
			System.out.println("숲 전투 시작");
			GameManager.nextStage = GameManager.preStage;
		} else if (GameManager.preStage.equals("CAVE")) {
			System.out.println("동굴 전투 시작");
			GameManager.nextStage = GameManager.preStage;
		}

	}

}
