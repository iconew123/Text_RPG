package Unit;

import java.util.Random;

public class Monster extends Unit {


	public Random random = new Random();

	private int exp;
	private int money;
	public Monster(String name, int hp, int mp, int power, int defense, int lv) {
		super(name, hp, mp, power, defense, lv);
	}

	public int getExp() {
		return this.exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	void Attack(Unit target) {

	}

}
