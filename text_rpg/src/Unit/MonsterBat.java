package Unit;

public class MonsterBat extends Monster implements Skill{

	public MonsterBat(String name, int hp, int power, int defense, int lv) {
		super(name, hp, power, defense, lv);
	}

	@Override
	public boolean skill(Unit target) {
		// TODO Auto-generated method stub
		
		
		return true;
	}

}
