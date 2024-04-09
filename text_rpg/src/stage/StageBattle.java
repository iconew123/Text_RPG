package stage;

import java.util.Vector;

import Unit.Monster;
import Unit.UnitManager;

public class StageBattle extends Stage {
	
	private Vector<Monster> monsters;

	@Override
	public void updateStage() {

		UnitManager.getInstance().summonRandomMonster(3);
		monsters = UnitManager.getInstance().getMonsterList();
		System.out.println();

	}

}
