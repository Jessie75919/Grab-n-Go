package _07_Rating.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _05_orderProcess.model.OrderBean;
import _05_orderProcess.model.OrderDAO;

public class RestRatingBeanDAO {
	private DataSource ds;
	
	private int rest_Id ;

	public int getRest_Id() {
		return rest_Id;
	}

	public void setRest_Id(int rest_Id) {
		this.rest_Id = rest_Id;
	}

	public RestRatingBeanDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public int insertRestEva(RestRatingBean rrb) {
		String sqlRestEva = "insert into rest_evaluate values(?,?,?,?,?) ";
		String updateOrderEva = "update order01 set ord_evalued = 1 where ord_id = ?";
		int x = -1;
		try (	Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sqlRestEva);
				) {
			con.setAutoCommit(false);
			try {
				pst.setInt(1, rrb.getOrd_id());
				pst.setInt(2, rrb.getRest_id());
				pst.setInt(3, rrb.getRestEva_star());
				pst.setString(4, rrb.getM_username());
				pst.setString(5, rrb.getRestEva_comment());
				int n = pst.executeUpdate();

				if (n == 1) {
					System.out.println("評價新增成功");
				} else {
					System.out.println("評價新增失敗");
				}
				
				PreparedStatement pst2 = con.prepareStatement(updateOrderEva);
				pst2.setInt(1, rrb.getOrd_id());

				int k = pst2.executeUpdate();
				if (k == 1) {
					System.out.println("訂單評價更新成功");
				} else {
					System.out.println("訂單評價更新失敗");
				}
				
				if(k==1 && n==1) {
					x = 1;
					con.commit();
					con.setAutoCommit(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
				if(con!=null){
					con.rollback();
					System.out.println("評價過程錯誤");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	

	public List<RestRatingBean> getAllRestEvaByRestId(int rest_id) {
		String sql = "select * from rest_evaluate where rest_id = ?";
		List<RestRatingBean> list = new ArrayList<>();
		int count = 0;
		double total = 0;
		int score = 0;
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, rest_id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				RestRatingBean rrb = new RestRatingBean(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
						rs.getString(5));
				list.add(rrb);
			}
			if (list.size() == 0) {
				System.out.println("get the evalue data");
			} else {
				System.out.println("get Nothing");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Integer> getAllRestEvaOrderIdByUser(String username) {
		String sql = "select ord_id from rest_evaluate where m_username = ?";
		List<Integer> listEvalued = new ArrayList<>();
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				listEvalued.add(rs.getInt(1));
			}

			if (listEvalued.size() == 0) {
				System.out.println("get Nothing ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEvalued;

	}

	public List<Integer> getOrderIdNotEvalued(String username) {

		OrderDAO ordDAO = new OrderDAO();
		// 以使用者帳號抓取所有的訂單編號
		Collection<OrderBean> ordList = ordDAO.getMemberOrders();
		List<Integer> ordListNotEva = new ArrayList<>();
		for (OrderBean ob : ordList) {
			// 判斷是否有存在評價過的訂單編號中，如果沒有就塞入ordListNotEva
			if (!isExist(ob.getOrd_id(), username)) {
				ordListNotEva.add(ob.getOrd_id());
			}
		}
		return ordListNotEva;
	}

	private boolean isExist(int ord_id, String username) {
		boolean result = true;
		// 以使用者帳號抓取所有評價過的訂單編號
		List<Integer> ordIdEvalued = getAllRestEvaOrderIdByUser(username);
		for (Integer ordIdEva : ordIdEvalued) {
			if (ord_id != ordIdEva) {
				result = false;
			} else {
				result = true;
				break;
			}
		}

		return result;
	}

	public int getRestEva() {
		String sql = "select * from rest_evaluate where rest_id = ?";
		int count = 0;
		double total = 0;
		int score = 0;
//		System.out.println("rest_id = " + rest_Id );
		try (Connection con = ds.getConnection(); 
				PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, rest_Id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				count++;
				int restEva_star = rs.getInt("restEva_star");
//				System.out.println("restEva_star"+restEva_star);
				total += restEva_star;
			}
//			System.out.println("count = "+count);
//			System.out.println("total = "+total);

			score = (int) Math.round(total / count);
//			System.out.println("get the evalue data");
				
		} catch (Exception e) {
			System.out.println("get wrong in getRestEva");
			e.printStackTrace();
		}
		return score;
	}

}
