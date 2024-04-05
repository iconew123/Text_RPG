package Unit;

abstract public class Unit {

	private String name;
	private int max_hp;
	private int hp;
	private int power;
	private int defense;
	private int lv;

	public Unit(String name, int hp, int power, int defense, int lv) {
		this.name = name;
		this.max_hp = hp;
		this.hp = hp;
		this.power = power;
		this.defense = defense;
		this.lv = lv;
	}

	public String getName() {
		return this.name;
	}

	public int getMaxHp() {
		return this.max_hp;
	}

	public void setMaxHp() {
		this.max_hp += 50;
	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp += hp;
		if (hp <= 0)
			hp = 0;
	}

	public int getPower() {
		return this.power;
	}

	public void setPower() {
		this.power += 5;
	}

	public int getDefense() {
		return this.defense;
	}

	public void setDefense() {
		this.defense += 1;
	}

	public int getLv() {
		return this.lv;
	}

	public void setLv() {
		this.lv++;
	}

}
