package _04_shoppingCart.model;

import java.util.LinkedHashMap;
import java.util.Map;

import _05_orderProcess.model.OrderItemBean;

public class ShoppingCart {
	
	private Map <Integer,OrderItemBean> cart = new LinkedHashMap<>();
	
	public ShoppingCart() {
		// TODO Auto-generated constructor stub
	}
	
	public Map<Integer,OrderItemBean> getContent(){
		return cart;
	}
	
	public void addToCart(int prod_Id,OrderItemBean odb){
		
		if(cart.get(prod_Id)==null){
			cart.put(prod_Id, odb);
		}else{
			OrderItemBean old_odb = cart.get(prod_Id);
			/*if the note is the same,put them together */ 
			if(old_odb.getItem_note().equals(odb.getItem_note())){
				old_odb.setItem_amount
					(old_odb.getItem_amount()+odb.getItem_amount());
				old_odb.setItem_note(old_odb.getItem_note()+odb.getItem_note());
				
			/*the same prod_Id with differenet note */
			}else{ 
				cart.put(prod_Id, odb);
			}
		}
	}
	
	public boolean modifyAmount(int prod_Id,OrderItemBean odb){
		boolean result = false;
		if(cart.get(prod_Id)!=null){
			cart.put(prod_Id, odb);
			result = true;
		}
		return result;
	}
	
	public boolean modifyAmount(int prod_Id,int newAmount){
		boolean result = false;
		if(cart.get(prod_Id)!=null){
			OrderItemBean oib = cart.get(prod_Id);
			oib.setItem_amount(newAmount);
			result = true;
		}
		return result;
	}
	
	public int deleteItem(int serialNo,int prod_Id){
		int n = -1;
		if(cart.get(prod_Id)!=null){
			
		}
		
		
		return n;
		
	}
	
}
