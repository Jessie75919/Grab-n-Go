package _09_notification.model;

import java.sql.Timestamp;

public class NotificationBean {
    private int serial_no;
    private String m_username;
    private int rest_id;
    private String msg;
    private byte is_readed;
    private Timestamp noti_time;
    private int ord_id;
    
    
	public NotificationBean() {
		super();
	}


	public NotificationBean(int serial_no, String m_username, int rest_id, 
			String msg, byte is_readed , Timestamp noti_time, int ord_id) {
		super();
		this.serial_no = serial_no;
		this.m_username = m_username;
		this.rest_id = rest_id;
		this.msg = msg;
		this.is_readed = is_readed;
		this.noti_time = noti_time;
		this.ord_id = ord_id;
	}
	
	public NotificationBean( String m_username, int rest_id, String msg, byte is_readed,
			Timestamp noti_time, int ord_id) {
		super();
		this.m_username = m_username;
		this.rest_id = rest_id;
		this.msg = msg;
		this.is_readed = is_readed;
		this.noti_time = noti_time;
		this.ord_id = ord_id;
	}
	
	


	public int getOrd_id() {
		return ord_id;
	}


	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
	}


	public Timestamp getNoti_time() {
		return noti_time;
	}


	public void setNoti_time(Timestamp noti_time) {
		this.noti_time = noti_time;
	}


	public int getSerial_no() {
		return serial_no;
	}


	public void setSerial_no(int serial_no) {
		this.serial_no = serial_no;
	}


	public String getM_username() {
		return m_username;
	}


	public void setM_username(String m_username) {
		this.m_username = m_username;
	}


	public int getRest_id() {
		return rest_id;
	}


	public void setRest_id(int rest_id) {
		this.rest_id = rest_id;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public byte getIs_readed() {
		return is_readed;
	}


	public void setIs_readed(byte is_readed) {
		this.is_readed = is_readed;
	}


	@Override
	public String toString() {
		return "NotificationBean [serial_no=" + serial_no + ", m_username=" + m_username + ", rest_id=" + rest_id
				+ ", msg=" + msg + ", is_readed=" + is_readed + ", noti_time=" + noti_time + ", ord_id=" + ord_id + "]";
	}




	
    
    
    
}
