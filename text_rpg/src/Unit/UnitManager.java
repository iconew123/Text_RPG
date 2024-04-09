package Unit;

import java.util.Random;
import java.util.Vector;

import text_rpg.GameManager;

public class UnitManager {

	public Random random = new Random();

	public static final int MAX_PARTY = 3;

	public static Vector<Player> partyList = new Vector<Player>();
	private Vector<Monster> monsterList = new Vector<Monster>();

	private String path = "Unit.";
	private String[] forestKind = { "MonsterSlime", "MonsterSnail", "MonsterMushroom" };
	private String[] caveKind = { "MonsterBat", "MonsterOrc", "MonsterGolem" };

	private UnitManager() {
		partyList.add(new Player(GameManager.playerName, GameManager.playerType, 500, 500, 30, 10, 1, true));
	}

	private static UnitManager instance = new UnitManager();

	public static UnitManager getInstance() {
		return instance;
	}

	public Vector<Monster> getMonsterList() {
		return this.monsterList;
	}

	public void summonRandomMonster(int size) {
		monsterList.clear();

		int lv = 0;

		for (int i = 0; i < size; i++) {

			String[] monsterKind = null;
			if (GameManager.preStage.equals("FOREST")) {
				lv = random.nextInt(10) + 1;
				monsterKind = forestKind;
			} else if (GameManager.preStage.equals("CAVE")) {
				lv = random.nextInt(10) + 11;
				monsterKind = caveKind;
			}

			int num = random.nextInt(monsterKind.length);

			try {
				Class<?> clazz = Class.forName(path + monsterKind[num]);
				Class<?>[] params = new Class<?>[] { String.class, int.class, int.class, int.class, int.class };
				String name = clazzName(clazz);
				Object obj = clazz.getDeclaredConstructor(params).newInstance(name, 0, 0, 0, 0);
				Monster tmp = (Monster) obj;

				int hp = (random.nextInt(lv) + 1) * 30;
				int power = (random.nextInt(lv) + 1) * 2;
				int defense = (random.nextInt(lv) + 1);
				tmp.setExp((random.nextInt(lv) + 1) * 20);
				tmp.setMoney((random.nextInt(lv) + 1) * 100);
				tmp.init(name, hp, power, defense, lv);

				monsterList.add((Monster) tmp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String clazzName(Class<?> clazz) {
		String name = "";
		String[] color = { "빨강", "파랑", "초록" };

		if (clazz.getName().equals(MonsterSlime.class.getName())) {
			name += color[random.nextInt(color.length)] + " 슬라임";
		} else if (clazz.getName().equals(MonsterSnail.class.getName())) {
			name += color[random.nextInt(color.length)] + " 달팽이";
		} else if (clazz.getName().equals(MonsterMushroom.class.getName())) {
			name += color[random.nextInt(color.length)] + " 버섯";
		} else if (clazz.getName().equals(MonsterBat.class.getName())) {
			name += "박쥐";
		} else if (clazz.getName().equals(MonsterOrc.class.getName())) {
			name += "오크";
		} else if (clazz.getName().equals(MonsterGolem.class.getName())) {
			name += "골렘";
		}

		return name;
	}

}
