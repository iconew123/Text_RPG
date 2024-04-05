package Unit;

import java.util.Vector;

import text_rpg.GameManager;

public class UnitManager {

	private static Vector<Player> playerList = new Vector<Player>();
	private Vector<Unit> monsterList = new Vector<Unit>();
	
	private UnitManager() {
		playerList.add(new Player(GameManager.playerName, "전사", 1000, 50, 10, 1, true));
	}
	
	private static UnitManager instance = new UnitManager();
	
	public static UnitManager getInstance() {
		return instance;
	}	
}
