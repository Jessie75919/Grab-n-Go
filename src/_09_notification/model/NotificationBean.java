package _09_notification.model;

public class NotificationBean {
    private int serial_no;
    private String m_username;
    private int rest_id;
    private String msg;
    private byte is_readed;
    
    
	public NotificationBean() {
		super();
	}


	public NotificationBean(int serial_no, String m_username, int rest_id, String msg, byte is_readed) {
		super();
		this.serial_no = serial_no;
		this.m_username = m_username;
		this.rest_id = rest_id;
		this.msg = msg;
		this.is_readed = is_readed;
	}
	
	public NotificationBean( String m_username, int rest_id, String msg, byte is_readed) {
		super();
		this.m_username = m_username;
		this.rest_id = rest_id;
		this.msg = msg;
		this.is_readed = is_readed;
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
		return "Notification [serial_no=" + serial_no + ", m_username=" + m_username + ", rest_id=" + rest_id + ", msg="
				+ msg + ", is_readed=" + is_readed + "]";
	}
    
    
    
}
