<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <%--@elvariable id="order" type="com.github.sandra114.clothingshop.model.Order"--%>
            <%--@elvariable id="orders" type="java.util.List"--%>
            <c:forEach var="order" items="${orders}">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <c:choose>
                            <c:when test="${order.status == 'Отправлен'}">
                                <c:set var="labelType" value="danger"/>
                            </c:when>
                            <c:when test="${order.status == 'Отменен'}">
                                <c:set var="labelType" value="warning"/>
                            </c:when>
                            <c:when test="${order.status == 'Исполнен'}">
                                <c:set var="labelType" value="success"/>
                            </c:when>
                        </c:choose>
                        Заказ <span class="text-danger">№<c:out value="${order.id}"/></span>
                        Дата заказа: <fmt:formatDate type="date" value="${order.date}"/>
                        Статус: <span class="label label-<c:out value="${labelType}"/>"><c:out
                            value="${order.status}"/></span>
                    </div>
                    <div class="panel-body">
                        Клиент: <c:out value='${order.client.firstName} ${order.client.lastName}'/><br>
                        Телефон: <c:out value='${order.client.phone}'/><br>
                        Адрес доставки: <c:out value='${order.client.address}'/>
                        <div class="panel panel-default">
                            <table class="table table-striped">
                                <tr>
                                    <th>Товар</th>
                                    <th>Размер</th>
                                    <th>Цена</th>
                                    <th>Кол-во</th>
                                    <th>Сумма</th>
                                </tr>
                                <c:forEach var="orderItem" items="${order.orders}">
                                    <tr>
                                        <td><c:out value="${orderItem.size.items.title}"/></td>
                                        <td><c:out value="${orderItem.size.size}"/></td>
                                        <td><c:out value="${orderItem.size.items.price}"/></td>
                                        <td><c:out value="${orderItem.quantity}"/></td>
                                        <td><c:out value="${orderItem.quantity * orderItem.size.items.price}"/></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <th colspan="2"></th>
                                    <th>Итого</th>
                                    <th><c:out value="${order.count}"/></th>
                                    <th><c:out value="${order.totalPrice}"/></th>
                                </tr>
                            </table>
                        </div>
                        <c:if test="${order.status == 'Отправлен'}">
                            <form action="orders" method="post">
                                <input type="hidden" value="<c:out value="${order.id}"/>" name="orderId"/>
                                <div class="btn-block text-right">
                                    <button type="submit" name="orderAction" value="cancel" class="btn btn-warning">
                                        Отменить
                                    </button>
                                    <button type="submit" name="orderAction" value="perform" class="btn btn-success">
                                        Исполнить
                                    </button>
                                </div>
                            </form>
                        </c:if>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="WEB-INF/jspf/scripts.jspf" %>
</body>
</html>