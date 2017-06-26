package _01_Store_register.model;

import java.io.Serializable;
import java.sql.Blob;

public class StoreBean implements Serializable {

	private int rest_id;
	private String rest_type;
	private String rest_name;
	private String rest_branch;
	private String rest_address;
	private String rest_phone;
	private String rest_owner;
	private String rest_email;
	private String rest_username;
	private String rest_password;
	private String rest_url;
	private double rest_longitude;
	private double rest_latitude;
	private Blob rest_mainbanner;
	private Blob rest_logo;
	private Blob rest_coverimage;
	private boolean rest_validate;
	private double distance;

	public StoreBean() {
		super();
	}

	/*---------------------------------------------------------------------------------
	 * */

	public StoreBean(int rest_id, String rest_type, String rest_name, String rest_branch, String rest_address,
			String rest_phone, String rest_owner, String rest_email, String rest_username, String rest_password,
			String rest_url) {
		super();
		this.rest_id = rest_id;
		this.rest_type = rest_type;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_owner = rest_owner;
		this.rest_email = rest_email;
		this.rest_username = rest_username;
		this.rest_password = rest_password;
		this.rest_url = rest_url;
	}

	/*---------------------------------------------------------------------------------
	 * */

	public StoreBean(int rest_id, String rest_type, String rest_name, String rest_branch, String rest_address,
			String rest_phone, String rest_owner, String rest_email, String rest_username, String rest_password,
			String rest_url, double rest_longitude, double rest_latitude, Blob rest_mainbanner, Blob rest_logo,
			Blob rest_coverimage) {
		super();
		this.rest_id = rest_id;
		this.rest_type = rest_type;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_owner = rest_owner;
		this.rest_email = rest_email;
		this.rest_username = rest_username;
		this.rest_password = rest_password;
		this.rest_url = rest_url;
		this.rest_longitude = rest_longitude;
		this.rest_latitude = rest_latitude;
		this.rest_mainbanner = rest_mainbanner;
		this.rest_logo = rest_logo;
		this.rest_coverimage = rest_coverimage;
	}

	/*---------------------------------------------------------------------------------
	 * */

	public StoreBean(String rest_type, String rest_name, String rest_branch, String rest_address, String rest_phone,
			String rest_owner, String rest_email, String rest_username, String rest_password, String rest_url,
			double rest_longitude, double rest_latitude, Blob rest_mainbanner, Blob rest_logo, Blob rest_coverimage) {
		super();
		this.rest_type = rest_type;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_owner = rest_owner;
		this.rest_email = rest_email;
		this.rest_username = rest_username;
		this.rest_password = rest_password;
		this.rest_url = rest_url;
		this.rest_longitude = rest_longitude;
		this.rest_latitude = rest_latitude;
		this.rest_mainbanner = rest_mainbanner;
		this.rest_logo = rest_logo;
		this.rest_coverimage = rest_coverimage;
	}

	public StoreBean(String rest_username, String rest_address, String rest_phone, String rest_email,
			String rest_password, String rest_url) {
		super();
		this.rest_username = rest_username;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_email = rest_email;
		this.rest_password = rest_password;
		this.rest_url = rest_url;
	}

	public StoreBean(String rest_type, String rest_name, String rest_branch, String rest_address, String rest_phone,
			String rest_owner, String rest_email, String rest_username, String rest_password, String rest_url,
			double rest_longitude, double rest_latitude) {
		super();
		this.rest_type = rest_type;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_owner = rest_owner;
		this.rest_email = rest_email;
		this.rest_username = rest_username;
		this.rest_password = rest_password;
		this.rest_url = rest_url;
		this.rest_longitude = rest_longitude;
		this.rest_latitude = rest_latitude;
	}

	public StoreBean(String rest_name, String rest_branch, Blob rest_logo, boolean rest_validate) {
		super();
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_logo = rest_logo;
		this.rest_validate = rest_validate;
	}

	/*---------------------------------------------------------------------------------
	 * */

	// 含有rest_validate

	public StoreBean(int rest_id, String rest_type, String rest_name, String rest_branch, String rest_address,
			String rest_phone, String rest_owner, String rest_email, String rest_username, String rest_password,
			String rest_url, double rest_longitude, double rest_latitude, Blob rest_mainbanner, Blob rest_logo,
			Blob rest_coverimage, boolean rest_validate) {
		super();
		this.rest_id = rest_id;
		this.rest_type = rest_type;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_owner = rest_owner;
		this.rest_email = rest_email;
		this.rest_username = rest_username;
		this.rest_password = rest_password;
		this.rest_url = rest_url;
		this.rest_longitude = rest_longitude;
		this.rest_latitude = rest_latitude;
		this.rest_mainbanner = rest_mainbanner;
		this.rest_logo = rest_logo;
		this.rest_coverimage = rest_coverimage;
		this.rest_validate = rest_validate;
	}

	public StoreBean(int rest_id, String rest_type, String rest_name, String rest_branch, String rest_address,
			String rest_phone, String rest_owner, String rest_email, String rest_username, String rest_password,
			String rest_url, double rest_longitude, double rest_latitude, boolean rest_validate) {
		super();
		this.rest_id = rest_id;
		this.rest_type = rest_type;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_owner = rest_owner;
		this.rest_email = rest_email;
		this.rest_username = rest_username;
		this.rest_password = rest_password;
		this.rest_url = rest_url;
		this.rest_longitude = rest_longitude;
		this.rest_latitude = rest_latitude;
		this.rest_validate = rest_validate;
	}

	public StoreBean(int rest_id, String rest_type, String rest_name, String rest_branch, String rest_address,
			String rest_phone, String rest_owner, String rest_email, String rest_username, String rest_password,
			String rest_url, boolean rest_validate) {
		super();
		this.rest_id = rest_id;
		this.rest_type = rest_type;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_owner = rest_owner;
		this.rest_email = rest_email;
		this.rest_username = rest_username;
		this.rest_password = rest_password;
		this.rest_url = rest_url;
		this.rest_validate = rest_validate;
	}

	public StoreBean(String rest_type, String rest_name, String rest_branch, String rest_address, String rest_phone,
			String rest_owner, String rest_email, String rest_username, String rest_password, String rest_url,
			double rest_longitude, double rest_latitude, Blob rest_mainbanner, Blob rest_logo, Blob rest_coverimage,
			boolean rest_validate) {
		super();
		this.rest_type = rest_type;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_owner = rest_owner;
		this.rest_email = rest_email;
		this.rest_username = rest_username;
		this.rest_password = rest_password;
		this.rest_url = rest_url;
		this.rest_longitude = rest_longitude;
		this.rest_latitude = rest_latitude;
		this.rest_mainbanner = rest_mainbanner;
		this.rest_logo = rest_logo;
		this.rest_coverimage = rest_coverimage;
		this.rest_validate = rest_validate;
	}

	public StoreBean(String rest_type, String rest_name, String rest_branch, String rest_address, String rest_phone,
			String rest_owner, String rest_email, String rest_username, String rest_password, String rest_url,
			double rest_longitude, double rest_latitude, boolean rest_validate) {
		super();
		this.rest_type = rest_type;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_owner = rest_owner;
		this.rest_email = rest_email;
		this.rest_username = rest_username;
		this.rest_password = rest_password;
		this.rest_url = rest_url;
		this.rest_longitude = rest_longitude;
		this.rest_latitude = rest_latitude;
		this.rest_validate = rest_validate;
	}

	public StoreBean(String rest_type, String rest_name, String rest_branch, String rest_address, String rest_phone,
			String rest_owner, String rest_email, String rest_username, String rest_password, String rest_url,
			boolean rest_validate) {
		super();
		this.rest_type = rest_type;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_owner = rest_owner;
		this.rest_email = rest_email;
		this.rest_username = rest_username;
		this.rest_password = rest_password;
		this.rest_url = rest_url;
		this.rest_validate = rest_validate;
		
	}
	
	
	/*used  <StroeBeanDAO>  getStoreFromUser()*/
	public StoreBean(String rest_type, String rest_name, String rest_branch, String rest_address, String rest_phone,
			String rest_owner, String rest_email, String rest_username, String rest_password, String rest_url,
			boolean rest_validate,double distance) {
		super();
		this.rest_type = rest_type;
		this.rest_name = rest_name;
		this.rest_branch = rest_branch;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_owner = rest_owner;
		this.rest_email = rest_email;
		this.rest_username = rest_username;
		this.rest_password = rest_password;
		this.rest_url = rest_url;
		this.rest_validate = rest_validate;
		this.distance = distance;
	}

	/*---------------------------------------------------------------------------------
	 * */

	public int getRest_id() {
		return rest_id;
	}

	public void setRest_id(int rest_id) {
		this.rest_id = rest_id;
	}

	public String getRest_type() {
		return rest_type;
	}

	public void setRest_type(String rest_type) {
		this.rest_type = rest_type;
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

	public boolean isRest_validate() {
		return rest_validate;
	}

	public void setRest_validate(boolean rest_validate) {
		this.rest_validate = rest_validate;
	}

	@Override
	public String toString() {
		return "StoreBean [rest_id=" + rest_id + ", rest_type=" + rest_type + ", rest_name=" + rest_name
				+ ", rest_branch=" + rest_branch + ", rest_address=" + rest_address + ", rest_phone=" + rest_phone
				+ ", rest_owner=" + rest_owner + ", rest_email=" + rest_email + ", rest_username=" + rest_username
				+ ", rest_password=" + rest_password + ", rest_url=" + rest_url + ", rest_longitude=" + rest_longitude
				+ ", rest_latitude=" + rest_latitude + ", rest_mainbanner=" + rest_mainbanner + ", rest_logo="
				+ rest_logo + ", rest_coverimage=" + rest_coverimage + ", rest_validate=" + rest_validate + "]";
	}

}
