package _01_Store_register.model;

import java.sql.Blob;

public class StoreBean {
	private int rest_id;
	private String rest_typeId;
	private String rest_name;
	private String rest_branch;
	private String rest_address;
	private String rest_phone;
	private String rest_owner;
	private String rest_email;
	private String rest_username;
	private String rest_passward;
	private String rest_url;
	private float rest_longitude;
	private float rest_latitude;
	private Blob rest_mainbanner;
	private Blob rest_logo;
	private Blob rest_coverimage;
	
	
	public StoreBean() {
		super();
	}

	public StoreBean(String rest_typeId, String rest_name, String rest_branch, String rest_address, String rest_phone,
			String rest_owner, String rest_email, String rest_username, String rest_passward, String rest_url,
			float rest_longitude, float rest_latitude, Blob rest_mainbanner, Blob rest_logo, Blob rest_coverimage) {
		super();
		this.rest_typeId = rest_typeId;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_owner = rest_owner;
		this.rest_email = rest_email;
		this.rest_username = rest_username;
		this.rest_passward = rest_passward;
		this.rest_url = rest_url;
		this.rest_longitude = rest_longitude;
		this.rest_latitude = rest_latitude;
		this.rest_mainbanner = rest_mainbanner;
		this.rest_logo = rest_logo;
		this.rest_coverimage = rest_coverimage;
	}

	public int getRest_id() {
		return rest_id;
	}

	public void setRest_id(int rest_id) {
		this.rest_id = rest_id;
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

	public String getRest_username() {
		return rest_username;
	}

	public void setRest_username(String rest_username) {
		this.rest_username = rest_username;
	}

	public String getRest_passward() {
		return rest_passward;
	}

	public void setRest_passward(String rest_passward) {
		this.rest_passward = rest_passward;
	}

	public String getRest_url() {
		return rest_url;
	}

	public void setRest_url(String rest_url) {
		this.rest_url = rest_url;
	}

	public float getRest_longitude() {
		return rest_longitude;
	}

	public void setRest_longitude(float rest_longitude) {
		this.rest_longitude = rest_longitude;
	}

	public float getRest_latitude() {
		return rest_latitude;
	}

	public void setRest_latitude(float rest_latitude) {
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

	@Override
	public String toString() {
		return "StoreBean [rest_typeId=" + rest_typeId + ", rest_name=" + rest_name + ", rest_email=" + rest_email
				+ ", rest_username=" + rest_username + ", rest_passward=" + rest_passward + "]";
	}
	
	
	
	
	
	
}
