package Unit;

import Item.Item;

public class Player extends Unit {

	public static int money = 10000;

	private String type;
	private int exp;
	private boolean isParty;
	Item weapon;
	Item armor;
	Item accessory;

	public Player(String name, String type, int hp, int mp, int power, int defense, int lv, boolean isParty) {
		super(name, hp, mp, power, defense, lv);
		this.exp = 0;
		this.type = type;
		this.isParty = isParty;
		this.weapon = null;
		this.armor = null;
		this.accessory = null;
	}

	public int getExp() {
		return this.exp;
	}

	public void setExp(int exp) {
		this.exp += exp;
	}

	public String getType() {
		return this.type;
	}

	public boolean getIsParty() {
		return this.isParty;
	}

	public void setIsParty() {
		this.isParty = !this.isParty;
	}

	@Override
	void Attack(Unit target) {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String message = String.format("[이름 : %s] , [직업 : %s] , [레벨 : %d] , [exp : %d/%d]\n", this.getName(), this.type,
				this.getLv(), this.exp, this.getLv() * 100);
		message += String.format("[HP : %d/%d] , [MP : %d/%d] , [공격력 : %d] , [방어력 : %d]\n", this.getHp(),
				this.getMaxHp(), this.getMp(), this.getMaxMp(), this.getPower(), this.getDefense());
		return message;
	}

}
