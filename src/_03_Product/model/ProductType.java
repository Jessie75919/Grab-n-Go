package _03_Product.model;

import java.io.Serializable;

public class ProductType implements Serializable  {
	
	
	private String prod_typeName;
	private String rest_name;
	private int prod_type_no;
	
	
	public ProductType() {
		super();
	}

	public ProductType(String prod_typeName, String rest_name) {
		super();
		this.prod_typeName = prod_typeName;
		this.rest_name = rest_name;
	}
	
	public ProductType(String prod_typeName, String rest_name,int prod_type_no) {
		super();
		this.prod_typeName = prod_typeName;
		this.rest_name = rest_name;
		this.prod_type_no = prod_type_no;
	}
	
	

	public ProductType(int prod_type_no, String prod_typeName) {
		super();
		this.prod_type_no = prod_type_no;
		this.prod_typeName = prod_typeName;
	}

	public String getProd_typeName() {
		return prod_typeName;
	}

	public void setProd_typeName(String prod_typeName) {
		this.prod_typeName = prod_typeName;
	}

	public String getRest_name() {
		return rest_name;
	}

	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}
	

	public int getProd_type_no() {
		return prod_type_no;
	}

	public void setProd_type_no(int prod_type_no) {
		this.prod_type_no = prod_type_no;
	}

	@Override
	public String toString() {
		return "ProductType [Prod_typeName=" + prod_typeName + ", rest_name=" + rest_name + "]";
	}
	
}
