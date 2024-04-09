package Unit;

abstract public class Unit {

	private String name;
	private int max_hp;
	private int hp;
	private int max_mp;
	private int mp;
	private int power;
	private int defense;
	private int lv;
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

	public int getLv() {
		return this.lv;
	}

	public void setLv() {
		this.lv++;
		setMaxHp();
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

	public String battleMessage() {
		String message = "";
		String remainHp = showHp();
		message += String.format("lv%d. [%s] : " + remainHp + "(%d/%d)   ", this.lv, this.name, this.hp, this.max_hp);
		if (this.max_mp > 0) {
			String remainMp = showMp();
			message += String.format(remainMp + "(%d/%d)   ", this.mp, this.max_mp);
		}
		message += String.format("공격력 / 방어율 : (%d / %d%%)", this.power, this.defense);
		return message;
	}

	private String showHp() {
		String showHp = "";
		double per = ((double) this.hp / (double) this.max_hp) * 10.0;
		for (int i = 1; i <= 10; i++) {
			if (per < 1) {
				showHp += "◧□□□□□□□□□";
				break;
			}

			if (per >= i)
				showHp += "■";
			else
				showHp += "□";
		}

		return showHp;
	}

	private String showMp() {
		String showMp = "";
		double per = (this.mp / this.max_mp) * 10;
		for (int i = 1; i <= 10; i++) {
			if (per < 1) {
				showMp += "◧□□□□□□□□□";
				break;
			}

			if (per >= i)
				showMp += "■";
			else
				showMp += "□";
		}

		return showMp;
	}

}
