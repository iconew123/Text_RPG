package stage;

import java.util.Vector;

import Unit.Monster;
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
		System.out.printf("===============================[BATTLE %s]===============================\n",
				GameManager.preStage);
		turn = true;
		spawnMonster();
		battle();
	}

	private void spawnMonster() {
		players = unitManager.partyList;
		unitManager.summonRandomMonster(3);
		monsters = unitManager.getMonsterList();
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
		showUnitList();
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
				System.out.println(p.battleMessage());
		}
		System.out.println("==================================[MONSTER]==================================");
		for (Monster m : monsters)
			if (m.getIsDead())
				System.err.printf("lv%d. [%s] : [사망함]\n", m.getLv(), m.getName());
			else
				System.out.println(m.battleMessage());
		System.out.println("=============================================================================");
		GameManager.getInstace().delay(1500);
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
			int randomAction = random.nextInt(1) < 1 ? NORMAL_ATTACK : USE_SKILL;

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
