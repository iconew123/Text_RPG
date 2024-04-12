package stage;

import java.util.Vector;

import Unit.Player;
import Unit.UnitManager;
import text_rpg.GameManager;

public class StageGuild extends Stage {

	private Vector<Player> guildMenber;

	private final int GUILD_MANAGER = 1;
	private final int GUILD_STORE = 2;
	private final int inventory = 3;
	private final int EXIT = 0;

	private final int GUILD_LIST = 1;
	private final int ADD_GUILD_LIST = 2;
	private final int EXPEL_GUILD_LIST = 3;
	private final int CHANGE_PARTY = 4;
	private final int SORT = 5;

	private final int ADD_PARTY_MEMBER = 1;
	private final int DELETE_PARTY_MEMBER = 2;
	private final int CHANGE_PARTY_MEMBER = 3;

	public Vector<Player> getGuildMember() {
		return this.guildMenber;
	}

	public void setGuildData(Vector<Player> loadData) {
		this.guildMenber = loadData;
	}

	@Override
	public void updateStage() {
		if (guildMenber == null)
			guildMenber = (Vector<Player>) UnitManager.partyList.clone();

		while (true) {
			showMenu();
			int guildSel = inputNumber("메뉴 선택 : ");

			if (guildSel == GUILD_MANAGER)
				guildManager();
			else if (guildSel == GUILD_STORE)
				store();
			else if (guildSel == inventory)
				inven();
			else if (guildSel == EXIT) {
				System.out.println("길드 관리를 종료하고 마을로 돌아갑니다.");
				UnitManager.partyList = getPartyMembers();
				GameManager.nextStage = "TOWN";
				break;
			}
		}
	}

	private void showMenu() {
		GameManager.getInstace().printSpace();
		String message = "=================[ GUILD ]=================\n";
		message += "[1] 길드관리   	[2] 길드 상점    [3] 인벤토리\n";
		message += "[0] 뒤로가기\n";
		message += "==============================================\n";
		System.out.println(message);
	}

	private void guildManager() {
		while (true) {
			showGuildManagement();
			int manager = inputNumber("메뉴 선택 : ");

			if (manager == GUILD_LIST) {
				showGuildList();
				String next = inputString("상위 메뉴로 가려면 아무키나 누르세요");
			} else if (manager == ADD_GUILD_LIST)
				addGuildList();
			else if (manager == EXPEL_GUILD_LIST)
				expelGuildList();
			else if (manager == CHANGE_PARTY)
				changePartyList();
			else if (manager == SORT)
				sort();
			else if (manager == EXIT) {
				System.out.println("이전 메뉴로 돌아갑니다.");
				break;
			}
		}
	}

	private void showGuildManagement() {
		GameManager.getInstace().printSpace();
		String message = "=================[ GUILD ]=================\n";
		message += "[1] 길드 목록   	[2] 길드원 영입    [3] 길드원 제명\n";
		message += "[4] 파티원 변경    [5] 길드원 정렬    [0] 뒤로가기\n";
		message += "============================================\n";
		System.out.println(message);
	}

	private void showGuildList() {
		GameManager.getInstace().printSpace();
		System.out.println("============================[길드원 목록]============================");
		System.out.println(GameManager.white + "파티중인 경우\t" + GameManager.exit + GameManager.green + "파티중이 아닌 경우"
				+ GameManager.exit);
		for (int i = 0; i < guildMenber.size(); i++) {
			Player g = guildMenber.get(i);
			if (g.getIsParty())
				System.out.println(GameManager.white + "[ " + (i + 1) + " 번 ]. " + GameManager.exit + "[ " + g.getType()
						+ " ] " + g);
			else
				System.out.println(GameManager.green + "[ " + (i + 1) + " 번 ]. " + GameManager.exit + "[ " + g.getType()
						+ " ] " + g);
		}
		System.out.println("================================================================");
	}

	public int getAvgLv() {
		int avg = 0;

		for (Player p : UnitManager.partyList)
			avg += p.getLv();

		avg /= UnitManager.partyList.size();

		return avg;
	}

	private void addGuildList() {
		Player tmpP = randomGuildMenber();
		System.out.println("모험가가 길드에 가입했습니다.");
		System.out.println("[ " + tmpP.getType() + " ] " + tmpP);
		GameManager.getInstace().delay(2000);
		guildMenber.add(tmpP);
	}

	private Player randomGuildMenber() {
		String[] nf = { "박", "이", "김", "최", "유", "지", "오" };
		String[] nm = { "명", "기", "종", "민", "재", "석", "광" };
		String[] nl = { "수", "자", "민", "수", "석", "민", "철" };

		String[] t = { "전사", "마법사", "힐러" };

		String name = nf[random.nextInt(nf.length)] + nm[random.nextInt(nf.length)] + nl[random.nextInt(nf.length)];
		String type = t[random.nextInt(t.length)];
		int lv = getAvgLv() + random.nextInt(3) - 1;
		if (lv <= 0)
			lv = 1;
		int hp = 500 + (random.nextInt(50) - 25 + lv * 50);
		int mp = 500 + (random.nextInt(50) - 25 + lv * 50);
		int power = 30 + random.nextInt(3) * lv;
		int defense = 10 + random.nextInt(3) * lv;

		return new Player(name, type, hp, mp, power, defense, lv, false);
	}

	private void expelGuildList() {
		showGuildList();
		int delSel = inputNumber("제명시킬 번호를 입력하세요 : ") - 1;

		if (delSel < 0 || delSel >= guildMenber.size())
			return;
		else if (guildMenber.get(delSel).getIsParty()) {
			System.err.println("파티중인 멤버는 제명 할 수 없습니다. 파티 해제 후 이용하세요.");
			GameManager.getInstace().delay(1000);
			return;
		}

		guildMenber.remove(delSel);
		System.out.println("제명 완료");
		GameManager.getInstace().delay(500);
	}

	private void changePartyList() {
		while (true) {
			GameManager.getInstace().printSpace();
			System.out.println("[1] 파티원 추가 , [2] 파티원 제명 , [3] 파티원 교체 , [0] 종료");
			int partySel = inputNumber(">> ");

			if (partySel == ADD_PARTY_MEMBER)
				addParty();
			else if (partySel == DELETE_PARTY_MEMBER)
				deleteParty();
			else if (partySel == CHANGE_PARTY_MEMBER)
				changeParty();
			else if (partySel == EXIT) {
				System.out.println("이전 메뉴로 돌아갑니다.");
				break;
			}
		}
	}

	private void showPartyList() {
		GameManager.getInstace().printSpace();
		System.out.println("============================[파티원 목록]============================");
		for (int i = 0; i < UnitManager.partyList.size(); i++) {
			Player p = UnitManager.partyList.get(i);
			System.out.println("[ " + (i + 1) + " 번 ]. " + "[ " + p.getType() + " ] " + p);
		}
		System.out.println("================================================================\n");
	}

	private void addParty() {
		showGuildList();
		if (UnitManager.partyList.size() >= 3) {
			System.err.println("최대 파티 인원은 3명입니다.");
			GameManager.getInstace().delay(500);
			return;
		}

		int addSel = inputNumber("추가할 번호를 입력하세요 : ") - 1;
		if (addSel < 0 || addSel >= guildMenber.size() || (guildMenber.get(addSel).getIsParty())) {
			System.err.println("이미 파티중인 멤버이거나 , 길드에 없는 멤버입니다.");
			GameManager.getInstace().delay(500);
			return;
		}

		Player p = guildMenber.get(addSel);
		p.setIsParty();
		UnitManager.partyList.add(p);
		System.out.println("파티원을 추가했습니다.");
		GameManager.getInstace().delay(1000);
	}

	private void deleteParty() {
		showPartyList();
		if (UnitManager.partyList.size() < 2) {
			System.err.println("최소 1명은 유지되어야 합니다.");
			GameManager.getInstace().delay(500);
			return;
		}

		int delSel = inputNumber("제명 시킬 번호를 입력하세요 : ") - 1;
		if (delSel < 0 || delSel >= UnitManager.partyList.size()) {
			System.err.println("파티에 없는 멤버입니다.");
			GameManager.getInstace().delay(500);
			return;
		}

		UnitManager.partyList.get(delSel).setIsParty();
		UnitManager.partyList.remove(delSel);
		System.out.println("파티원을 제거했습니다.");
		GameManager.getInstace().delay(1000);
	}

	private void changeParty() {
		showPartyList();
		int n1 = inputNumber("교체 하고 싶은 번호를 입력하세요 : ") - 1;
		if (n1 < 0 || n1 >= UnitManager.partyList.size()) {
			System.err.println("파티에 없는 멤버입니다.");
			GameManager.getInstace().delay(500);
			return;
		}
		showGuildList();
		int n2 = inputNumber("가입시키고 싶은 번호를 입력하세요 : ") - 1;
		if (n2 < 0 || n2 >= guildMenber.size() || (guildMenber.get(n2).getIsParty())) {
			System.err.println("이미 파티중인 멤버이거나 , 길드에 없는 멤버입니다.");
			GameManager.getInstace().delay(500);
			return;
		}

		Player p = guildMenber.get(n2);
		String message = String.format("기존 파티원 [%s]를 길드원 [%s]로 교체 하였습니다.", UnitManager.partyList.get(n1).getName(),
				p.getName());
		UnitManager.partyList.get(n1).setIsParty();
		p.setIsParty();
		UnitManager.partyList.set(n1, p);
		System.out.println(message);
		GameManager.getInstace().delay(1000);

	}

	private void sort() {
		System.out.println("길드원 목록을 레벨순으로 정렬합니다.");
		for (int i = 0; i < guildMenber.size() - 1; i++) {
			for (int j = i + 1; j < guildMenber.size(); j++) {
				Player pi = guildMenber.get(i);
				Player pj = guildMenber.get(j);
				if (pj.getLv() > pi.getLv()) {
					// 두 요소를 교환하여 내림차순으로 정렬
					Player temp = guildMenber.get(i);
					guildMenber.set(i, guildMenber.get(j));
					guildMenber.set(j, temp);
				}
			}
		}
		GameManager.getInstace().delay(1000);
	}

	private void store() {

	}

	private void inven() {

	}

	private Vector<Player> getPartyMembers() {
		Vector<Player> tmp = new Vector<Player>();
		for (Player g : guildMenber)
			if (g.getIsParty())
				tmp.add(g);

		return tmp;
	}

}
