package shopping.model.vo;

public class Ggim {
	private int pNo;
	private String userId;
	private String pName;
	private int price;
	private String changeName;
	
	public Ggim() {}

	public Ggim(int pNo, String userId, String pName, int price, String changeName) {
		super();
		this.pNo = pNo;
		this.userId = userId;
		this.pName = pName;
		this.price = price;
		this.changeName = changeName;
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
		return "Cart [pNo=" + pNo + ", userId=" + userId + ", pName=" + pName + ", price="
				+ price + ", changeName=" + changeName + "]";
	}
	
}
