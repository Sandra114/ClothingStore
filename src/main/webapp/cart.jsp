<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Clothing Shop</title>
    <%@include file="WEB-INF/jspf/css.jspf" %>
</head>
<body>
<%@include file="WEB-INF/jspf/top_bar.jspf" %>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <ul class="nav nav-pills nav-stacked">
                        <%--@elvariable id="categories" type="java.util.List"--%>
                        <c:forEach items="${categories}" var="cat">
                            <li role="presentation" class="
                    <c:if test="${param.category != null && param.category != 'null' && param.category==cat.id}">
                        <c:out value="active"/>
                        </c:if>">
                                <a href="<c:url value="items?category=${cat.id}"/>"><c:out
                                        value="${cat.title}"></c:out></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-7">
            <c:choose>
                <c:when test="${sessionScope.cart != null && sessionScope.cart.size != 0}">
                    <jsp:useBean id="cart" scope="session" type="com.github.sandra114.clothingshop.model.Cart"/>
                    <c:forEach var="entry" items="${cart.items}">
                        <c:set var="itemDesc" value="${entry.key.items}"/>

                        <div class="panel panel-default">
                            <div class="panel-body">

                                <div class="row">
                                    <div class="col-md-2">
                                        <a href="items?action=show&id=<c:out value="${itemDesc.id}"/>">
                                            <img alt="placeholder"
                                                 src="<c:out value="photos/${itemDesc.photo}"/>"
                                                 data-holder-rendered="true"
                                                 style="height: 100px; width: 100px; display: block;">
                                        </a>
                                    </div>

                                    <div class="col-md-6">
                                        <h5><c:out value="${itemDesc.title}"/></h5>
                                        <h5>Пол:
                                            <c:out value="${itemDesc.gender}"/></h5>
                                        <h5> Выбранный размер:
                                            <span class="label label-success"><c:out value="${entry.key.size}"/></span>
                                        </h5>
                                    </div>

                                    <div class="col-md-4 text-right right-block">
                                        Цена: <c:out value="${itemDesc.price} Руб"/>
                                        <form class="form-horizontal" action="cart" method="post">
                                            <div class="btn-group right-block">
                                                <button type="submit" name="cartAction"
                                                        <c:if test="${entry.value == 0}">
                                                            disabled="disabled"
                                                        </c:if>
                                                        value="m<c:out value="${entry.key.id}"/>"
                                                        class="btn btn-default glyphicon glyphicon-minus">
                                                </button>
                                                <button
                                                        class="btn alert-info glyphicon" readonly=""><c:out
                                                        value="${entry.value}"/>
                                                </button>
                                                <button type="submit" name="cartAction"
                                                        value="p<c:out value="${entry.key.id}"/>"
                                                        class="btn btn-default glyphicon glyphicon-plus">
                                                </button>
                                            </div>
                                            <button type="submit" name="cartAction"
                                                    value="d<c:out value="${entry.key.id}"/>"
                                                    class="btn btn-danger glyphicon glyphicon-remove"></button>
                                        </form>
                                        <h3><span class="label label-primary">Cумма: <c:out
                                                value="${itemDesc.price * entry.value} Руб"/></span></h3>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>

                    <div class="row">
                        <div class="col-md-12 ">
                            <h2 class="text-right">
                                <span class="label label-primary">Игого: <c:out value="${cart.totalPrice}"/> руб.</span>
                            </h2>
                            <h2 class="text-right">
                                <a href="one_click.jsp" class="btn btn-danger">Оформить заказ</a>
                            </h2>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <div class="col-md-12 ">
                            <div class="panel panel-default panel-body">
                                <h3>Корзина скорее пуста, чем полна</h3>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@include file="WEB-INF/jspf/scripts.jspf" %>
</body>
</html>