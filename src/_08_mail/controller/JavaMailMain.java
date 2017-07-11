package _08_mail.controller;

import java.util.Arrays;
import java.util.List;

public class JavaMailMain {

	public static void main(String[] args) {
		String from = "apriori304@gmail.com";
		List<String> to = Arrays.asList(
				   new String[]{"grabngojava@gmail.com", "jessie75919@gmail.com"});
		List<String> cc = Arrays.asList(
				   new String[]{"xpt12591@gmail.com", "apriori306@gmail.com"});
		List<String> bcc = Arrays.asList(
				   new String[]{"teehorng14q1@gmail.com"});
		String subject = "歡迎加入會員";
		String text = 
		  "<h1>謝謝您加入會員</h1>" +
		  "<h2>您可以按下列連結感受最新的體驗</h2>" +
	      "<a href='http://tw.yahoo.com'>yahoo</a><br>" +
	      "<a href='http://www.google.com'>google</a><br>" +
          "<br><br><font color='blue'> 再次感謝, </font><br>工作小組敬上";
		List<String> attachment = Arrays.asList(
				   new String[]{"C:\\_JSP\\maven3\\README.txt", 
						   "D:\\_Java\\fs.jpg",
						   "D:\\_Java\\autumn_fs.jpg",
						   });;
		JavaMailUtil  util = new JavaMailUtil(from, to, cc, bcc, subject, text ,attachment);
		if (util.send()){
		   System.out.println("發信成功");
		} else {
		   System.out.println("發信失敗");
		}
	}

}
