package Unit;

import java.util.Vector;

import text_rpg.GameManager;

public class UnitManager {

	public static final int MAX_PARTY = 3;

	public static Vector<Player> partyList = new Vector<Player>();
	private Vector<Unit> monsterList = new Vector<Unit>();

	private UnitManager() {
		partyList.add(new Player(GameManager.playerName, GameManager.playerType, 500, 500, 30, 10, 1, true));
	}

	private static UnitManager instance = new UnitManager();

	public static UnitManager getInstance() {
		return instance;
	}

}
