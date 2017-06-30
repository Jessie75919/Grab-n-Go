package _04_shoppingCart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _05_orderProcess.model.OrderItemBean;

public class ShoppingCart {
	
	private Map <Integer,List<OrderItemBean>> shoppingCart = new HashMap<>();
	
	public ShoppingCart() {
		// TODO Auto-generated constructor stub
	}
	
	public Map<Integer,List<OrderItemBean>> getContent(){
		return shoppingCart;
	}
	
	public void addToCart(int prod_Id,OrderItemBean odb){
		
		if(shoppingCart.get(prod_Id)==null){
			List<OrderItemBean> al = new ArrayList<>();
			al.add(odb);
			shoppingCart.put(prod_Id, al);
		}else{
			List<OrderItemBean> al = shoppingCart.get(prod_Id);
			 /*if the amount of pro_id is the only one*/
			if(al.size()==0){
				List<OrderItemBean> a = new ArrayList<>();
				a.add(odb);
				shoppingCart.put(prod_Id, a);
			}else{
				/*if the note is the same,put them together */ 
				for(OrderItemBean oldOdb : al){
					if(oldOdb.getItem_note().equals(odb.getItem_note())){
						oldOdb.setItem_amount(oldOdb.getItem_amount()+odb.getItem_amount());
					}
				}
			}
		}
	}
	/*
	public boolean modifyAmount(int prod_Id,OrderItemBean odb){
		boolean result = false;
		if(shoppingCart.get(prod_Id)!=null){
			shoppingCart.put(prod_Id, odb);
			result = true;
		}
		return result;
	}
	
	public boolean modifyAmount(int prod_Id,int newAmount){
		boolean result = false;
		if(shoppingCart.get(prod_Id)!=null){
			OrderItemBean oib = shoppingCart.get(prod_Id);
			oib.setItem_amount(newAmount);
			result = true;
		}
		return result;
	}
	
	public int deleteItem(int serialNo,int prod_Id){
		int n = -1;
		if(shoppingCart.get(prod_Id)!=null){
			
		}
		
		
		return n;
		
	}*/
	
}
