package _05_orderProcess.model;

import java.sql.Date;
import java.sql.Timestamp;

public class OrderBean {
	int ord_id;
	String m_username;
	String m_pickupname;
	Timestamp ord_time;
	Timestamp ord_pickuptime;
	int rest_id;
	int ord_totalPrice;
	String ord_status;
	String rest_name;

	public OrderBean() {
		super();
	}

	public int getOrd_id() {
		return ord_id;
	}

	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
	}

	public String getM_username() {
		return m_username;
	}

	public void setM_username(String m_username) {
		this.m_username = m_username;
	}

	public Timestamp getOrd_time() {
		return ord_time;
	}

	public void setOrd_time(Timestamp ord_time) {
		this.ord_time = ord_time;
	}

	public int getRest_id() {
		return rest_id;
	}

	public void setRest_id(int rest_id) {
		this.rest_id = rest_id;
	}

	public int getOrd_totalPrice() {
		return ord_totalPrice;
	}

	public void setOrd_totalPrice(int ord_totalPrice) {
		this.ord_totalPrice = ord_totalPrice;
	}

	public String getOrd_status() {
		return ord_status;
	}

	public void setOrd_status(String ord_status) {
		this.ord_status = ord_status;
	}

	public String getRest_name() {
		return rest_name;
	}

	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}

	public String getM_pickupname() {
		return m_pickupname;
	}

	public void setM_pickupname(String m_pickupname) {
		this.m_pickupname = m_pickupname;
	}

	public Timestamp getOrd_pickuptime() {
		return ord_pickuptime;
	}

	public void setOrd_pickuptime(Timestamp ord_pickuptime) {
		this.ord_pickuptime = ord_pickuptime;
	}

}
