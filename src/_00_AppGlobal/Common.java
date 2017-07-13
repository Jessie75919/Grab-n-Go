package _00_AppGlobal;

public class Common {
	public final static String getRestWithLoc = 
			 "  SELECT a.rest_id , a.rest_type , a.rest_name , a.rest_branch , a.rest_address , a.rest_phone, "
			+ " a.rest_owner , a.rest_email , a.rest_username , a.rest_password , a.rest_url , a.rest_longitude , "
			+ "  a.rest_latitude , a.rest_mainbanner , a.rest_logo , a.rest_coverimage , a.rest_validate , "
			+ " 111.111 * " + " DEGREES(ACOS(COS(RADIANS(a.rest_latitude)) " + " * COS(RADIANS(?)) "
			+ "  * COS(RADIANS(a.rest_longitude - ?)) " + " + SIN(RADIANS(a.rest_latitude)) "
			+ " * SIN(RADIANS(?)))) AS distance_in_km " + " FROM restaurant AS a " + " HAVING distance_in_km < 1; ";
}
