package shopping.model.vo;

public class Cart {
	private int pNo;
	private String userId;
	private int amount;
	private String pName;
	private int price;
	private String changeName;
	private int pAmount;
	
	public Cart() {}

	public Cart(int pNo, String userId, int amount, String pName, int price, String changeName) {
		super();
		this.pNo = pNo;
		this.userId = userId;
		this.amount = amount;
		this.pName = pName;
		this.price = price;
		this.changeName = changeName;
	}

	public Cart(int pNo, String userId, int amount, String pName, int price, String changeName, int pAmount) {
		super();
		this.pNo = pNo;
		this.userId = userId;
		this.amount = amount;
		this.pName = pName;
		this.price = price;
		this.changeName = changeName;
		this.pAmount = pAmount;
	}

	public int getpAmount() {
		return pAmount;
	}

	public void setpAmount(int pAmount) {
		this.pAmount = pAmount;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	@Override
	public String toString() {
		return "Cart [pNo=" + pNo + ", userId=" + userId + ", amount=" + amount + ", pName=" + pName + ", price="
				+ price + ", changeName=" + changeName + "]";
	}
	
}
