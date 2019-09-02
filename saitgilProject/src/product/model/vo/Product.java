package product.model.vo;

public class Product {
	
	private int pNo;
	private String pName;
	private int price;
	private int amount;
	private String contents;
	private int purchase;
	private String url;
	private String category;
	
	public Product() {}

	public Product(int pNo, String pName, int price, int amount, String contents, int purchase, String category) {
		super();
		this.pNo = pNo;
		this.pName = pName;
		this.price = price;
		this.amount = amount;
		this.contents = contents;
		this.purchase = purchase;
		this.category = category;
	}

	public Product(int pNo, String pName, int price, int amount, String contents, int purchase, String url,
			String category) {
		super();
		this.pNo = pNo;
		this.pName = pName;
		this.price = price;
		this.amount = amount;
		this.contents = contents;
		this.purchase = purchase;
		this.url = url;
		this.category = category;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getPurchase() {
		return purchase;
	}

	public void setPurchase(int purchase) {
		this.purchase = purchase;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [pNo=" + pNo + ", pName=" + pName + ", price=" + price + ", amount=" + amount + ", contents="
				+ contents + ", purchase=" + purchase + ", url=" + url + ", category=" + category + "]";
	}
	
}
