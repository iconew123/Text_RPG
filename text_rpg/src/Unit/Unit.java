package Unit;

import text_rpg.GameManager;

abstract public class Unit {

	private String name;
	private int max_hp;
	private int hp;
	private int max_mp;
	private int mp;
	private int power;
	private int defense;
	private int lv;
	private int exp;
	private boolean isDead;

	public Unit(String name, int hp, int mp, int power, int defense, int lv) {
		this.name = name;
		this.max_hp = hp;
		this.hp = hp;
		this.max_mp = mp;
		this.mp = mp;
		this.power = power;
		this.defense = defense;
		this.lv = lv;
		this.exp = 0;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxHp() {
		return this.max_hp;
	}

	public void setMaxHp() {
		this.max_hp += 50;
	}

	public int getMaxMp() {
		return this.max_mp;
	}

	public void setMaxMp() {
		this.max_mp += 50;
	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp += hp;
		if (this.hp <= 0) {
			this.hp = 0;
			this.isDead = true;
		}
	}

	public int getMp() {
		return this.mp;
	}

	public void setMp(int mp) {
		this.mp += mp;
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

	public int getExp() {
		return this.exp;
	}

	public void setExp(int exp) {
		this.exp += exp;
		// 플레이어 케릭터만 레벨업가능 -> 플레이어 인지 구분 방법 (MaxMp > 0)
		while (this.exp >= this.getLv() * 100 && this.getMaxMp() > 0) {
			System.out.printf("[%s] 레벨업!!!\n", this.getName());
			this.setLv();
			this.setHp(50);
			this.setMp(50);
			this.exp -= (this.getLv() - 1) * 100;
		}
	}

	public int getLv() {
		return this.lv;
	}

	public void setLv() {
		this.lv++;
		setMaxHp();
		setMaxMp();
		setPower();
		setDefense();
	}

	public boolean getIsDead() {
		return this.isDead;
	}

	abstract public void attack(Unit target);

	public void init(String name, int hp, int power, int defense, int lv) {
		this.name = name;
		this.max_hp = hp;
		this.hp = hp;
		this.power = power;
		this.defense = defense;
		this.lv = lv;
	}

	@Override
	public String toString() {
		String message = "";
		String remainHp = showHp();
		message += String.format("lv%d. [%s] : " + "hp : " + remainHp + "(%d/%d)   ", this.lv ,this.name, this.hp,
				this.max_hp);
		if (this.max_mp > 0) {
			String remainMp = showMp();
			String remainExp = showExp();
			message += String.format("mp : " + remainMp + "(%d/%d)   ", this.mp, this.max_mp);
			message += String.format("exp : " + remainExp + "(%d/%d)   ", this.exp, this.lv * 100);
		}
		message += String.format("공격력 / 방어율 : (%d / %d%%)", this.power, this.defense);
		return message;
	}

	private String showHp() {
		String showHp = "";
		double per = ((double) this.hp / (double) this.max_hp) * 10.0;
		for (int i = 1; i <= 10; i++) {
			if (per < 1) {
				showHp += GameManager.red + "◧□□□□□□□□□" + GameManager.exit;
				break;
			}

			if (per >= i)
				showHp += GameManager.red + "■" + GameManager.exit;
			else
				showHp += GameManager.red + "□" + GameManager.exit;
		}

		return showHp;
	}

	private String showMp() {
		String showMp = "";
		double per = ((double) this.mp / (double) this.max_mp) * 10.0;
		for (int i = 1; i <= 10; i++) {
			if (per < 1) {
				showMp += GameManager.blue + "◧□□□□□□□□□" + GameManager.exit;
				break;
			}

			if (per >= i)
				showMp += GameManager.blue + "■" + GameManager.exit;
			else
				showMp += GameManager.blue + "□" + GameManager.exit;
		}

		return showMp;
	}

	private String showExp() {
		String showExp = "";
		double per = ((double) this.exp / (double) (this.lv * 100)) * 10.0;
		for (int i = 1; i <= 10; i++) {
			if (per < 1) {
				showExp += GameManager.yellow + "◧□□□□□□□□□" + GameManager.exit;
				break;
			}

			if (per >= i)
				showExp += GameManager.yellow + "■" + GameManager.exit;
			else
				showExp += GameManager.yellow + "□" + GameManager.exit;
		}

		return showExp;
	}

}
