package _07_Rating.model;

import java.sql.Date;

public class RestRatingBean {
	private int ord_id;
	private int rest_id;
	private int restEva_star;
	private String m_username;
	private String restEva_comment;
	private Date eva_date;
	
	
	public RestRatingBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RestRatingBean(int ord_id, int rest_id, int restEva_star, String m_username, 
			String restEva_comment,Date eva_date) {
		super();
		this.ord_id = ord_id;
		this.rest_id = rest_id;
		this.restEva_star = restEva_star;
		this.m_username = m_username;
		this.restEva_comment = restEva_comment;
		this.eva_date = eva_date;
	}


	public Date getEva_date() {
		return eva_date;
	}


	public void setEva_date(Date eva_date) {
		this.eva_date = eva_date;
	}


	public int getOrd_id() {
		return ord_id;
	}


	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
	}


	public int getRest_id() {
		return rest_id;
	}


	public void setRest_id(int rest_id) {
		this.rest_id = rest_id;
	}


	public int getRestEva_star() {
		return restEva_star;
	}


	public void setRestEva_star(int restEva_star) {
		this.restEva_star = restEva_star;
	}


	public String getM_username() {
		return m_username;
	}


	public void setM_username(String m_username) {
		this.m_username = m_username;
	}


	public String getRestEva_comment() {
		return restEva_comment;
	}


	public void setRestEva_comment(String restEva_comment) {
		this.restEva_comment = restEva_comment;
	}


	@Override
	public String toString() {
		return "RestRatingBean [ord_id=" + ord_id + ", rest_id=" + rest_id + ", restEva_star=" + restEva_star
				+ ", m_username=" + m_username + ", restEva_comment=" + restEva_comment + "]";
	}
	
	
	
	
	
	
}
