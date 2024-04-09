package Unit;

import java.util.Random;

import text_rpg.GameManager;

public class Monster extends Unit {

	public Random random = new Random();

	private int money;

	public Monster(String name, int hp, int power, int defense, int lv) {
		super(name, hp, 0, power, defense, lv);
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public void attack(Unit target) {
		// 데미지 산출 공식
		int damage = (int) (this.getPower() * (1 - (double) target.getDefense() / 100));
		if (damage <= 1)
			damage = 1;
		String message = String.format("[%s]의 일반 공격으로 [%s]에게 [%d]의 피해를 입혔습니다.", this.getName(), target.getName(),
				damage);
		System.out.println(message);
		target.setHp(damage * -1);
		if (target.getHp() == 0)
			System.out.printf("[%s] 유팃 사망\n", target.getName());
		GameManager.getInstace().delay(1000);
	}

}
