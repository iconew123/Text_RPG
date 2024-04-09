package Unit;

import Item.Item;
import text_rpg.GameManager;

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
		while (this.exp >= this.getLv() * 100) {
			System.out.printf("[%s] 레벨업!!!\n", this.getName());
			this.setLv();
			this.exp -= (this.getLv() - 1) * 100;
		}
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
	public void attack(Unit monster) {
		Monster target = (Monster) monster;
		// 데미지 산출 공식
		int damage = (int) (this.getPower() * (1 - (double) target.getDefense() / 100));
		if (damage <= 1)
			damage = 1;
		String message = String.format("[%s]의 일반 공격으로 [%s]에게 [%d]의 피해를 입혔습니다.", this.getName(), target.getName(),
				damage);
		System.out.println(message);
		target.setHp(damage * -1);
		if (target.getHp() == 0) {
			System.out.printf("[%s] 유닛 처치로 , 경험치 : %d, 골드 : %d를 얻었습니다.\n", target.getName(), target.getExp(),
					target.getMoney());
			this.setExp(1000);
			Player.money += target.getMoney();
		}
		GameManager.getInstace().delay(1000);
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
