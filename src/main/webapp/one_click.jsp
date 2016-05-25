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
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <ul class="nav nav-pills nav-stacked">
                        <%--@elvariable id="categories" type="java.util.List"--%>
                        <c:forEach items="${categories}" var="cat">
                            <li role="presentation" class="
                    <c:if test="${param.category != null && param.category==cat.id}">
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
            <div class="panel panel-default">
                <div class="panel-body">
                    <c:choose>
                        <c:when test="${requestScope.reqSize != null}">
                            <div class="row">
                                <div class="col-md-2">
                                    <a href="items?action=show&id=<c:out value="${reqSize.items.id}"/>">
                                        <img alt="placeholder"
                                             src="<c:out value="photos/${reqSize.items.photo}"/>"
                                             data-holder-rendered="true"
                                             style="height: 100px; width: 100px; display: block;">
                                    </a>
                                </div>

                                <div class="col-md-6">
                                    <h5><c:out value="${reqSize.items.title}"/></h5>
                                    <h5>Пол:
                                        <c:out value="${reqSize.items.gender}"/></h5>
                                    <h5> Выбранный размер:
                                        <span class="label label-success"><c:out value="${reqSize.size}"/></span></h5>
                                </div>

                                <div class=" col-md-4 text-right">
                                    <h3><span class="label label-primary">Цена:
                                <c:out value="${reqSize.items.price} Руб"/></span></h3>
                                </div>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <jsp:useBean id="cart" scope="session" type="com.github.sandra114.clothingshop.model.Cart"/>
                            <h4>
                                Ваш заказ содержит <span class="label label-primary"><c:out
                                    value="${cart.size}"/></span> товаров на общую сумму <span class="label label-primary"><c:out
                                    value="${cart.totalPrice}"/> руб.</span>
                            </h4>
                        </c:otherwise>
                    </c:choose>

                    <br>
                    <div class="panel-footer">
                        <div class="row">
                            <div class="col-md-12">
                                <form class="form-horizontal" action="confirm" method="post">
                                    <fieldset>

                                        <!-- Form Name -->
                                        <%--<legend></legend>--%>

                                        <!-- Text input-->
                                        <div class="form-group">
                                            <label class="col-md-4 control-label" for="firstName">Имя</label>
                                            <div class="col-md-4">
                                                <input id="firstName" name="firstName" type="text"
                                                       placeholder="введите имя"
                                                       class="form-control input-md" required="">

                                            </div>
                                        </div>

                                        <!-- Text input-->
                                        <div class="form-group">
                                            <label class="col-md-4 control-label" for="lastName">Фамилия</label>
                                            <div class="col-md-4">
                                                <input id="lastName" name="lastName" type="text"
                                                       placeholder="введите фамилию" class="form-control input-md"
                                                       required="">

                                            </div>
                                        </div>

                                        <!-- Text input-->
                                        <div class="form-group">
                                            <label class="col-md-4 control-label" for="phone">Телефон</label>
                                            <div class="col-md-4">
                                                <input id="phone" name="phone" type="text"
                                                       placeholder="введите номер телефона"
                                                       class="form-control input-md"
                                                       required="">

                                            </div>
                                        </div>

                                        <!-- Textarea -->
                                        <div class="form-group">
                                            <label class="col-md-4 control-label" for="address">Адрес доставки</label>
                                            <div class="col-md-4">
                                                <textarea class="form-control" id="address" name="address"
                                                          required></textarea>
                                            </div>
                                        </div>
                                        <c:if test="${requestScope.reqSize!= null}">
                                            <input type="hidden" name="sizeId"
                                                   value="<c:out value="${reqSize.id}"/>"/>
                                        </c:if>
                                        <!-- Button -->
                                        <div class="form-group">
                                            <label class="col-md-4 control-label" for="singlebutton"></label>
                                            <div class="col-md-4">
                                                <button id="singlebutton" name="singlebutton"
                                                        class="btn btn-danger btn-block">
                                                    Оформить заказ
                                                </button>
                                            </div>
                                        </div>

                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="WEB-INF/jspf/scripts.jspf" %>
</body>
</html>