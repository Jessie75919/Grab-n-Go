package _03_Product.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;

public class Product implements Serializable{
	
	private int prod_id; 
	private int rest_id; 
	private String type_name; 
	private String prod_name; 
	private int prod_price; 
	private String prod_desc; 
	private Blob prod_img; 
	private String prod_filename;
	
	
	public Product(int prod_id, int rest_id, String type_name, String prod_name, int prod_price, String prod_desc,
			Blob prod_img, String prod_filename) {
		super();
		this.prod_id = prod_id;
		this.rest_id = rest_id;
		this.type_name = type_name;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_desc = prod_desc;
		this.prod_img = prod_img;
		this.prod_filename = prod_filename;
	}

	public Product(int rest_id, String type_name, String prod_name, int prod_price, String prod_desc, 
			String prod_filename) {
		super();
		this.rest_id = rest_id;
		this.type_name = type_name;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_desc = prod_desc;
		this.prod_filename = prod_filename;
	}
	
	public Product(int prod_id,int rest_id, String type_name, String prod_name, int prod_price, String prod_desc, 
			String prod_filename) {
		super();
		this.prod_id = prod_id;
		this.rest_id = rest_id;
		this.type_name = type_name;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_desc = prod_desc;
		this.prod_filename = prod_filename;
	}

	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

	public int getRest_id() {
		return rest_id;
	}

	public void setRest_id(int rest_id) {
		this.rest_id = rest_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getProd_price() {
		return prod_price;
	}

	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}

	public String getProd_desc() {
		return prod_desc;
	}

	public void setProd_desc(String prod_desc) {
		this.prod_desc = prod_desc;
	}

	public Blob getProd_img() {
		return prod_img;
	}

	public void setProd_img(Blob prod_img) {
		this.prod_img = prod_img;
	}

	public String getProd_filename() {
		return prod_filename;
	}

	public void setProd_filename(String prod_filename) {
		this.prod_filename = prod_filename;
	}

	
	@Override
	public String toString() {
		return "Product [產品id=" + prod_id + ", 餐廳id=" + rest_id + ", 餐點分類 =" + type_name + ", 餐點名稱="
				+ prod_name + ", 餐點價格=" + prod_price + ", 餐點描述=" + prod_desc + ", 餐點圖片=" + prod_img
				+ ", 圖片檔名=" + prod_filename + "]";
	} 
	
	
	
	
}
