package stage;

import text_rpg.GameManager;

public class StageLobby extends Stage {

	private final int OK = 1;
	private final int NO = 2;

	private final int NEW_GAME = 1;
	private final int LOAD_GAME = 2;
	private final int SAVE_GAME = 3;

	@Override
	public void updateStage() {
		askHowToUse();
		int sel = inputNumber(">> ");
		choice(sel);
	}

	private void askHowToUse() {
		GameManager.getInstace().printSpace();

		String text = "게임 진행 방법에 대한 설명을 들으시겠습니까?\n";
		text += "[1] 예         [2] 아니오\n";
		GameManager.getInstace().showText(text, 1000);
	}

	private void choice(int sel) {
		if (sel == OK)
			showHowToUse();
		else if (sel == NO)
			showMenu();
	}

	private void showHowToUse() {

		String text = "게임 진행 방법 설명서\n";
		text += "[1]. 로비에서 새 게임 / 로드 게임 / 저장 기능을 선택 하실 수 있습니다.\n";
		text += "[2]. 이 게임은 콘솔 텍스트 RPG로 파티를 만들어서 몬스터를 잡아 성장하는 게임입니다.\n";
		text += "[3]. 새 게임시 플레이어는 Lv1 전사로 시작합니다.\n";
		text += "[4]. 길드에서 파티원을 영입하거나 퇴출시킬 수 있습니다.\n";
		text += "[5]. 길드에서 아이템을 구매하여 케릭터를 성장 할 수 있습니다.\n";
		text += "[6]. 전투 중 모든 파티원이 사망하면 GAME OVER 됩니다.\n";
		text += "[7]. 마지막 층의 보스를 클리어하면 GAME CLEAR 됩니다.\n";
		text += "로비 메뉴를 보시려면 'O'를 입력해주세요.\n";

		while (true) {
			GameManager.getInstace().printSpace();
			GameManager.getInstace().showText(text, 1000);
			String sel = inputString(">> ");

			if (sel.equals("O"))
				break;
		}

	}

	private void showMenu() {

		String text = "[1] 새 게임     [2] 로드 게임    [3] 게임 저장\n";

		while (GameManager.nextStage.equals("LOBBY")) {
			GameManager.getInstace().printSpace();
			System.out.println("=================[ LOBBY ]=================");
			GameManager.getInstace().showText(text, 0);
			System.out.println("============================================");
			int sel = inputNumber(">> ");

			if (sel == NEW_GAME) {
				GameManager.getInstace().showLoading();
				GameManager.nextStage = "TOWN";
				break;
			} else if (sel == LOAD_GAME) {
				// 여기에 데이터 로드
				GameManager.getInstace().showLoading();
				break;
			} else if (sel == SAVE_GAME) {
				// 여기에 데이터 세이브
				System.out.println("게임 세이브 성공");
			}
		}

	}

}
