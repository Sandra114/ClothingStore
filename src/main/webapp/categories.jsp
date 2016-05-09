<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html>
<head>
    <title>Clothing Shop</title>
    <%@include file="WEB-INF/jspf/css.jspf" %>
</head>
<body>
<%@include file="WEB-INF/jspf/top_bar.jspf" %>
<div class="container">
    <div class="row">
        <div class="col-md-offset-2 col-md-8">
            <ul class="nav nav-pills nav-stacked">
                <c:forEach items="${categories}" var="cat">
                    <li role="presentation">
                        <a href="<c:url value="items?category=${cat.id}"/>"><c:out value="${cat.title}"></c:out></a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<%@include file="WEB-INF/jspf/scripts.jspf" %>
</body>
</html>