package shopping.model.vo;

import java.sql.Date;

public class Order {
	private int buyCode;
	private String userId;
	private int pNo;
	private Date buyDate;
	private int buyAmount;
	private String buyStatue;
	private String address;
	private String phone;
	private String deliveryStatus;
	private String request;
	private String name;
	private String cancelStatus;
	private String Email;
	private String pName;
	private String changeName;
	private int price;
	
	public Order() {}
	
	public Order(String userId, int pNo, int buyAmount, String buyStatue, String address, String phone, String request,
			String name) {
		super();
		this.userId = userId;
		this.pNo = pNo;
		this.buyAmount = buyAmount;
		this.buyStatue = buyStatue;
		this.address = address;
		this.phone = phone;
		this.request = request;
		this.name = name;
	}

	public Order(int buyCode, String userId, int pNo, Date buyDate, int buyAmount, String buyStatue, String address,
			String phone, String deliveryStatus, String request, String name, String cancelStatus) {
		super();
		this.buyCode = buyCode;
		this.userId = userId;
		this.pNo = pNo;
		this.buyDate = buyDate;
		this.buyAmount = buyAmount;
		this.buyStatue = buyStatue;
		this.address = address;
		this.phone = phone;
		this.deliveryStatus = deliveryStatus;
		this.request = request;
		this.name = name;
		this.cancelStatus = cancelStatus;
	}
	

	public Order(int buyCode, String userId, int pNo, Date buyDate, int buyAmount, String buyStatue, String address,
			String phone, String deliveryStatus, String request, String name, String cancelStatus, String email) {
		super();
		this.buyCode = buyCode;
		this.userId = userId;
		this.pNo = pNo;
		this.buyDate = buyDate;
		this.buyAmount = buyAmount;
		this.buyStatue = buyStatue;
		this.address = address;
		this.phone = phone;
		this.deliveryStatus = deliveryStatus;
		this.request = request;
		this.name = name;
		this.cancelStatus = cancelStatus;
		Email = email;
	}
	
	

	public Order(int buyCode, String userId, int pNo, Date buyDate, int buyAmount, String buyStatue, String address,
			String phone, String deliveryStatus, String request, String name, String cancelStatus, String email,
			String pName, String changeName) {
		super();
		this.buyCode = buyCode;
		this.userId = userId;
		this.pNo = pNo;
		this.buyDate = buyDate;
		this.buyAmount = buyAmount;
		this.buyStatue = buyStatue;
		this.address = address;
		this.phone = phone;
		this.deliveryStatus = deliveryStatus;
		this.request = request;
		this.name = name;
		this.cancelStatus = cancelStatus;
		Email = email;
		this.pName = pName;
		this.changeName = changeName;
	}
	
	

	public Order(int buyCode, String userId, int pNo, Date buyDate, int buyAmount, String buyStatue, String address,
			String phone, String deliveryStatus, String request, String name, String cancelStatus, String email,
			String pName, String changeName, int price) {
		super();
		this.buyCode = buyCode;
		this.userId = userId;
		this.pNo = pNo;
		this.buyDate = buyDate;
		this.buyAmount = buyAmount;
		this.buyStatue = buyStatue;
		this.address = address;
		this.phone = phone;
		this.deliveryStatus = deliveryStatus;
		this.request = request;
		this.name = name;
		this.cancelStatus = cancelStatus;
		Email = email;
		this.pName = pName;
		this.changeName = changeName;
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getBuyCode() {
		return buyCode;
	}

	public void setBuyCode(int buyCode) {
		this.buyCode = buyCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public int getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(int buyAmount) {
		this.buyAmount = buyAmount;
	}

	public String getBuyStatue() {
		return buyStatue;
	}

	public void setBuyStatue(String buyStatue) {
		this.buyStatue = buyStatue;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCancelStatus() {
		return cancelStatus;
	}

	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	@Override
	public String toString() {
		return "Order [buyCode=" + buyCode + ", userId=" + userId + ", pNo=" + pNo + ", buyDate=" + buyDate
				+ ", buyAmount=" + buyAmount + ", buyStatue=" + buyStatue + ", address=" + address + ", phone=" + phone
				+ ", deliveryStatus=" + deliveryStatus + ", request=" + request + ", name=" + name + ", cancelStatus="
				+ cancelStatus + "]";
	}
	
}
