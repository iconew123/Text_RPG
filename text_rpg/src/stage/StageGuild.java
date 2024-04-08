package stage;

public class StageGuild extends Stage {

	private final int PARTY_LIST = 1;
	private final int GUILD_LIST = 2;
	private final int ADD_GUILD_LIST = 3;
	private final int EXPEL_GUILD_LIST = 4;
	private final int ADD_PARTY_LIST = 5;
	private final int EXIT = 5;

	@Override
	public void updateStage() {
		while(true) {
			showMenu();
//			int guildSel = inputNumber("메뉴 선택 : ");
//			
//			if(guildSel == PARTY_LIST)
//				showPartyList();
//			else if(guildSel == GUILD_LIST)
//				showGuildList();
//			else if(guildSel == ADD_GUILD_LIST)
//				addGuildList();
//			else if(guildSel == EXPEL_GUILD_LIST)
//				expelGuildList();
//			else if(guildSel == ADD_PARTY_LIST)
//				addPartyList();
//			else if(guildSel == EXIT) {
//				System.out.println("길드 관리를 종료하고 마을로 돌아갑니다.");
//				break;
//			}
		}
	}

	private void showMenu() {
		String message = "=================[ GUILD ]=================";
		message += "[1] 파티원 목록 	[2] 길드원 목록    [3] 길드원 추가";
		message += "[4] 길드원 제명    [5] 파티원 추가    [0] 뒤로가기";
		message += "============================================";
		System.out.println(message);
	}
}
