package _03_Product.model;

import java.io.InputStream;
import java.util.List;

public interface ProductInterface {
	public int insertProduct(Product prod,InputStream is);
	public int updateProduct(Product prod, InputStream is ,int mode) ;
	public int deleteProduct(int prod_Id);
	public List<Product> queryProducts(int rest_id, String typeName);

}
