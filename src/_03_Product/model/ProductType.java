package _03_Product.model;

import java.io.Serializable;

public class ProductType implements Serializable  {
	
	private String Prod_typeName;
	private String rest_name;
	
	
	public ProductType() {
		super();
	}

	public ProductType(String prod_typeName, String rest_name) {
		super();
		Prod_typeName = prod_typeName;
		this.rest_name = rest_name;
	}

	public String getProd_typeName() {
		return Prod_typeName;
	}

	public void setProd_typeName(String prod_typeName) {
		Prod_typeName = prod_typeName;
	}

	public String getRest_name() {
		return rest_name;
	}

	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}

	@Override
	public String toString() {
		return "ProductType [Prod_typeName=" + Prod_typeName + ", rest_name=" + rest_name + "]";
	}
	
}
