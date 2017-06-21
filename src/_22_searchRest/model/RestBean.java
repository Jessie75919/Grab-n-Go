package _22_searchRest.model;

import java.sql.Blob;

public class RestBean {
	
	private int rest_Id;
	private String rest_typeId;
	private String rest_name;
	private String rest_branch;
	private String rest_address;
	private String rest_phone;
	private String rest_owner;
	private String rest_email;
	private String rest_userName;
	private String rest_password;
	private String rest_url;
	private double rest_longitude;
	private double rest_latitude;
	private Blob rest_mainbanner;
	private Blob rest_logo;
	private Blob rest_coverimage;
	
	//-----------------------------------------------
	
	public RestBean() {
		super();
	}
    
	
	
	public RestBean(int rest_Id, String rest_typeId, String rest_name, String rest_branch, String rest_address,
			String rest_phone) {
		super();
		this.rest_Id = rest_Id;
		this.rest_typeId = rest_typeId;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
	}



	//-----------------------------------------------
	public int getRest_Id() {
		return rest_Id;
	}

	public void setRest_Id(int rest_Id) {
		this.rest_Id = rest_Id;
	}

	public String getRest_typeId() {
		return rest_typeId;
	}

	public void setRest_typeId(String rest_typeId) {
		this.rest_typeId = rest_typeId;
	}

	public String getRest_name() {
		return rest_name;
	}

	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}

	public String getRest_branch() {
		return rest_branch;
	}

	public void setRest_branch(String rest_branch) {
		this.rest_branch = rest_branch;
	}

	public String getRest_address() {
		return rest_address;
	}

	public void setRest_address(String rest_address) {
		this.rest_address = rest_address;
	}

	public String getRest_phone() {
		return rest_phone;
	}

	public void setRest_phone(String rest_phone) {
		this.rest_phone = rest_phone;
	}

	public String getRest_owner() {
		return rest_owner;
	}

	public void setRest_owner(String rest_owner) {
		this.rest_owner = rest_owner;
	}

	public String getRest_email() {
		return rest_email;
	}

	public void setRest_email(String rest_email) {
		this.rest_email = rest_email;
	}

	public String getRest_userName() {
		return rest_userName;
	}

	public void setRest_userName(String rest_userName) {
		this.rest_userName = rest_userName;
	}

	public String getRest_password() {
		return rest_password;
	}

	public void setRest_password(String rest_password) {
		this.rest_password = rest_password;
	}

	public String getRest_url() {
		return rest_url;
	}

	public void setRest_url(String rest_url) {
		this.rest_url = rest_url;
	}

	public double getRest_longitude() {
		return rest_longitude;
	}

	public void setRest_longitude(double rest_longitude) {
		this.rest_longitude = rest_longitude;
	}

	public double getRest_latitude() {
		return rest_latitude;
	}

	public void setRest_latitude(double rest_latitude) {
		this.rest_latitude = rest_latitude;
	}

	public Blob getRest_mainbanner() {
		return rest_mainbanner;
	}

	public void setRest_mainbanner(Blob rest_mainbanner) {
		this.rest_mainbanner = rest_mainbanner;
	}

	public Blob getRest_logo() {
		return rest_logo;
	}

	public void setRest_logo(Blob rest_logo) {
		this.rest_logo = rest_logo;
	}

	public Blob getRest_coverimage() {
		return rest_coverimage;
	}

	public void setRest_coverimage(Blob rest_coverimage) {
		this.rest_coverimage = rest_coverimage;
	}
	
    

	
	
}
