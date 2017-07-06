package _05_orderProcess.model;

import java.io.Serializable;

public class OrderItemBean implements Serializable{
	int serial_no;
	int ord_id;
	int prod_id;
	String item_name;
	int item_price;
	int item_amount;
	String item_note;
	String m_username;
	String m_pickupname;
	

	

	public OrderItemBean() {
	}
	
	public OrderItemBean( int prod_id, String item_name, int item_price, int item_amount,
			String item_note) {
		super();
		this.prod_id = prod_id;
		this.item_name = item_name;
		this.item_price = item_price;
		this.item_amount = item_amount;
		this.item_note = item_note;
		
	}
	
	public OrderItemBean(int item_price, int item_amount, String item_note) {
		super();
		this.item_price = item_price;
		this.item_amount = item_amount;
		this.item_note = item_note;
	}

	@Override
	public String toString() {
		return "OrderItemBean [serial_no=" + serial_no + ", ord_id=" + ord_id + ", prod_id=" + prod_id + ", item_name="
				+ item_name + ", item_price=" + item_price + ", item_amount=" + item_amount + ", item_note=" + item_note
				+ ", m_username=" + m_username + "]";
	}

	public int getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(int serial_no) {
		this.serial_no = serial_no;
	}
	public int getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
	}
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public int getItem_amount() {
		return item_amount;
	}
	public void setItem_amount(int item_amount) {
		this.item_amount = item_amount;
	}
	public String getItem_note() {
		return item_note;
	}
	public void setItem_note(String item_note) {
		this.item_note = item_note;
	}
	public String getM_username() {
		return m_username;
	}
	public void setM_username(String m_username) {
		this.m_username = m_username;
	}
	public String getM_pickupname() {
		return m_pickupname;
	}

	public void setM_pickupname(String m_pickupname) {
		this.m_pickupname = m_pickupname;
	}

	
	
}
