<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
     <jsp:useBean id="notif" class="_09_notification.model.NotificationDAO" scope="session" />
     <c:set target="${notif}" property="username" value="${LoginOK.memberId}"/>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <!--站內訊息-->
                 <div class="massage">
                    <div class="searchBg"></div>
                    <section class="searchContent">
                        <h2>您的提醒訊息</h2>
                        <c:if test="${notif.queryNoticationCountByUserNoRead==0}">
                        	<p style="text-align: center">您現在沒有訊息喔~</p>
                        </c:if>
                        <div class="closeBtn"><i class="icon-close" title="關閉"></i></div>
                        <!--訊息列表 最多5則-->
                        <c:set var="notifList" value="${notif.queryNoticationByUserNoRead}"/>
                        <c:forEach var="notification" items="${notifList}">
                        <jsp:useBean id="restDAO" class="_01_Store_register.model.StoreBeanDAO" scope="page"/>
                        <c:set target="${restDAO}" property="rest_id" value="${notification.rest_id}"/>
                        <c:set var="rest" value="${restDAO.storeById}"/>
                        
                        <div class="massageList">
                            <figure>
                            <a href="${pageContext.servletContext.contextPath}/_07_storePage/getOneRest.do?id=${rest.rest_id}">
                            <img src="${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${rest.rest_name}&type=restaurant&loc=logo" 
                            alt="${rest.rest_name}" title="${rest.rest_name}"></a></figure>
                            <div class="massageInfo">
                                <h3>訂購店家：${rest.rest_name}</h3>
                                <p>訂單編號：<a href="_06_member/order_detail.jsp?ordId=${notification.ord_id}&restName=${rest.rest_name}">${notification.ord_id}</a></p>
                                <p>訊息時間：<fmt:formatDate type = "both"  pattern="yyyy-MM-dd HH:mm" 
                                value ="${notification.noti_time}" /></p>
                                <p>${notification.msg}</p>
                            </div>
                        </div>
                        </c:forEach>
                        <!--訊息列表 最多5則 end-->
                    </section>
                </div>
                <!--站內訊息 end-->


</body>
</html>