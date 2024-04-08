package Item;

public class Item {

	public static final int WEAPON = 1;
	public static final int ARMOR = 2;
	public static final int ACCESSORY = 3;
	public static final int HP_POTION = 4;
	public static final int MP_POTION = 5;

	private int kind;
	private String name;
	private int stat;
	private int price;

	public Item(int kind, String name, int stat, int price) {
		this.kind = kind;
		this.name = name;
		this.stat = stat;
		this.price = price;
	}

	public int getKind() {
		return this.kind;
	}

	public String getName() {
		return this.name;
	}

	public int getStat() {
		return this.stat;
	}

	public int getPrice() {
		return this.price;
	}
}
