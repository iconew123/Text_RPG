package Unit;

public class MonsterSlime extends Monster implements Skill{

	public MonsterSlime(String name, int hp, int power, int defense, int lv) {
		super(name, hp, power, defense, lv);
	}

	@Override
	public boolean skill(Unit target) {
		
		
		return true;
	}
}
