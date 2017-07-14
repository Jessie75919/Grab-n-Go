<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-md-3">
		<center>
			<!-- <img class="img-rounded" src="../images/restImage/af_logo.jpg"> -->
			<a
				href="${pageContext.servletContext.contextPath}/_07_storePage/getOneRest.do?id=${StoreLoginOK['rest_id']}">
				<img
				src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${StoreLoginOK["rest_username"]}&type=restaurant&loc=logo'
				alt="Photo" title="Photo">
			</a> <br>
		</center>
	</div>

</body>
</html>