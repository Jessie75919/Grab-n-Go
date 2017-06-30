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
			List<OrderItemBean> newList = new ArrayList<>();
			newList.add(odb);
			shoppingCart.put(prod_Id, newList);
		}else{
			List<OrderItemBean> oldList = shoppingCart.get(prod_Id);
			 /*if the amount of pro_id is the only one*/
//			if(al.size()==0){
//				List<OrderItemBean> a = new ArrayList<>();
//				a.add(odb);
//				shoppingCart.put(prod_Id, a);
//			}
//			else{
				/*if the note is the same,put them together */
				int[] result = isExist(odb,oldList);
				// result[0] : 0 == 不存在 ; 1== 已存在
				if(result[0]==0){
					oldList.add(odb);
					shoppingCart.put(prod_Id, oldList);
				}else{
					oldList.get(result[1]).setItem_amount(oldList.get(result[1]).getItem_amount()+odb.getItem_amount());
					
				}
			
//				for(OrderItemBean oldOdb : oldList){
//					if(oldOdb.getItem_note().equals(odb.getItem_note())){
//						oldOdb.setItem_amount(oldOdb.getItem_amount()+odb.getItem_amount());
//					}else{
//						break;
//					}
//				}
//				oldList.add(odb);
//				shoppingCart.put(prod_Id, oldList);
//			}
		}
	}
	
	public int[] isExist(OrderItemBean newOid,List<OrderItemBean> oldList){
		int[] result = new int[2];
		
		for(int i=0;i<oldList.size();i++){
			if(oldList.get(i).getItem_note().equals(newOid.getItem_note())){
				result[0] = 1;
				result[1] = i;
				break;
			}else{
				result[0] = 0;
			}
		}
		
		return result;
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
