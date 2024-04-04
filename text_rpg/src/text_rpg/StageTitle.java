package text_rpg;

public class StageTitle extends Stage {

	@Override
	public void updateStage() {
		while (GameManager.nextStage.equals("TITLE")) {
			GameManager.getInstace().printSpace();
			showTitle();
			inputString("게임을 실행하려면 아무키나 누르세요.\n");
			GameManager.nextStage = "LOBBY";
		}
	}

	private void showTitle() {
		String text = "=================[TEXT RPG]=================\n";
		text += "[*] 게임 시작\n";
		text += "============================================\n";
		String[] show = text.split("");
		for (int i = 0; i < show.length; i++) {
			System.out.print(show[i]);
			GameManager.getInstace().delay();
		}
	}

}
