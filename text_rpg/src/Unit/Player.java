package Unit;

public class Player extends Unit {

	public static int money;

	private String type;
	private int exp;
	private boolean isParty;
	// 아이템 착용 추가

	public Player(String name, String type, int hp, int power, int defense, int lv, boolean isParty) {
		super(name, hp, power, defense, lv);
		this.exp = 0;
		this.type = type;
		this.isParty = isParty;
		// 아이템 착용 초기값 null
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

}
