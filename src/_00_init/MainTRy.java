package _00_init;

public class MainTRy {

	public static void main(String[] args) {

		String a = "abc";
		String afterEcry =  GlobalService.encryptString(a);
		String afterMD5 = GlobalService.getMD5Endocing(afterEcry);
		System.out.println(afterMD5);

	}

}
