package product.model.vo;

import java.sql.Date;

public class Order {
	private int bCode;
	private String userId;
	private String userName;
	private String orderName;
	private int pNo;
	private String pName;
	private Date buyDate;
	private int amount;
	private String status;
	private String deliveryPay;
	private String shipping;
	private String phone;
	private String deliveryStatus;
	private String request;
	private String cancelBuy;
	
	public Order() {}
	

	public Order(int bCode, String userId, int pNo, Date buyDate, int amount, String status, String deliveryPay,
			String shipping, String phone, String deliveryStatus, String request) {
		super();
		this.bCode = bCode;
		this.userId = userId;
		this.pNo = pNo;
		this.buyDate = buyDate;
		this.amount = amount;
		this.status = status;
		this.deliveryPay = deliveryPay;
		this.shipping = shipping;
		this.phone = phone;
		this.deliveryStatus = deliveryStatus;
		this.request = request;
	}
	
	public Order(int bCode, String userId, int pNo, Date buyDate, int amount, String status, String deliveryPay,
			String shipping, String phone, String deliveryStatus, String request, String cancelBuy) {
		super();
		this.bCode = bCode;
		this.userId = userId;
		this.pNo = pNo;
		this.buyDate = buyDate;
		this.amount = amount;
		this.status = status;
		this.deliveryPay = deliveryPay;
		this.shipping = shipping;
		this.phone = phone;
		this.deliveryStatus = deliveryStatus;
		this.request = request;
		this.cancelBuy = cancelBuy;
	}
	
	

	public Order(int bCode, String userId, String userName, String orderName, int pNo, String pName, Date buyDate,
			int amount, String status, String deliveryPay, String shipping, String phone, String deliveryStatus,
			String request, String cancelBuy) {
		super();
		this.bCode = bCode;
		this.userId = userId;
		this.userName = userName;
		this.orderName = orderName;
		this.pNo = pNo;
		this.pName = pName;
		this.buyDate = buyDate;
		this.amount = amount;
		this.status = status;
		this.deliveryPay = deliveryPay;
		this.shipping = shipping;
		this.phone = phone;
		this.deliveryStatus = deliveryStatus;
		this.request = request;
		this.cancelBuy = cancelBuy;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getOrderName() {
		return orderName;
	}


	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}


	public String getpName() {
		return pName;
	}


	public void setpName(String pName) {
		this.pName = pName;
	}


	public String getCancelBuy() {
		return cancelBuy;
	}


	public void setCancelBuy(String cancelBuy) {
		this.cancelBuy = cancelBuy;
	}


	public int getbCode() {
		return bCode;
	}

	public void setbCode(int bCode) {
		this.bCode = bCode;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeliveryPay() {
		return deliveryPay;
	}

	public void setDeliveryPay(String deliveryPay) {
		this.deliveryPay = deliveryPay;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
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


	@Override
	public String toString() {
		return "Order [bCode=" + bCode + ", userId=" + userId + ", userName=" + userName + ", orderName=" + orderName
				+ ", pNo=" + pNo + ", pName=" + pName + ", buyDate=" + buyDate + ", amount=" + amount + ", status="
				+ status + ", deliveryPay=" + deliveryPay + ", shipping=" + shipping + ", phone=" + phone
				+ ", deliveryStatus=" + deliveryStatus + ", request=" + request + ", cancelBuy=" + cancelBuy + "]";
	}

}
