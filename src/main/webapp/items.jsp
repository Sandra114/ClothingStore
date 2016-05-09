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
        <div class="col-md-2">
            <ul class="nav nav-pills nav-stacked">
                <c:forEach items="${categories}" var="cat">
                    <li role="presentation" class="
                    <c:if test="${param.category != null && param.category==cat.id}">
                        <c:out value="active"/>
                    </c:if>">
                        <a href="<c:url value="items?category=${cat.id}"/>"><c:out value="${cat.title}"></c:out></a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-8">
            <c:forEach items="${itemDescriptions}" var="itemDesc">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <ol class="breadcrumb">
                                    <li><a href="items">Все товары</a></li>
                                    <li>
                                        <a href="items?category=<c:out value="${itemDesc.category.id}"/>">
                                            <c:out value="${itemDesc.category.title}"/>
                                        </a>
                                    </li>
                                    <li class=" active">
                                        <a href="items?action=show&id=<c:out value="${itemDesc.id}"/>">
                                            <c:out value="${itemDesc.title}"/>
                                        </a>
                                    </li>
                                </ol>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">
                                <a href="items?action=show&id=<c:out value="${itemDesc.id}"/>">
                                    <img alt="placeholder"
                                         src="<c:out value="photos/${itemDesc.photo}"/>"
                                         data-holder-rendered="true"
                                         style="height: 100px; width: 100px; display: block;">
                                </a>
                            </div>

                            <div class="col-md-7">
                                <a href="items?action=show&id=<c:out value="${itemDesc.id}"/>">
                                    <h4><c:out value="${itemDesc.title}"/></h4>
                                </a>
                                Пол:
                                <a href="items?gender=<c:out value="${itemDesc.gender}"/>">
                                    <c:out value="${itemDesc.gender}"/>
                                </a>
                            </div>

                            <div class=" col-md-3 text-right">
                                <a class="btn btn-default"
                                   href="items?action=show&id=<c:out value="${itemDesc.id}"/>">
                                    <span class="glyphicon glyphicon-shopping-cart text-primary"
                                          aria-hidden="true"></span>
                                    <c:out value="${itemDesc.price} Руб"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="WEB-INF/jspf/scripts.jspf" %>
</body>
</html>