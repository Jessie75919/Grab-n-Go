package _04_ShoppingCart.model;

import java.util.*;
// 本類別存放訂單資料
public class OrderBean {
	int 	orderNo;
	String 	userId;
	double	totalAmount;
	String	shippingAddress; 
	String  bno;
	String  InvoiceTitle;
	Date  orderDate;
	Date  shippingDate;
	String	cancelTag;
	List<OrderItemDAOBean> items = new ArrayList<OrderItemDAOBean>();
	
	public OrderBean() {
		super();
	}

	public OrderBean(String userId, double totalAmount, String shippingAddress,
			String bNO, String invoiceTitle, Date orderDate,
			Date shippingDate, List<OrderItemDAOBean> items) {
		super();
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.shippingAddress = shippingAddress;
		bno = bNO;
		InvoiceTitle = invoiceTitle;
		this.orderDate = orderDate;
		this.shippingDate = shippingDate;
		this.items = items;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getInvoiceTitle() {
		return InvoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		InvoiceTitle = invoiceTitle;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getCancelTag() {
		return cancelTag;
	}

	public void setCancelTag(String cancelTag) {
		this.cancelTag = cancelTag;
	}

	public List<OrderItemDAOBean> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDAOBean> items) {
		this.items = items;
	}

}
