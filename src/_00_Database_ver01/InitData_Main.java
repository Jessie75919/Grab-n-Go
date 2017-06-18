package _00_Database_ver01;

public class InitData_Main {

	public static void main(String[] args) {
		TableDAO dao = new TableDAO();
		dao.insertRestaurantType();
		dao.insertMemberTable();
		dao.insertRestaurantTable();
		dao.insertProductType();
	}

}
