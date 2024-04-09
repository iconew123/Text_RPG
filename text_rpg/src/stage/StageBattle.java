package stage;

import java.util.Vector;

import Unit.Monster;
import Unit.MonsterSlime;
import Unit.Player;
import Unit.UnitManager;
import text_rpg.GameManager;

public class StageBattle extends Stage {

	private final int NORMAL_ATTACK = 1;
	private final int USE_SKILL = 2;

	private UnitManager unitManager = UnitManager.getInstance();
	private Vector<Player> players;
	private Vector<Monster> monsters;
	private int livePlayer;
	private int liveMonster;
	private boolean turn;

	@Override
	public void updateStage() {
		GameManager.getInstace().printSpace();
		System.out.printf("===============================[BATTLE %s]===============================\n",
				GameManager.preStage);
		turn = true;
		spawnMonster();
		battle();
	}

	private void spawnMonster() {
		players = unitManager.partyList;
		if (GameManager.preStage.contains("보스")) {
			monsters = new Vector<Monster>();
			if (GameManager.preStage.contains("FOREST")) {
				MonsterSlime boss = new MonsterSlime("킹 슬라임", 2000, 50, 20, 10);
				boss.setExp(500);
				boss.setMoney(1000);
				monsters.add(boss);
			} else if (GameManager.preStage.contains("CAVE")) {
				MonsterSlime boss = new MonsterSlime("킹 케슬 골렘", 4000, 70, 50, 20);
				boss.setExp(500);
				boss.setMoney(1000);
				monsters.add(boss);
			}

		} else {
			unitManager.summonRandomMonster(3);
			monsters = unitManager.getMonsterList();
		}
		livePlayer = players.size();
		liveMonster = monsters.size();
	}

	private void battle() {
		while (livePlayer > 0 && liveMonster > 0) {
			showUnitList();
			if (turn)
				attackPlayers();
			else
				attackMonsters();
			deadCount();
			turn = !turn;
		}
		if (liveMonster == 0) {
			System.out.println("플레이어 승리!");
			GameManager.getInstace().delay(1000);
		}
		countMonsters();
		showUnitList();
		// 플레이어 유닛 모두 사망시 즉시 게임종료
		if (GameManager.nextStage.equals("END"))
			return;

		// 이전 맵 돌아가기(보스전 포함)
		if (GameManager.preStage.contains("FOREST"))
			GameManager.preStage = "FOREST";
		else if (GameManager.preStage.contains("CAVE"))
			GameManager.preStage = "CAVE";
		GameManager.nextStage = GameManager.preStage;
		GameManager.preStage = "";
	}

	private void showUnitList() {
		GameManager.getInstace().printSpace();
		System.out.println("===================================[PLAYER]==================================");
		for (Player p : players) {
			if (p.getIsDead())
				System.err.printf("lv%d. [%s] : [사망함]\n", p.getLv(), p.getName());
			else
				System.out.println(p);
		}
		System.out.println("==================================[MONSTER]==================================");
		for (Monster m : monsters)
			if (m.getIsDead())
				System.err.printf("lv%d. [%s] : [사망함]\n", m.getLv(), m.getName());
			else
				System.out.println(m);
		System.out.println("=============================================================================");
		GameManager.getInstace().delay(1500);
	}

	private void countMonsters() {
		for (int i = 0; i < monsters.size(); i++) {
			String name = monsters.get(i).getName();

			// 스테이지 해금 조건
			if (name.equals("킹 슬라임"))
				GameManager.isOpenCave = true;
			else if (name.equals("킹 케슬 골렘"))
				GameManager.isOpenCastle = true;

			// 각각의 몬스터 카운트
			if (name.contains("슬라임"))
				GameManager.slimeCnt++;
			else if (name.contains("버섯"))
				GameManager.mushroomCnt++;
			else if (name.contains("달팽이"))
				GameManager.snailCnt++;
			else if (name.contains("오크"))
				GameManager.snailCnt++;
			else if (name.contains("박쥐"))
				GameManager.snailCnt++;
			else if (name.contains("골렘"))
				GameManager.snailCnt++;
		}
	}

	private int targetMonster() {
		int ranTarget = -1;
		while (true) {
			ranTarget = random.nextInt(monsters.size());

			if (!monsters.get(ranTarget).getIsDead())
				break;
		}
		return ranTarget;
	}

	private int targetPlayer() {
		int ranTarget = -1;
		while (true) {
			ranTarget = random.nextInt(players.size());

			if (!players.get(ranTarget).getIsDead())
				break;
		}
		return ranTarget;
	}

	private void attackPlayers() {
		System.out.println("==================================[메뉴 선택]==================================");
		for (int i = 0; i < players.size(); i++) {
			deadCount();
			if (liveMonster == 0)
				break;

			Player player = players.get(i);
			if (player.getIsDead())
				continue;

			int randomTarget = targetMonster();
			System.out.printf("[%s] [1.일반 공격] [2.스킬 사용(mp소모)]\n", player.getName());
			int sel = inputNumber(">>");

			if (sel == NORMAL_ATTACK)
				player.attack(monsters.get(randomTarget));
			else if (sel == USE_SKILL) {
				// 스킬 구현
			} else {
				i--;
				continue;
			}
		}
	}

	private void attackMonsters() {
		for (int i = 0; i < monsters.size(); i++) {
			deadCount();
			if (livePlayer == 0)
				break;

			Monster monster = monsters.get(i);
			if (monster.getIsDead())
				continue;

			int randomTarget = targetPlayer();
			// 보스랑 2스테이지유닛만 스킬을 사용
			int randomAction = 1;
			if (GameManager.preStage.contains("CAVE") || monster.getName().contains("킹"))
				randomAction = random.nextInt(10) < 3 ? NORMAL_ATTACK : USE_SKILL;

			if (randomAction == NORMAL_ATTACK)
				monster.attack(players.get(randomTarget));
			else if (randomAction == USE_SKILL) {

			}
		}
	}

	private void deadCount() {
		int pDead = 0;
		int mDead = 0;

		for (int i = 0; i < players.size(); i++)
			if (players.get(i).getIsDead())
				pDead++;
		for (int i = 0; i < monsters.size(); i++)
			if (monsters.get(i).getIsDead())
				mDead++;

		livePlayer = players.size() - pDead;
		liveMonster = monsters.size() - mDead;

		if (livePlayer == 0)
			GameManager.nextStage = "END";
	}

}
