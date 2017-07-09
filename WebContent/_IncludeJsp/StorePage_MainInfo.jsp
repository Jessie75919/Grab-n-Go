<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set target="${rating}" property="rest_Id" value="${clickRest.rest_id}" />
<jsp:useBean id="order" class="_05_orderProcess.model.OrderDAO" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="insideTitle">

                <figure>
                    <img src="${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${clickRest.rest_username}&type=restaurant&loc=main" alt="店家資訊" title="店家資訊">
                </figure>
                <!--店家資訊-->
                <div class="storeTitle">
                    <div class="storeLogo"><img src="${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${clickRest.rest_username}&type=restaurant&loc=logo" alt="${clickRest.rest_name}" title="${clickRest.rest_name}"></div>
                    <div class="storeRight">
                        <h3>${clickRest.rest_name}<span><c:if test="${clickRest.rest_branch!='null'}">${clickRest.rest_branch}</c:if></span></h3>
                        <div class="star">
                            <c:set var="x" value="${5-rating.restEva}" />
                            <c:forEach begin="1" end="${rating.restEva}" step="1">
                                <i class="icon-star on"></i>
                            </c:forEach>
                            <c:forEach begin="1" end="${x}" step="1">
                                <i class="icon-star"></i>
                            </c:forEach>
                        </div>
                        <div class="commentBtn"><a href="storeCommentA.jsp">查看商家評語 (<span>${rating.allRestEvaCountByRestId}</span>)</a></div>
                        <p><i class="icon-location"></i>${clickRest.rest_address}</p>
                        <p><i class="icon-phone"></i>${clickRest.rest_phone}</p>
                        <div class="tagOrder">
                            <div class="tag"><span>${clickRest.rest_type}</span></div>
                            <c:set target="${order}" property="restId" value="${clickRest.rest_id}"/>
                            <div class="orderCount">目前訂單<span>${order.orderInProgressCountByRestId}</span></div>
                        </div>
                    </div>
                </div>
                <!--店家資訊 end-->
            </div>


</body>
</html>