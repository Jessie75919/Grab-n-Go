package _03_Product.model;

import java.io.InputStream;
import java.util.List;

public interface ProductInterface {
	public int insertProduct(Product prod,InputStream is);
	public int updateProduct(Product prod,InputStream is);
	public int deleteProduct(int id);
	public Product queryProduct(int id);
	public List<Product> queryAllProduct(int rest_id, String typeName);

}
