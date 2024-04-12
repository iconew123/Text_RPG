package Unit;

import java.util.Vector;

import Item.Item;
import text_rpg.GameManager;

public class Player extends Unit implements Skill {

	public static int money = 10000;

	private String type;
	private boolean isParty;
	Item weapon;
	Item armor;
	Item accessory;

	public Player(String name, String type, int hp, int mp, int power, int defense, int lv, boolean isParty) {
		super(name, hp, mp, power, defense, lv);
		this.type = type;
		this.isParty = isParty;
		this.weapon = null;
		this.armor = null;
		this.accessory = null;
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
		// (int) (this.getPower() * (1 - (double) target.getDefense() / 100));
		int damage = damageFunction(target, this.getPower());
		if (damage <= 1)
			damage = 1;
		String message = String.format("[%s]의 일반 공격으로 [%s]에게 [%d]의 피해를 입혔습니다.", this.getName(), target.getName(),
				damage);
		System.out.println(message);
		target.setHp(damage * -1);
		if (target.getHp() == 0) {
			System.out.printf("[%s] 유닛 처치로 , 경험치 : %d, 골드 : %d를 얻었습니다.\n", target.getName(), target.getExp(),
					target.getMoney());
			this.setExp(target.getExp());
			Player.money += target.getMoney();
		}
		GameManager.getInstace().delay(1000);
	}

	@Override
	public boolean skill(Unit target) {
		if (this.getType().equals("전사"))
			return smash(target);
		else if (this.getType().equals("마법사"))
			return wideAttack();
		else if (this.getType().equals("힐러"))
			heal();

		return true;
	}

	private int damageFunction(Unit target, int power) {
		int damage = (int) (power * (1 - (double) target.getDefense() / 100));

		return damage;
	}

	private boolean smash(Unit target) {
		if (this.getMp() < 50) {
			String message = String.format("[%s] 의 mp가 부족하여 스킬을 시전할 수 없습니다.", this.getName());
			System.out.println(GameManager.red + message + GameManager.exit);
			return false;
		}

		int damage = damageFunction(target, this.getPower() * 2);
		String message = String.format("[%s] 의 스킬 공격으로 [%s]에게 [%d]만큼의 피해를 입혔습니다.", this.getName(), target.getName(),
				damage);
		System.out.println(message);
		target.setHp(damage * -1);

		this.setMp(-50);

		return true;
	}

	private boolean wideAttack() {
		if (this.getMp() < 50) {
			String message = String.format("[%s] 의 mp가 부족하여 스킬을 시전할 수 없습니다.", this.getName());
			System.out.println(GameManager.red + message + GameManager.exit);
			return false;
		}

		Vector<Monster> monsters = UnitManager.getInstance().getMonsterList();
		for (Monster m : monsters) {
			if (m.getIsDead())
				continue;
			int damage = damageFunction(m, (int) (this.getPower() * 0.66));
			String message = String.format("[%s] 의 광역 공격으로 [%s]에게 [%d]만큼의 피해를 입혔습니다.", this.getName(), m.getName(),
					damage);
			System.out.println(message);
			m.setHp(damage * -1);
		}

		this.setMp(-50);
		return true;
	}

	private void heal() {
		int hpHeal = this.getPower();
		int mpHeal = this.getPower();
		for (Player p : UnitManager.partyList) {
			if (p.getIsDead())
				continue;
			// 계산결과
			if (p.getHp() + hpHeal >= p.getMaxHp())
				hpHeal = p.getMaxHp() - (p.getHp() + hpHeal);

			if (p.getMp() + mpHeal >= p.getMaxMp())
				mpHeal = p.getMaxMp() - (p.getMp() + mpHeal);

			p.setHp(hpHeal);
			p.setMp(mpHeal);
			String message = String.format("[%s]의 힐 스킬로 [%s] 의 hp/mp가 [%s/%s]회복 되었습니다.", this.getName(), p.getName(),
					hpHeal, mpHeal);
			System.out.println(message);

		}
	}

}
